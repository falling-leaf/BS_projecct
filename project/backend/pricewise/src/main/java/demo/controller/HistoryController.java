package demo.controller;

// 基本实现Restful API的包
import demo.utils.APIResponse;
import demo.entity.History;
import demo.service.HistoryService;
import demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Comparator;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/insert")
    public ResponseEntity<APIResponse> insert_history(@RequestParam String jwt_value, @RequestParam String input) {
        String account = JwtUtil.paraJWT2account(jwt_value);
        LocalDateTime present_time = LocalDateTime.now();
        input = input.substring(0, 128);
        History history = new History(0, account, input, present_time);
        historyService.insertHistory(history);
        return ResponseEntity.ok(new APIResponse("insert successfully", 200));
    }

    @GetMapping("/get")
    public ResponseEntity<APIResponse> get_history(@RequestParam String jwt_value) {
        String account = JwtUtil.paraJWT2account(jwt_value);
        List<History> history_set = historyService.getHistory(account);
        history_set.sort(Comparator.comparing(History::getSearch_time).reversed());
        history_set = history_set.subList(0, 10);
        return ResponseEntity.ok(new APIResponse("get successfully", 200, history_set));
    }

}
