CREATE TABLE IF NOT EXISTS products(
   id INT NOT NULL AUTO_INCREMENT,
   title VARCHAR(100) NOT NULL,
   description VARCHAR(255) NOT NULL,
   price FLOAT NOT NULL,
   created_at TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
   updated_at TIMESTAMP(3) NULL ON UPDATE CURRENT_TIMESTAMP(3),
   PRIMARY KEY ( id )
);

INSERT INTO products (title, description, price)
    VALUES ('Apple', 'This is an apple.', 0.25);
INSERT INTO products (title, description, price)
    VALUES ('Orange', 'This is an orange.', 0.30);
INSERT INTO products (title, description, price)
    VALUES ('Pear', 'This is a pear.', 0.16);
INSERT INTO products (title, description, price)
    VALUES ('Steak Filet', 'This is a steak filet.', 29.95);
INSERT INTO products (title, description, price)
    VALUES ('Zucchini', 'This is a zucchini.', 0.50);
INSERT INTO products (title, description, price)
    VALUES ('Banana', 'This is a bunch of bananas.', 0.69);
INSERT INTO products (title, description, price)
    VALUES ('Cottage Cheese', 'This is cottage cheese.', 2.95);
INSERT INTO products (title, description, price)
    VALUES ('Mushrooms', 'These are mushrooms.', 1.55);
INSERT INTO products (title, description, price)
    VALUES ('Cookies', 'These are cookies.', 4.99);
INSERT INTO products (title, description, price)
    VALUES ('Pepsi', 'This is a pack of Pepsi.', 4.95);
INSERT INTO products (title, description, price)
    VALUES ('Milk', 'This is a gallon of milk.', 0.25);
INSERT INTO products (title, description, price)
    VALUES ('Whiskey', 'This is a bottle of whiskey.', 39.95);
