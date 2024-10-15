package demo.controller;

// 基本实现Restful API的包
import demo.utils.APIResponse;
import demo.entity.History;
import demo.service.HistoryService;
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
    public ResponseEntity<APIResponse> insert_history(@RequestParam String account, @RequestParam String input) {
        LocalDateTime present_time = LocalDateTime.now();
        input = input.substring(0, 128);
        History history = new History(0, account, input, present_time);
        historyService.insertHistory(history);
        return ResponseEntity.ok(new APIResponse("insert successfully", 200, null));
    }

    @GetMapping("/get")
    public ResponseEntity<APIResponse> get_history(@RequestParam String account) {
        List<History> history_set = historyService.getHistory(account);
        history_set.sort(Comparator.comparing(History::getSearch_time).reversed());
        return ResponseEntity.ok(new APIResponse("get successfully", 200, history_set));
    }

}
