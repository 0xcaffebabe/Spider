package wang.ismy.spider.event;

import wang.ismy.spider.Spider;
import wang.ismy.spider.request.Request;

public interface ConnectionTimeOutEvent {

    void onTimeOut(Spider spider, Request request);
}
