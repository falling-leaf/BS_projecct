package demo.utils;

import demo.entity.Discount;
import demo.entity.Item;
import demo.entity.User;
import demo.spider.alispider;
import demo.spider.amazonspider;
import demo.spider.jdspider;
import org.springframework.beans.factory.annotation.Autowired;

import demo.service.DiscountService;
import demo.service.UserService;
import demo.service.ItemService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import demo.utils.EmailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RefreshDiscount {

    @Autowired
    private DiscountService discount_service;

    @Autowired
    private UserService user_service;

    @Autowired
    private ItemService item_service;

    @PostConstruct
    public void refreshDiscount() {
        List<Discount> tar = discount_service.getAllDiscount();
        LocalDateTime present_time = LocalDateTime.now();
        for (Discount el : tar) {
            String item_name = el.getItem_name();
            Double price = el.getPrice();
            Double Newprice = 0.0;
            String platform = el.getPlatform();
            List<Item> ret = new ArrayList<>();
            try {
                switch (platform) {
                    case "京东" -> {
                        jdspider en_jdspider = new jdspider();
                        ret = en_jdspider.get_jdspider(item_name);
                    }
                    case "阿里巴巴" -> {
                        alispider en_alispider = new alispider();
                        ret = en_alispider.get_alispider(item_name);
                    }
                    case "亚马逊" -> {
                        amazonspider en_amazonspider = new amazonspider();
                        ret = en_amazonspider.get_amazonspider(item_name);
                    }
                }
            } catch (Exception e) {
                System.out.println("降价更新拉取失败");
            }
            for (Item item : ret) {
                if (item.getItem_name().equals(item_name)) {
                    Newprice = item.getPrice();
                    // if (true) {
                    if (Newprice < price) {
                        // 发送邮件的逻辑
                        User res_user = user_service.find_user_by_account(el.getAccount());
                        if (res_user != null) {
                            String email = res_user.getEmail();
                            String text = "您关注的商品：【" + item_name + "】已经降价，快去看看吧！";
                            String subject = "pricewise：降价提醒";
                            EmailSender.email_sender(email, text, subject);
                        }
                    }
                    discount_service.updateDiscount(Newprice, present_time, item_name);
                }
                item_service.insertItem(item);
            }
        }
        System.out.println("降价提醒操作已完成");
    }
}