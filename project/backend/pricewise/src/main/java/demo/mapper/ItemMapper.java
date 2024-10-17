package demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;

import demo.entity.Item;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ItemMapper {
    // 1. 搜索后展示商品，先展示，后端同步进行insert
    @Insert("insert into Item(item_name, price, shop_name, item_time, platform, image) values(#{item_name}, #{price}, #{shop_name}, #{item_time}, #{platform}, #{image})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void InsertNewItem(Item item);
}
