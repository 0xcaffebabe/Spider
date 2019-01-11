package wang.ismy.spider;

import org.junit.Test;
import java.io.IOException;


public class SpiderTest {
    @Test
    public void initTest() throws IOException {
        Spider spider = new Spider();
        Request request = new Request();
        request.setUrl("http://www.baidu.com");
        //request.header("User-Agent","chrome");
        spider.request(request,response -> {
            String html = new String(response.getBody());
            var list = SpiderUtils.getAllLinks(html);
            for (var i : list){
                System.out.println(i);
            }
        });
    }

}