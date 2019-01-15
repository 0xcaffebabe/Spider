package wang.ismy.spider;

import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Assert;
import org.junit.Test;
import wang.ismy.spider.enums.RequestMethods;
import wang.ismy.spider.event.ConnectionTimeOutEvent;
import wang.ismy.spider.request.Request;
import wang.ismy.spider.response.Response;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class SpiderTest {


    private static Spider spider = new Spider();
    private static Map<String,String> map = new HashMap<>();
    public static void main(String[] args) {

        spider.setConnectionTimeOutEvent((spider1, request) -> {
            System.out.println(request.getUrl()+"超时了");
        });
        Request request = new Request()
                .url("http://dytt8.net/")
                .method(RequestMethods.GET);
        request(request);

    }

    public static void request(Request request){
       spider.request(request,response -> {
           response.toTextResponse("gb2312")
                   .css("td[style=WORD-WRAP: break-word] a")
                   .forEach(e->{
                       System.out.println(e.attr("href"));
                   });
           response.toTextResponse("gb2312")
                   .css("a")
                   .forEach(e->{
                       String url = null;
                       if (!e.attr("href").startsWith("http://")){
                           url = "http://dytt8.net"+e.attr("href");
                       }else{
                           url = e.attr("href");
                       }
                       Request subRequest = new Request()
                               .url(url);
                       request(subRequest);
                   });
       });
    }





}