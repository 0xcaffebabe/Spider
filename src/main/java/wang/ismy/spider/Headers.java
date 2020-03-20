package wang.ismy.spider;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Data
public class Headers {

    private Map<String,String> headers = new HashMap<>();

    public Headers header(String key,String value){
        headers.put(key,value);
        return this;
    }

    public String header(String key){
        return headers.get(key);
    }

    public void forEach(Consumer<Header> consumer){

        for (String key : headers.keySet()){
            Header header = new Header();
            header.setKey(key);
            header.setValue(headers.get(key));
            consumer.accept(header);
        }
    }
}
