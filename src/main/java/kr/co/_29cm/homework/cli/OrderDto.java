package kr.co._29cm.homework.cli;

import lombok.Getter;

@Getter
public class OrderDto {
    private final Long itemId;
    private final Integer itemCount;

    public OrderDto(Long itemId, Integer itemCount){
        this.itemId = itemId;
        this.itemCount = itemCount;
    }
}
