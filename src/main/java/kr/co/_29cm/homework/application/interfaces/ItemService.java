package kr.co._29cm.homework.application.interfaces;

import kr.co._29cm.homework.domain.Item;

import java.util.List;

public interface ItemService<T extends Item> {

    List<T> loadAll();

    T loadOneBy(Long id);

    void save(T item);
}
