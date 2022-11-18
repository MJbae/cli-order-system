package kr.co._29cm.homework.domain;

public class SoldOutException extends RuntimeException{
    public SoldOutException() {
        super("주문한 상품의 수가 재고량 보다 많습니다.");
    }
}