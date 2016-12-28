import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @author BFD_499
 * @create 2016-11-07 17:11
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.baidu.com/").get();
        doc.toString();
        Element element = doc.getElementById("form");
        String attrTd = element.attr("action");
        String attrTd1 = element.attr("abs:action");
    }
}
