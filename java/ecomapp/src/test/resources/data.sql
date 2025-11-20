-- this data will be used by tests

INSERT INTO categories (category_name, description, image_url) VALUES
    ('Fruit', 'Here are some fruits.', 'https://picsum.photos/200'),
    ('Vegetable', 'Veggies!', 'https://picsum.photos/200'),
    ('Dairy', 'moo!', 'https://picsum.photos/200');

INSERT INTO products (name, category_id, description, price, image_url) VALUES
    ('Pear', 1, 'Better than an Apple', 0.45, 'https://picsum.photos/200'),
    ('Squash', 2, 'Better than an egg plant', 0.12, 'https://picsum.photos/200'),
    ('Carrot', 2, 'For rabbits', 0.23, 'https://picsum.photos/200');
