package demo.service;

import demo.entity.User;
import demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 1. 登录服务
    public String login(String account) {
        return userMapper.findPasswordByAccount(account);
    }

    // 2. 注册服务
    public String check_account(String account) {
        return userMapper.findAccountByAccount(account);
    }

    public User find_user_by_account(String account) {
        return userMapper.findUserByAccount(account);
    }

    public String check_email(String email) {
        return userMapper.findEmailByEmail(email);
    }

    public void register(User user) {
        userMapper.insertNewUser(user);
    }

    // 3. 重置密码
    public User check_user_by_email(String email) {
        return userMapper.findUserByEmail(email);
    }

    public void update_password_by_account(String account, String password) {
        userMapper.UpdatePasswordByAccount(account, password);
    }
}
