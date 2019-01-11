# Spider
2019-1-10
启动项目
#
注意事项：
项目用的JDK版本为11，并且使用了一些JDK11的新特性，运行低于JDK11可能会出现异常

#
2019-1-11

第一个应用：
```java
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
```
#
暂时不考性能问题
##
