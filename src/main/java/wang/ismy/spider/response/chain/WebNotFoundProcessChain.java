package wang.ismy.spider.response.chain;

import wang.ismy.spider.Spider;
import wang.ismy.spider.enums.HttpState;
import wang.ismy.spider.request.Request;
import wang.ismy.spider.response.Response;
import wang.ismy.spider.response.ResponseProcessChain;

public class WebNotFoundProcessChain implements ResponseProcessChain {
    @Override
    public void process(Request request, Response response, Spider spider) {
        if (response.getHttpCode() == HttpState.NOT_FOUND_404.getCode()) {
            System.out.println(request+"该网页无法找到");
        }
    }
}
