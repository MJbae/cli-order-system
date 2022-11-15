package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository){
        this.repository = repository;
    }

    public List<Item> loadAll(){
        return repository.findAll();
    }
}
