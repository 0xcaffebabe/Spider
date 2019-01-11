package wang.ismy.spider;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.function.Consumer;


public class Spider {

    private int timeOutSec = 2000; // 超时时间

    private static final SpiderHttpClient spiderHttpClient = new SpiderHttpClient();

    public Spider(){}

    public Spider(int timeOutSec) {
        this.timeOutSec = timeOutSec;
    }

    public void request(Request request, Consumer<Response> consumer) throws IOException {
        URLConnection connection = spiderHttpClient.send(request.getUrl(),request.getHeaders());
        HttpURLConnection urlConnection = (HttpURLConnection)connection;

        Response response = new Response();
        response.setHttpCode(urlConnection.getResponseCode());
        response.setResponseHeaders(urlConnection.getHeaderFields());

        InputStream inputStream = connection.getInputStream();
        response.setBody(inputStream.readAllBytes());
        consumer.accept(response);
    }

}
