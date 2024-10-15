package demo.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class jdspider {
    static String url = "https://search.jd.com/Search?keyword=%E9%A4%90%E5%B7%BE%E7%BA%B8";

    public static void main(String[] args) throws IOException {
        Map<String, String> cookies = new HashMap<String, String>();
        cookies.put("thor", "37B4D61543EED1286AD903B1BE3347B2112FCB3B1B7A5BC33EDCEFA3EF70FDE7F8A0F3C12A5A8D08B676333F29C411E840BAFC6646D81A47C55227A16726F6FB98F6E1AC08EDBBA5442073BA6EC821C1FC242FBD7045D970A25AB6BF49A17E355EF3F84DD2A0FC25588D90CBE0B3C0913A874DE0273DF30EC204D5F72EF168312D383F03AD15F725CF8C4F640EDD079970E9ECDB02C18C6C213A5CA535662797");

        Document document = Jsoup.connect(url).cookies(cookies).get();

        // 通过class获取ul标签
        Elements ul = document.getElementsByClass("gl-warp clearfix");
        // 获取ul标签下的所有li标签
        Elements liList = ul.select("li");
        for (Element element : liList) {
            System.out.println("------------------");
            String pict = element.getElementsByTag("img").first().attr("data-lazy-img");
            String price = element.getElementsByClass("p-price").first().text();
            String shopName = element.getElementsByClass("p-shop").first().text();
            System.out.println(pict);
            System.out.println(price);
            System.out.println(shopName);
//            System.out.println(element);
//            System.out.println();
        }
    }
}
