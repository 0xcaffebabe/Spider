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
                .url("http://tieba.baidu.com/f?ie=utf-8&kw=%E6%B3%89%E5%B7%9E%E5%B8%88%E8%8C%83%E5%AD%A6%E9%99%A2&red_tag=w3212545833")
                .method(RequestMethods.GET);
        spider.request(request,response -> {
            response.toTextResponse("utf8").css("a").forEach(e->{
                if (e.text().contains("成绩")){
                    System.out.println(e.text()+" --- "+ "http://tieba.baidu.com" +e.attr("href"));
                }
            });
            spider.close();

        });


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