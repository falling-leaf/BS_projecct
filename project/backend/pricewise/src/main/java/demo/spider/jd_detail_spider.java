package demo.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import demo.entity.Item;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;


public class jd_detail_spider {

    static String url = "https://item.jd.com/";
    static Map<String, String> cookies = new HashMap<String, String>();

    // 这个爬虫到最后发现单就价格爬不下来，因此京东无法设置降价提醒（无法爬取单个的商品）
    public static void get_jdspider(String input) throws IOException, InterruptedException {
        List<Item> res_item = new ArrayList<>();
        String whole_url = url + input + ".html";

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
        // 这里需要定时修改cookie
        // cookies.put("thor", "FCEFB17C68A6956B16885A116411EE8674CDF6495EF568FC5628C94AC6609E770304FACE0099DA84E66D3052B79481B39AF461AF4B0809B21EE07B194DEBAC0E7678CE10CD2CB8C625BA252FC8FDAFA166764EDCD67B50B1A912176212ACCB33135D53BC138107E85B148945E314FD28E2E38C9F510E5C36255BFADA6A944F3A1206AB1A50C6092DA4F1D5CB1BC73E3E0D4A8CDB23F318D5667EB009523513A4");
        // 使用Jsoup解析渲染后的HTML
        Document document = Jsoup.parse(renderedHtml);
        System.out.println(document);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        get_jdspider("100149708968");
    }
}
