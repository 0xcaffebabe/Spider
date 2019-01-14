package wang.ismy.spider.response;


import wang.ismy.spider.Spider;
import wang.ismy.spider.request.Request;
import wang.ismy.spider.response.Response;

/*
*
* 实现该接口的类可以被添加的蜘蛛的response消息处理链中
* */
public interface ResponseProcessChain {

    void process(Request request, Response response, Spider spider);
}
