package wang.ismy.spider;

import java.util.LinkedList;
import java.util.List;

/*
* 该类为response响应处理链
*/
public class ResponseProcessor {

    private List<ResponseProcessChain> processChainList = new LinkedList<>();

    public ResponseProcessor() { }

    public void registerProcessChain(ResponseProcessChain chain){
        processChainList.add(chain);
    }

    public void removeProcessChain(ResponseProcessChain chain){
        processChainList.remove(chain);
    }

    public void process(Response response){
        for (ResponseProcessChain chain : processChainList){
            chain.process(response);
        }
    }
}
