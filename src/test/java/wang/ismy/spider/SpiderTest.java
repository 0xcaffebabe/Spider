package wang.ismy.spider;

import org.junit.Assert;
import org.junit.Test;
import wang.ismy.spider.request.Request;

import java.io.IOException;


public class SpiderTest {



    @Test
    public void test() throws InterruptedException {

    }

    public static void main(String[] args) {
        Spider spider = new Spider();
        Request request = new Request()
                .url("http://www.baidu.com")
                .header("User-Agent","baidu spider");
        spider.request(request,response -> {
            System.out.println(response
                    .toTextResponse("utf8"));
        });


    }

}