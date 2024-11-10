package demo.controller;

// 基本实现Restful API的包
import demo.entity.History;
import demo.service.ItemService;
import demo.utils.APIResponse;
import demo.entity.Item;
import demo.service.ItemService;
import demo.utils.Encoder;
import demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.spider.jdspider;
import demo.spider.vipspider;
import demo.spider.amazonspider;
import demo.spider.alispider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/insert")
    public ResponseEntity<APIResponse> insert_item(@RequestParam String input) {
        jdspider en_jdspider = new jdspider();
        vipspider en_vipspider = new vipspider();
        amazonspider en_amazonspider = new amazonspider();
        alispider en_alispider = new alispider();
        List<Item> item_set = new ArrayList<>();
        List<Item> tmp_item_set = new ArrayList<>();
        System.out.println("ready to start creeping.");
        try {
            tmp_item_set = en_jdspider.get_jdspider(input);
            // 为了性能，只截取前40条
            if (tmp_item_set.size() >= 40)
                tmp_item_set = tmp_item_set.subList(0, 40);
            item_set.addAll(tmp_item_set);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok(new APIResponse("fail on jdspider", 200));
        }
//        try {
//            tmp_item_set = en_vipspider.get_vipspider(input);
//            // 为了性能，只截取前40条
//            if (tmp_item_set.size() >= 40)
//                tmp_item_set = tmp_item_set.subList(0, 40);
//            item_set.addAll(tmp_item_set);
//        } catch (Exception e) {
//            return ResponseEntity.ok(new APIResponse("fail on vipspider", 200));
//        }
        try {
            tmp_item_set = en_alispider.get_alispider(input);
            // 为了性能，只截取前40条
            if (tmp_item_set.size() >= 40)
                tmp_item_set = tmp_item_set.subList(0, 40);
            item_set.addAll(tmp_item_set);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("fail on alispider", 200));
        }
        try {
            tmp_item_set = en_amazonspider.get_amazonspider(input);
            if (tmp_item_set.size() >= 40)
                tmp_item_set = tmp_item_set.subList(0, 40);
            item_set.addAll(tmp_item_set);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("fail on amazonspider", 200));
        }
        // 把东西弄到数据库中
        for (Item item : item_set) {
            itemService.insertItem(item);
        }

        // 注：这里考虑到前端的工作并不包括“对商品id进行调动”，因此不再复杂化其中的逻辑，让传到前端的item.id无效化
        return ResponseEntity.ok(new APIResponse("get successfully", 200, item_set));
    }

    @GetMapping("/get")
    public ResponseEntity<APIResponse> get_item(@RequestParam String name) {
        List<Item> res_item =  itemService.selectItem(name);
        res_item.sort(Comparator.comparing(Item::getItem_time).reversed());
        return ResponseEntity.ok(new APIResponse("get successfully", 200, res_item));
    }
}
