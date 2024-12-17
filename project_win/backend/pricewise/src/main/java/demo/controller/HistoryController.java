package demo.controller;

// 基本实现Restful API的包
import demo.utils.APIResponse;
import demo.entity.History;
import demo.service.HistoryService;
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
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/insert")
    public ResponseEntity<APIResponse> insert_history(@RequestParam String jwt_value, @RequestParam String input) {
        String account;
        try {
            account = JwtUtil.paraJWT2account(jwt_value);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("invalid token", 200));
        }
        LocalDateTime present_time = LocalDateTime.now();
        if (input.length() > 128)
            input = input.substring(0, 128);

        // 检查该记录是否已存在
        if (historyService.checkHistory(account, input) != null) {
            historyService.updateHistoryTime(present_time, account, input);
            return ResponseEntity.ok(new APIResponse("update successfully", 200));
        }

        History history = new History(0, account, input, present_time);
        historyService.insertHistory(history);
        return ResponseEntity.ok(new APIResponse("insert successfully", 200));
    }

    @GetMapping("/get")
    public ResponseEntity<APIResponse> get_history(@RequestParam String jwt_value) {
        String account;
        try {
            account = JwtUtil.paraJWT2account(jwt_value);
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse("invalid token", 200));
        }
        List<History> history_set = historyService.getHistory(account);
        history_set.sort(Comparator.comparing(History::getSearch_time).reversed());
        if (history_set.size() > 10)
            history_set = history_set.subList(0, 10);
        List<String> history_input = new ArrayList<>();
        for (History history : history_set) {
            history_input.add(history.getSearch_input());
        }
        return ResponseEntity.ok(new APIResponse("get successfully", 200, history_input));
    }

}
