CREATE TABLE IF NOT EXISTS products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    price TEXT NOT NULL,
    brand TEXT NOT NULL,
    description TEXT,
    image_path TEXT
);

DELETE FROM products;

INSERT INTO products (title, price, brand, description, image_path) VALUES
('4DFWD PULSE SHOES', '$160.00', 'Adidas', 'This product is excluded from all promotional discounts and offers.', '../images/img1.png'),
('FORUM MID SHOES', '$100.00', 'Adidas', 'This product is excluded from all promotional discounts and offers.', '../images/img2.png'),
('SUPERNOVA SHOES', '$150.00', 'Adidas', 'NMD City Stock 2', '../images/img3.png'),
('Adidas', '$160.00', 'Adidas', 'NMD City Stock 2', '../images/img4.png'),
('Adidas', '$120.00', 'Adidas', 'NMD City Stock 2', '../images/img5.png'),
('4DFWD PULSE SHOES', '$160.00', 'Adidas', 'This product is excluded from all promotional discounts and offers.', '../images/img6.png'),
('4DFWD PULSE SHOES', '$160.00', 'Adidas', 'This product is excluded from all promotional discounts and offers.', '../images/img1.png'),
('FORUM MID SHOES', '$100.00', 'Adidas', 'This product is excluded from all promotional discounts and offers.', '../images/img2.png');
