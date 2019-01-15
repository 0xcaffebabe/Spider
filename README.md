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
        Spider spider = new Spider();
        Request request = new Request()
                .url("http://www.baidu.com")
                .header("User-Agent","baidu spider");
        spider.request(request,response -> {
            System.out.println(response
                    .toTextResponse("utf8"));
        });
```

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
