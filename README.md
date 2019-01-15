# Spider
2019-1-10
启动项目
#
注意事项：
项目用的JDK版本为11，并且使用了一些JDK11的新特性，运行低于JDK11可能会出现异常

#
2019-1-15

一个简单应用：
```java
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
```

![avatar](https://ismy1.oss-cn-qingdao.aliyuncs.com/gif8.gif)
#
扩展：

可以实现该接口：

```java
public interface ResponseProcessChain {

    void process(Request request, Response response, Spider spider);
}
```
在Spider构造函数中进行注册：
```java
public Spider(){
        responseProcessor.registerProcessChain(new WebNotFoundProcessChain());
        responseProcessor.registerProcessChain(new MovedTemporarilyProcessChain());
    }
```
当一个请求通过request完成之后，会根据注册顺序依次调用相关处理器，


  

使用者可以根据自身需要分别对request,response,spider等对象进行修改

2->
    
```java
spider.setConnectionTimeOutEvent((spider1, request) -> {
            System.out.println(request.getUrl()+"超时了");
        });
```

可以向该函数传入一个实现了该接口的事件：

```java
public interface ConnectionTimeOutEvent {

    void onTimeOut(Spider spider, Request request);
}
```

当请求超时，这个事件将会被调用
#
如果进行大量爬取操作,该段代码可能会成为性能瓶颈
```java
public URLConnection send(String url,Map<String,String> headers) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        for (String key : headers.keySet()){
            connection.setRequestProperty(key,headers.get(key));
        }
        return connection;
    }
```
后期会考虑连接复用，前期暂时不考虑性能问题
##
