package wang.ismy.spider;

import lombok.Data;

import java.util.Map;

@Data
public class Request {

    private String url;

    private Map<String,String> headers;

    private String body;


}
