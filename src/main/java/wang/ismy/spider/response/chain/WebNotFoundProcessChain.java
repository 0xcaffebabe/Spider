package wang.ismy.spider.response.chain;

import wang.ismy.spider.response.Response;
import wang.ismy.spider.response.ResponseProcessChain;

public class WebNotFoundProcessChain implements ResponseProcessChain {
    @Override
    public void process(Response response) {
        if (response.getHttpCode() == 404){
            System.out.println("该网页无法找到");
        }
    }
}
