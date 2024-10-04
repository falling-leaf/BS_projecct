package demo.controller;

import demo.entity.User;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. 登录功能
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String account, @RequestParam String password) {
        String res_password = userService.login(account);
        if (res_password == null)
            return ResponseEntity.ok("用户不存在");
        else {
            if (!res_password.equals(password))
                return ResponseEntity.ok("密码错误");
            else return ResponseEntity.ok("login successfully");
        }
    }

    // 2. 注册功能
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String account, @RequestParam String password, @RequestParam String email) {
        // 若存在账户则创建失败
        String res_account = userService.check_account(account);
        if (res_account != null)
            return ResponseEntity.ok("账户已存在");

        // 若存在邮箱则创建失败
        String res_email = userService.check_email(email);
        if (res_email != null)
            return ResponseEntity.ok("邮箱已被注册");

        // 检查账户/密码是否过短
        if (account.length() < 6)
            return ResponseEntity.ok("账户名过短");
        if (password.length() < 6)
            return ResponseEntity.ok("密码过短");
        // 检查邮箱格式
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches())
            return ResponseEntity.ok("邮箱格式有误");

        User user = new User(0, account, password, email);
        userService.register(user);
        return ResponseEntity.ok("register successfully");
    }
}