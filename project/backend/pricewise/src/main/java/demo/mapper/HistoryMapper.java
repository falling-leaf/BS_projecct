package demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;

import demo.entity.History;
import java.util.List;

@Mapper
public interface HistoryMapper {
    @Select("select search_input, search_time from History where account = #{account}")
    List<History> findHistoryByAccount(@Param("account") String account);

    @Insert("insert into History(account, search_input, search_time) values(#{account}, #{search_input}, #{search_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertNewHistory(History history);
}
