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


public class amazonspider {

    static String url = "https://www.amazon.com/s?language=zh_CN&currency=CNY&k=";
    static Map<String, String> cookies = new HashMap<String, String>();

    public List<Item> get_amazonspider(String input) throws IOException {
        List<Item> res_item = new ArrayList<>();
        String whole_url = url + input;
        Document document = Jsoup.connect(whole_url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").get();
        LocalDateTime time = LocalDateTime.now();
        Elements res = document.select("[data-component-type='s-search-result']");
        for (Element element : res) {
            String item_final_id = element.attr("data-asin");
            Element pictElement = element.getElementsByClass("s-image").first();
            String pict = pictElement != null ? pictElement.attr("src") : "unknown";
            Element nameElement = element.getElementsByClass("a-size-medium a-color-base a-text-normal").first();
            String name = nameElement != null ? nameElement.text() : "unknown";
            Element currencyElement = element.getElementsByClass("a-price-symbol").first();
            String currency = currencyElement != null ? currencyElement.text() : "unknown";
            Element decimalElement = element.getElementsByClass("a-price-whole").first();
            String decimal = decimalElement != null ? decimalElement.text().replace(",", "") : "unknown";
            Element fractionElement = element.getElementsByClass("a-price-fraction").first();
            String fraction = fractionElement != null ? fractionElement.text().replace(",", "").substring(0, 2) : "unknown";
            if (pict.equals("unknown") || name.equals("unknown") || currency.equals("unknown") || decimal.equals("unknown") || fraction.equals("unknown"))
                continue;
            String price = decimal + fraction;
            double item_price = Double.parseDouble(price);
            if (currency.equals("US$"))
                item_price *= 7.1023;
            long round = Math.round(item_price * 100);
            item_price = round / 100.0;
            Item item = new Item();
            item.setItem_id(item_final_id);
            item.setItem_name(name);
            item.setPrice(item_price);
            item.setItem_time(time);
            item.setPlatform("亚马逊");
            item.setImage(pict);
            res_item.add(item);
        }
        return res_item;
    }
}
