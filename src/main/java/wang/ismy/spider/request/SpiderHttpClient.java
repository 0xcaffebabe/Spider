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

    public HttpURLConnection send(String url, Map<String,String> headers, int timeOutMS) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(timeOutMS);
        for (String key : headers.keySet()){
            connection.setRequestProperty(key,headers.get(key));
        }
        return (HttpURLConnection) connection;
    }
}
