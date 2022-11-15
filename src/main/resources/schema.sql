CREATE TABLE item
(
    item_id        BIGINT NOT NULL,
    item_name      VARCHAR(64),
    price          INT    NOT NULL,
    stock_quantity INT    NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (item_id)
);