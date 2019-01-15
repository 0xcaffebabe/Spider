package wang.ismy.spider.response;


import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
* 文本形式的HTTP响应
*/
@Data
public class TextResponse {

    private String content;

    /*
    * 可以通过css的选择器来选取文档的相关结点或者属性
    */
    public Elements css(String selector){
        Document document = Jsoup.parse(content);
        return document.select(selector);
    }

    @Override
    public String toString(){
        return content;
    }
}
