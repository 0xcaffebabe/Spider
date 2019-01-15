package wang.ismy.spider;

import org.junit.Assert;
import org.junit.Test;
import wang.ismy.spider.event.ConnectionTimeOutEvent;
import wang.ismy.spider.request.Request;

import java.io.IOException;


public class SpiderTest {



    @Test
    public void test() throws InterruptedException {

    }

    public static void main(String[] args) {
        Spider spider = new Spider();
        spider.setConnectionTimeOutEvent((spider1, request) -> {
            System.out.println(request.getUrl()+"超时了");
        });
        Request request = new Request()
                .url("https://www.imooc.com/u/59654/courses")
                .header("User-Agent","baidu spider");
        spider.request(request,response -> {
            System.out.println(response
                    .toTextResponse("utf8"));
        });


    }

}