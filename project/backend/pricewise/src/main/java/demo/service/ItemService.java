package demo.service;

import demo.entity.Item;
import demo.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemService  {

    @Autowired
    private ItemMapper itemMapper;

    public void insertItem(Item item) {
        itemMapper.InsertNewItem(item);
    }
}
