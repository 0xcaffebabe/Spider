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
        request.setUrl("https://weibo.com");
        spider.request(request,response -> {

            for (String s : response.getResponseHeaders().keySet()){

                System.out.println(s+" : " +response.getResponseHeaders().get(s));
            }

            System.out.println("-----");

            String html = null;
            try {
                html = new String(response.getBody(),"gb2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            Document document = Jsoup.parse(html);
            System.out.println(document);
        });
    }

}