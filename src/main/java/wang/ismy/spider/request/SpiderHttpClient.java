package wang.ismy.spider.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;

public class SpiderHttpClient {




    public SpiderHttpClient() {

    }

    public HttpURLConnection send(Request request,int timeOutMS) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
        connection.setRequestMethod(request.getRequestMethods().getMethod());
        connection.setConnectTimeout(timeOutMS);
        for (String key : request.getHeaders().keySet()){
            connection.setRequestProperty(key,request.getHeaders().get(key));
        }
        return connection;
    }
}
