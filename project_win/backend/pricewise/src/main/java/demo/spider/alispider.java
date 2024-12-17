package demo.spider;

import demo.entity.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class alispider {

    static String url = "https://p4psearch.1688.com/page.html?hpageId=old-sem-pc-list&spm=a2638t.b_78128457.szyxhead.submit&exp=offerWwClick%3AA&keywords=";
    static Map<String, String> cookies = new HashMap<String, String>();

    public List<Item> get_alispider(String input) throws InterruptedException {
        List<Item> res_item = new ArrayList<>();
        String whole_url = url + input;

        // 使用Selenium动态渲染页面
        WebDriver driver = null;
        // 设置EdgeDriver的路径
        String edgeDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", edgeDriverPath);

        // 创建EdgeOptions对象
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless"); // 无头模式，不打开浏览器窗口

        // 创建WebDriver实例
        driver = new EdgeDriver(options);

        // 打开目标URL
        driver.get(whole_url);

        // 等待页面加载完成（可以根据需要调整等待时间）
        Thread.sleep(2000); // 等待5秒

        // 获取渲染后的页面源代码
        String renderedHtml = driver.getPageSource();

        // 使用Jsoup解析渲染后的HTML
        Document document = Jsoup.parse(renderedHtml);
        // System.out.println(document);
        // Document document = Jsoup.connect(whole_url).get();
        LocalDateTime time = LocalDateTime.now();

        Elements overall = document.select(".mt20.over_hide.w_1024.offer_list");
        int i = 0;
        for (Element all_element : overall) {
            Elements all_items =  all_element.children();
            for (Element element : all_items) {
                Element img_element = element.getElementsByClass("main-img layLoad").first();
                String img = img_element != null ? img_element.attr("src") : null;
                Element name_element = element.getElementsByClass("offer-title").first();
                String name = name_element != null ? name_element.text() : null;
                Element price_element = element.getElementsByClass("offer-price").first();
                String price = price_element != null ? price_element.text() : null;
                int offset = 0;
                if (price != null) {
                    for (offset = 0; (price.charAt(offset) != '.'); offset++);
                    price = price.substring(1, offset + 3);
                }
                if (name == null || price == null || img == null)
                    continue;
                Item item = new Item();
                item.setItem_name(name);
                item.setPrice(Double.parseDouble(price));
                item.setItem_time(time);
                item.setPlatform("阿里巴巴");
                item.setImage(img);
                res_item.add(item);
            }
        }
        System.out.println("items from ali_spider: " + res_item.size());
        return res_item;
    }
//    public static void main(String[] args) throws InterruptedException {
//        get_alispider("手机");
//    }
}
