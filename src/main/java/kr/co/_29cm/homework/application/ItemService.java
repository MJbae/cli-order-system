package kr.co._29cm.homework.application;

import kr.co._29cm.homework.infra.ItemRepository;
import kr.co._29cm.homework.domain.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository){
        this.repository = repository;
    }

    public List<Item> loadAll(){
        return repository.findAll();
    }

    public Item loadOne(Long id){
        return repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Item save(Item item){
        return repository.save(item);
    }
}
