package demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from User where account = #{account}")
    User findUserByAccount(@Param("account") String account);

    @Select("select email from User where email = #{email}")
    String findEmailByEmail(@Param("email") String email);


    @Insert("insert into User(account, password, email) values(#{account}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertNewUser(User user);

    // 3. 重置密码
    @Select("select * from User where email = #{email}")
    User findUserByEmail(@Param("email") String email);

    @Update("update User set password = #{password} where account = #{account}")
    void UpdatePasswordByAccount(@Param("account") String account, @Param("password") String password);
}
