package demo.service;

import demo.entity.History;
import demo.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    // 1. 插入历史记录
    public void insertHistory(History history) {
        historyMapper.insertNewHistory(history);
    }

    // 2. 读取历史记录
    public List<History> getHistory(String account) {
        return historyMapper.findHistoryByAccount(account);
    }
}
