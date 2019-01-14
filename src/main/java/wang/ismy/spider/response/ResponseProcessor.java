package wang.ismy.spider.response;

import wang.ismy.spider.Spider;
import wang.ismy.spider.request.Request;

import java.util.LinkedList;
import java.util.List;

/*
* 该类为response响应处理链
*/
public class ResponseProcessor {

    private List<ResponseProcessChain> processChainList = new LinkedList<>();

    private Spider spider ;
    public ResponseProcessor(Spider spider) {
        this.spider = spider;
    }

    public void registerProcessChain(ResponseProcessChain chain){
        processChainList.add(chain);
    }

    public void removeProcessChain(ResponseProcessChain chain){
        processChainList.remove(chain);
    }

    public void process(Request request, Response response){
        for (ResponseProcessChain chain : processChainList){
            chain.process(request,response,spider);
        }
    }
}
