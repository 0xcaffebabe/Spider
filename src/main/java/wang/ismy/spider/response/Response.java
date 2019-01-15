package wang.ismy.spider.response;

import lombok.Data;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Data
public class Response {

    private int httpCode;

    private Map<String,List<String>> responseHeaders;

    private byte[] body;

    public void copy(Response response){
        httpCode = response.getHttpCode();
        responseHeaders  = response.getResponseHeaders();
        body = response.getBody();
    }

    public String toText(String charset){
        try {
            return new String(body,charset);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public TextResponse toTextResponse(String charset){
        String content = toText(charset);
        TextResponse textResponse = new TextResponse();
        textResponse.setContent(content);
        return textResponse;
    }
}
