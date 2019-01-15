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
        Request request = new Request();
        request.setUrl("http://www.sina.com");
        spider.request(request,response -> {
            response
                    .toTextResponse("utf8")
                    .css("a")
                    .forEach(e->{
                        System.out.println(e.attr("href"));
                    });
        });


    }

}