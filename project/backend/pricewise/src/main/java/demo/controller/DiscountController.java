package demo.controller;

// 基本实现Restful API的包
import demo.mapper.DiscountMapper;
import demo.utils.APIResponse;
import demo.entity.Discount;
import demo.service.DiscountService;
import demo.utils.Encoder;
import demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/insert")
    public ResponseEntity<APIResponse> insertNewDiscount(@RequestParam String jwt_value, @RequestParam String item_name, @RequestParam Double price, @RequestParam LocalDateTime item_time) {
        String account;
        try {
            account = JwtUtil.paraJWT2account(jwt_value);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("invalid token", 200));
        }

        try {
            Discount discount = new Discount();
            discount.setAccount(account);
            discount.setPrice(price);
            discount.setItem_name(item_name);
            discount.setItem_time(item_time);

            discountService.insertDiscount(discount);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("sql exception", 200));
        }

        return ResponseEntity.ok(new APIResponse("ok", 200));
    }

    @GetMapping("/get")
    public ResponseEntity<APIResponse> getDiscount(@RequestParam String jwt_value) {
        String account;
        try {
            account = JwtUtil.paraJWT2account(jwt_value);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("invalid token", 200));
        }
        List<Discount> res = new ArrayList<>();
        try {
            res = discountService.selectDiscount(account);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("sql exception", 200));
        }
        return ResponseEntity.ok(new APIResponse("ok", 200, res));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<APIResponse> deleteDiscount(@RequestParam String jwt_value, @RequestParam String item_name) {
        String account;
        try {
            account = JwtUtil.paraJWT2account(jwt_value);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("invalid token", 200));
        }
        try {
            discountService.deleteDiscount(account, item_name);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("sql exception", 200));
        }
        return ResponseEntity.ok(new APIResponse("ok", 200));
    }

}
