package demo.service;

import demo.entity.Discount;
import demo.mapper.DiscountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DiscountService {

    @Autowired
    private DiscountMapper discountMapper;

    public void insertDiscount(Discount discount) {
        discountMapper.insertNewDiscount(discount);
    }

    public List<Discount> selectDiscount(String account) {
        return discountMapper.findDiscountByAccount(account);
    }

    public List<Discount> getAllDiscount() {
        return discountMapper.findDiscount();
    }

    public void deleteDiscount(String account, String item_name) {
        discountMapper.deleteDiscount(account, item_name);
    }

    public List<Discount> checkDiscount(String account, String item_name) {
        return discountMapper.findDiscountByAccountandName(account, item_name);
    }

    public void updateDiscount(Double price, LocalDateTime item_time, String item_name) {
        discountMapper.updatePriceByName(price, item_time, item_name);
    }
}
