package demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;

import demo.entity.User;

@Mapper
public interface UserMapper{

    // 1. 登录接口
    @Select("select password from User where account = #{account}")
    String findPasswordByAccount(@Param("account") String account);

    // 2. 注册接口
    @Select("select account from User where account = #{account}")
    String findAccountByAccount(@Param("account") String account);

    @Select("select email from User where email = #{email}")
    String findEmailByEmail(@Param("email") String email);


    @Insert("insert into User(account, password, email) values(#{account}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertNewUser(User user);
}
