package demo.mapper;

import org.apache.ibatis.annotations.*;

import demo.entity.Discount;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DiscountMapper {
    @Select("select * from Discount")
    List<Discount> findDiscount();

    @Select("select * from Discount where account = #{account}")
    List<Discount> findDiscountByAccount(@Param("account") String account);

    @Select ("select * from Discount where account = #{account} and item_name = #{item_name}")
    List<Discount> findDiscountByAccountandName(@Param("account") String account, @Param("item_name") String item_name);

    @Insert("insert into Discount(account, item_name, price, item_time, platform) values(#{account}, #{item_name}, #{price}, #{item_time}, #{platform})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertNewDiscount(Discount discount);

    @Delete("delete from Discount where account = #{account} and item_name = #{item_name}")
    void deleteDiscount(@Param("account") String account, @Param("item_name") String item_name);

    @Update("update Discount set price = #{price}, item_time = #{item_time} where item_name = #{item_name}")
    void updatePriceByName(@Param("price") Double price, @Param("item_time") LocalDateTime item_time, @Param("item_name") String item_name);
}
