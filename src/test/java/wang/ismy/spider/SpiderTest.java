package wang.ismy.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class SpiderTest {
    @Test
    public void initTest() throws IOException {
        Spider spider = new Spider();
        Request request = new Request();
        request.setUrl("https://www.baidu.com");
        spider.request(request,response -> {

            for (String s : response.getResponseHeaders().keySet()){

                System.out.println(s+" : " +response.getResponseHeaders().get(s));
            }

            System.out.println("-----");

            String html = new String(response.getBody());

            System.out.println(html);
        });
    }

}