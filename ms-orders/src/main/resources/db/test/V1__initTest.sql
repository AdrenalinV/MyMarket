DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS baskets;
DROP TABLE IF EXISTS baskets_items;
create table orders
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id    INTEGER NOT NULL,
    cost       FLOAT NOT NULL,
    address    VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);



create table order_items
(
    id                INTEGER PRIMARY KEY NOT NULL,
    order_id          INTEGER REFERENCES orders (id),
    product_id        INTEGER,
    title             VARCHAR(255),
    quantity          int,
    cost_per_product  FLOAT,
    cost              FLOAT,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

create table baskets
(
    id      UUID primary key,
    user_id INTEGER,
    cost    FLOAT
);

create table basket_items
(
    id                INTEGER PRIMARY KEY NOT NULL,
    cart_id           UUID references baskets (id),
    product_id        INTEGER,
    title             VARCHAR(255),
    quantity          int,
    cost_per_product  FLOAT,
    cost              FLOAT,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);
