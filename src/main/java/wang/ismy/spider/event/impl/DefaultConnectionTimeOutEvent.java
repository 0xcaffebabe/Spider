package wang.ismy.spider.event.impl;

import wang.ismy.spider.Spider;
import wang.ismy.spider.event.ConnectionTimeOutEvent;
import wang.ismy.spider.request.Request;

public class DefaultConnectionTimeOutEvent implements ConnectionTimeOutEvent {
    @Override
    public void onTimeOut(Spider spider, Request request) {
        System.out.println(request+"超时了");
    }
}
