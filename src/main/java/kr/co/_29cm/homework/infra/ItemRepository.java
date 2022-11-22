package kr.co._29cm.homework.infra;

import kr.co._29cm.homework.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select i from Item i where i.id = :id")
    Item findByIdForUpdate(@Param("id") Long id);
}
