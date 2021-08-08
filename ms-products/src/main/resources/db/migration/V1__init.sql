DROP TABLE IF EXISTS products;

CREATE TABLE products (id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
title VARCHAR(255),
cost FLOAT NOT NULL,
created_at   timestamp default current_timestamp,
updated_at   timestamp default current_timestamp);

INSERT INTO products (title, cost) VALUES
('Product_1',125.23),
('Product_2',256.32),
('Product_3',1258.23),
('Product_4',125.36),
('Product_5',256.52);
