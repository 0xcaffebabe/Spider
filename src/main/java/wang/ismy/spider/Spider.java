package wang.ismy.spider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Spider {

    private int timeOutSec = 2000; // 超时时间

    private String userAgent = "spider"; // UA头

    private static final SpiderHttpClient spiderHttpClient = new SpiderHttpClient();

    public Spider(){}

    public Spider(int timeOutSec) {
        this.timeOutSec = timeOutSec;
    }

    public void request(Request request, Consumer<Response> consumer) throws IOException {
        URLConnection connection = spiderHttpClient.send(request.getUrl());
        HttpURLConnection urlConnection = (HttpURLConnection)connection;

        Response response = new Response();
        response.setHttpCode(urlConnection.getResponseCode());
        response.setResponseHeaders(urlConnection.getHeaderFields());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = new byte[1024];
        response.setBody(inputStream.readAllBytes());
        consumer.accept(response);
    }

}
