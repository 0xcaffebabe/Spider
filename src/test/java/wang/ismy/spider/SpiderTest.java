package wang.ismy.spider;

import org.junit.Test;
import wang.ismy.spider.request.Request;

import java.io.IOException;


public class SpiderTest {
    @Test
    public void initTest() throws IOException {
        Spider spider = new Spider();
        Request request = new Request();
        request.setUrl("http://www.ismy.wang");
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