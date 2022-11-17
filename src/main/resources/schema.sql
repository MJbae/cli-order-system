CREATE TABLE item
(
    item_id        BIGINT NOT NULL,
    item_name      VARCHAR(64),
    price          INT    NOT NULL,
    stock_quantity INT    NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (item_id)
);

CREATE TABLE `order`
(
    order_id        BIGINT NOT NULL,
    price           INT    NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (order_id)
);

CREATE TABLE order_item
(
    order_item_id   BIGINT    NOT NULL,
    order_id        BIGINT    NOT NULL,
    item_id         BIGINT    NOT NULL,
    count           INT       NOT NULL,
    CONSTRAINT pk_order_item PRIMARY KEY (order_item_id),
    foreign key (order_id) references `order`(order_id),
    foreign key (item_id) references item(item_id)
);
