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
        cookies.put("thor", "FCEFB17C68A6956B16885A116411EE8674CDF6495EF568FC5628C94AC6609E770304FACE0099DA84E66D3052B79481B39AF461AF4B0809B21EE07B194DEBAC0E7678CE10CD2CB8C625BA252FC8FDAFA166764EDCD67B50B1A912176212ACCB33135D53BC138107E85B148945E314FD28E2E38C9F510E5C36255BFADA6A944F3A1206AB1A50C6092DA4F1D5CB1BC73E3E0D4A8CDB23F318D5667EB009523513A4");
        String whole_url = url + input;
        Document document = Jsoup.connect(whole_url).cookies(cookies).get();
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
