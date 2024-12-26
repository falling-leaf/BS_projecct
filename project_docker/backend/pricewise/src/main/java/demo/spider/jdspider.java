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

    static public List<Item> get_jdspider(String input) throws IOException {
        List<Item> res_item = new ArrayList<>();
        // 这里需要定时修改cookie
        cookies.put("thor", "37B4D61543EED1286AD903B1BE3347B2112FCB3B1B7A5BC33EDCEFA3EF70FDE7F63C95D261BF82E0A9173714E8DBD69883DA7E7894C306C8876FC7C562E0E7417AD7368420CFFCC4CB66CA46E70FC06A99226F57075CD8966DD23A76599A10479BE697B3DCD78E1F8E0BF1D0327BA43325FD557EE95ACC9AF297F1DCA62946B02C0EC128CADC53DDEBDD3EFF4830CA7F88AACC2BD8DC4A7A06A7292BE588EB1C");
        String whole_url = url + input;
        Document document = Jsoup.connect(whole_url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").cookies(cookies).get();
        // System.out.println(document);
        LocalDateTime time = LocalDateTime.now();
        // 通过class获取ul标签
        Elements ul = document.getElementsByClass("gl-warp clearfix");
        // 获取ul标签下的所有li标签
        Elements liList = ul.select("li");
        for (Element element : liList) {
            String item_final_id = element.attr("data-sku");
            Element pictElement = element.getElementsByTag("img").first();
            String pict = pictElement != null ? pictElement.attr("data-lazy-img") : "unknown";
            Element nameElement = element.getElementsByClass("p-name").first();
            Element subnameElement = nameElement != null ? nameElement.getElementsByTag("em").first() : null;
            String name = subnameElement != null ? subnameElement.text() : "unknown";
            Element priceElement = element.getElementsByClass("p-price").first();
            String price = priceElement != null ? priceElement.text().substring(1).replaceAll("[^\\d.]", "") : "unknown";
            Element shopNameElement = element.getElementsByClass("p-shop").first();
            String shopName = shopNameElement != null ? shopNameElement.text() : "unknown";
            if (name.equals("unknown") || price.equals("unknown") || shopName.equals("unknown") || pict.equals("unknown") || price.isEmpty())
                continue;
            long round = Math.round(Double.parseDouble(price) * 100);
            Double final_price = round / 100.0;
            Item item = new Item();
            item.setItem_id(item_final_id);
            item.setItem_name(name);
            item.setPrice(final_price);
            item.setShop_name(shopName);
            item.setItem_time(time);
            item.setPlatform("京东");
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
