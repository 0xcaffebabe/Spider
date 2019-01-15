package wang.ismy.spider;

import wang.ismy.spider.event.ConnectionTimeOutEvent;
import wang.ismy.spider.event.SpiderRequestAroundEvent;
import wang.ismy.spider.event.impl.DefaultConnectionTimeOutEvent;
import wang.ismy.spider.request.Request;
import wang.ismy.spider.request.SpiderHttpClient;
import wang.ismy.spider.response.Response;
import wang.ismy.spider.response.ResponseProcessor;
import wang.ismy.spider.response.chain.MovedTemporarilyProcessChain;
import wang.ismy.spider.response.chain.WebNotFoundProcessChain;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class Spider {

    private int timeOutMS = 2000; // 超时时间

    private static final SpiderHttpClient spiderHttpClient = new SpiderHttpClient();

    private ResponseProcessor responseProcessor = new ResponseProcessor(this); // 响应消息处理链

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private ConnectionTimeOutEvent connectionTimeOutEvent = new DefaultConnectionTimeOutEvent();

    private boolean close = false;

    public Spider(){
        responseProcessor.registerProcessChain(new WebNotFoundProcessChain());
        responseProcessor.registerProcessChain(new MovedTemporarilyProcessChain());

    }

    public Spider(int timeOutSec) {
        this.timeOutMS = timeOutSec;
    }

    public void request(Request request, Consumer<Response> consumer) {
        if (close){
            throw new IllegalStateException("当前的spider已被关闭");
        }
        executorService.execute(() -> {
            try {

                sendRequest(request, consumer);
            }catch (IOException e){
                if (e instanceof SocketTimeoutException){
                    connectionTimeOutEvent.onTimeOut(this,request);
                    return;
                }
                throw new RuntimeException(e);
            }

        });

    }

    public void request(Request request,Consumer<Response> consumer,boolean multiThread){
        if (multiThread){
            request(request,consumer);
        }else{
            try {
                sendRequest(request,consumer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /*
    * 终止所有任务并退出
    */
    public void close(){
        if (close){
            throw new IllegalStateException("当前的spider已被关闭");
        }
        if (!executorService.isShutdown()){
            executorService.shutdown();
            try {
                executorService.awaitTermination(1000,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
            close=true;
        }
    }

    private void sendRequest(Request request, Consumer<Response> consumer) throws IOException {
        HttpURLConnection connection = sendRequestAndGetConnection(request);
        Response response = getResponse(connection);
        InputStream inputStream = getInputStream(connection);
        response.setBody(inputStream.readAllBytes());
        //得到响应之后，该响应会被传入到一个response处理链当中
        responseProcessor.process(request,response);
        // 调用传入的消费者代码
        consumer.accept(response);
    }

    private InputStream getInputStream(HttpURLConnection connection) {
        InputStream inputStream = null;

        try{
            inputStream = connection.getInputStream();
        }catch (Exception e){
            inputStream = connection.getErrorStream();
        }
        return inputStream;
    }

    private Response getResponse(HttpURLConnection connection) throws IOException {
        Response response = new Response();
        response.setHttpCode(connection.getResponseCode());
        response.setResponseHeaders(connection.getHeaderFields());
        return response;
    }

    private HttpURLConnection sendRequestAndGetConnection(Request request) throws IOException {
        return spiderHttpClient.send(request.getUrl(),request.getHeaders(),timeOutMS);
    }

    public void setConnectionTimeOutEvent(ConnectionTimeOutEvent connectionTimeOutEvent) {
        this.connectionTimeOutEvent = connectionTimeOutEvent;
    }
}
