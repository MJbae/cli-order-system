package kr.co._29cm.homework.application.interfaces;

import kr.co._29cm.homework.application.ProductService;
import kr.co._29cm.homework.domain.Item;

import java.util.List;

/**
 * 상품에 대한 비니지스 로직을 처리한다
 * <p>
 * All Known Implementing Classes:
 * @see ProductService
 * </p>
 */
public interface ItemService<T extends Item> {

    List<T> loadAll();

    T loadOneBy(Long id);

    void save(T item);
}
