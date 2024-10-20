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
        cookies.put("thor", "E55A2BEA2F22DF6E6991503652DC7EE92A06DF1C3681AC13365673509B725C1E970EDDD598490C9837FDB75CEF0E47721E896E396751A99544CBE2C3030748649E92AA5CDEC7ECAF5972C7B95E1C9E22CFB180605F63CEFA7ECEB29C4D1534A7DCF829760554F3C66125FAB670F7CEDA5BD7DC3BC526F0D3757A25E06CEFC016EC6CBE92C480C189902A2D4C9184B683");
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
