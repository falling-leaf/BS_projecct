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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;

// 亚马逊也烂完了，nmd100多万个字符没有我要的东西
public class amazon_detail_spider {

    static String url = "https://www.amazon.com/dp/";
    static Map<String, String> cookies = new HashMap<String, String>();

    public static List<Item> get_amazonspider(String input) throws IOException, InterruptedException {
        List<Item> res_item = new ArrayList<>();
        String whole_url = url + input + "/ref=sr_1_1_sspa?currency=CN&th=1";

        // 使用Selenium动态渲染页面
        WebDriver driver = null;
        // 设置EdgeDriver的路径
        String edgeDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", edgeDriverPath);

        // 创建EdgeOptions对象
        EdgeOptions options = new EdgeOptions();

        options.addArguments("--headless"); // 无头模式，不打开浏览器窗口

        // 设置自定义的HTTP请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        // 将自定义的HTTP请求头添加到EdgeOptions中
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            options.addArguments(String.format("--header=%s:%s", entry.getKey(), entry.getValue()));
        }

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
//        Document document = Jsoup.connect(whole_url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
//                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").get();
        LocalDateTime time = LocalDateTime.now();
        System.out.println(document);
//        Elements res = document.select("a-price-whole");
//        for (Element element : res) {
//            System.out.println(element);
//        }
        return res_item;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        get_amazonspider("B0CMDMKQB7");
    }
}
