package wang.ismy.spider;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SpiderTest {


    @Test
    public void initTest() throws IOException {
        Spider spider = new Spider();
        Request request = new Request();
        request.setUrl("http://117.24.105.76");
        spider.request(request,response -> {
            String s = new String(response.getBody());
            System.out.println(s);

            System.out.println(response.getResponseHeaders());
        });
    }

}