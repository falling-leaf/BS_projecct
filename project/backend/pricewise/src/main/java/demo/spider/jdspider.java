package demo.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import demo.entity.Item;

import java.util.List;


public class jdspider {

    static String url = "https://search.jd.com/Search?keyword=";
    static Map<String, String> cookies = new HashMap<String, String>();

    public List<Item> get_jdspider(String input) throws IOException {
        List<Item> res_item = new ArrayList<>();
        // 这里需要定时修改cookie
        cookies.put("thor", "37B4D61543EED1286AD903B1BE3347B2112FCB3B1B7A5BC33EDCEFA3EF70FDE784FFEFD7429DD401E10276FA572450EBDF615331827FCE275286EB762747FAE8CAD59BD5B0387A83AC87131C975A491E003486D29F1BFA2B2FE6A19FB167639781DEC3452E87681F85197B6328217F2C5D3E1332905294C267BACF822AEC97678C1C74C65C23CA0C30E998C559B1D2F8CCAFD0B8B2AA3CE67B4BE608A4FB79C4");
        String whole_url = url + input;
        Document document = Jsoup.connect(whole_url).cookies(cookies).get();
        LocalDateTime time = LocalDateTime.now();
        // 通过class获取ul标签
        Elements ul = document.getElementsByClass("gl-warp clearfix");
        // 获取ul标签下的所有li标签
        Elements liList = ul.select("li");
        for (Element element : liList) {
            Element pictElement = element.getElementsByTag("img").first();
            String pict = pictElement != null ? pictElement.attr("data-lazy-img") : "unknown";
            Element nameElement = element.getElementsByClass("p-name").first();
            Element subnameElement = nameElement != null ? nameElement.getElementsByTag("em").first() : null;
            String name = subnameElement != null ? subnameElement.text() : "unknown";
            Element priceElement = element.getElementsByClass("p-price").first();
            String price = priceElement != null ? priceElement.text().substring(1) : "unknown";
            Element shopNameElement = element.getElementsByClass("p-shop").first();
            String shopName = shopNameElement != null ? shopNameElement.text() : "unknown";
            if (name.equals("unknown") || price.equals("unknown") || shopName.equals("unknown") || pict.equals("unknown"))
                continue;
            Item item = new Item();
            item.setItem_name(name);
            item.setPrice(Double.parseDouble(price));
            item.setShop_name(shopName);
            item.setItem_time(time);
            item.setPlatform("jingdong");
            item.setImage(pict);
            res_item.add(item);
//            System.out.println("------------------");
//            System.out.println(pict);
//            System.out.println(name);
//            System.out.println(price);
//            System.out.println(shopName);
        }
        return res_item;
    }
}
