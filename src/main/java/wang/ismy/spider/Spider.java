package wang.ismy.spider;

import wang.ismy.spider.request.Request;
import wang.ismy.spider.request.SpiderHttpClient;
import wang.ismy.spider.response.Response;
import wang.ismy.spider.response.ResponseProcessor;
import wang.ismy.spider.response.chain.WebNotFoundProcessChain;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.function.Consumer;


public class Spider {

    private int timeOutSec = 2000; // 超时时间

    private static final SpiderHttpClient spiderHttpClient = new SpiderHttpClient();

    private ResponseProcessor responseProcessor = new ResponseProcessor();

    public Spider(){
        responseProcessor.registerProcessChain(new WebNotFoundProcessChain());
    }

    public Spider(int timeOutSec) {
        this.timeOutSec = timeOutSec;
    }

    public void request(Request request, Consumer<Response> consumer) throws IOException {
        URLConnection connection = spiderHttpClient.send(request.getUrl(),request.getHeaders());
        HttpURLConnection urlConnection = (HttpURLConnection)connection;
        Response response = new Response();
        response.setHttpCode(urlConnection.getResponseCode());
        response.setResponseHeaders(urlConnection.getHeaderFields());

        InputStream inputStream = null;

        try{
            inputStream = connection.getInputStream();
        }catch (Exception e){
            inputStream = ((HttpURLConnection) connection).getErrorStream();
        }

        response.setBody(inputStream.readAllBytes());

        //得到响应之后，该响应会被传入到一个response处理链当中
        responseProcessor.process(response);
        consumer.accept(response);
    }

}
