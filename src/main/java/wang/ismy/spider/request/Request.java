package wang.ismy.spider.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Request {

    private String url;

    private Map<String,String> headers = new HashMap<>();

    private String body;

    public Request header(String key,String value){
        headers.put(key,value);
        return this;
    }


}
