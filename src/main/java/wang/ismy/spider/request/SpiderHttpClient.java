package wang.ismy.spider.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;

public class SpiderHttpClient {



    public SpiderHttpClient() {

    }

    public URLConnection send(String url,Map<String,String> headers) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        for (String key : headers.keySet()){
            connection.setRequestProperty(key,headers.get(key));
        }
        return connection;
    }
}
