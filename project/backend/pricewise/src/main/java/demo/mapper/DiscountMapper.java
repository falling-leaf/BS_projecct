package demo.mapper;

import org.apache.ibatis.annotations.*;

import demo.entity.Discount;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DiscountMapper {
    @Select("select * from Discount where account = #{account}")
    List<Discount> findDiscountByAccount(@Param("account") String account);

    @Insert("insert into Discount(account, item_name, price, item_time) values(#{account}, #{item_name}, #{price}, #{item_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertNewDiscount(Discount discount);

    @Delete("delete from Discount where account = #{account} and item_name = #{item_name}")
    void deleteDiscount(@Param("account") String account, @Param("item_name") String item_name);
}
