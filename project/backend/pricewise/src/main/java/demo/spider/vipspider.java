package demo.spider;

import org.jsoup.Connection;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;


public class vipspider {

    static String url = "https://category.vip.com/suggest.php?keyword=";

    public List<Item> get_vipspider(String input) throws InterruptedException {
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
        Thread.sleep(5000); // 等待5秒

        // 获取渲染后的页面源代码
        String renderedHtml = driver.getPageSource();

        // 使用Jsoup解析渲染后的HTML
        Document document = Jsoup.parse(renderedHtml);
        // Document document = Jsoup.connect(whole_url).get();
        LocalDateTime time = LocalDateTime.now();

        Elements all_items = document.getElementsByClass("c-goods-item");
        for (Element element : all_items) {
            String pict = element.select(".c-goods-item__img img.lazy").attr("data-original");
            if (pict.isEmpty()) {
                Element pictElement = element.getElementsByTag("img").first();
                pict = pictElement != null ? pictElement.attr("src") : "unknown";
            }
            Element nameElement = element.getElementsByClass("c-goods-item__name  c-goods-item__name--two-line").first();
            String name = nameElement != null ? nameElement.text() : "unknown";
            Element priceElement = element.getElementsByClass("c-goods-item__sale-price J-goods-item__sale-price").first();
            String price = priceElement != null ? priceElement.text().substring(1) : "unknown";
            if (name.equals("unknown") || price.equals("unknown") || pict.equals("unknown"))
                continue;
            Item item = new Item();
            item.setItem_name(name);
            item.setPrice(Double.parseDouble(price));
            item.setItem_time(time);
            item.setPlatform("vip");
            item.setImage(pict);
            res_item.add(item);
//            System.out.println("------------------");
//            System.out.println(pict);
//            System.out.println(name);
//            System.out.println(price);
        }
        return res_item;
    }
}