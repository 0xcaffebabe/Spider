package wang.ismy.spider.response;


import wang.ismy.spider.response.Response;

/*
*
* 实现该接口的类可以被添加的蜘蛛的response消息处理链中
* */
public interface ResponseProcessChain {

    void process(Response response);
}
