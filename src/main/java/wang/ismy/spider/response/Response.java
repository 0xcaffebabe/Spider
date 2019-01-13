package wang.ismy.spider.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Response {

    private int httpCode;

    private Map<String,List<String>> responseHeaders;

    private byte[] body;

}
