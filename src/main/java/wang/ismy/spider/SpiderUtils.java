package wang.ismy.spider;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SpiderUtils {

    /*
    * 根据传入的html代码获取所有a标签的href属性，结果返回list
    */
    public static List<String> getAllLinks(String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.select("a");

        List<String> list = new ArrayList<>();

        for (Element e : elements){
            list.add(e.attr("href"));
        }
        return list;

    }
}
