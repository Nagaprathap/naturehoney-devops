CREATE DATABASE IF NOT EXISTS naturehoney CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'naturehoney'@'localhost' IDENTIFIED BY 'CHANGE_ME';
GRANT ALL PRIVILEGES ON naturehoney.* TO 'naturehoney'@'localhost';
FLUSH PRIVILEGES;
USE naturehoney;
CREATE TABLE IF NOT EXISTS products (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(160) NOT NULL,
 description TEXT,
 weight VARCHAR(80),
 price DECIMAL(10,2) NOT NULL,
 image VARCHAR(255) NOT NULL DEFAULT 'honey-placeholder.svg',
 active BOOLEAN NOT NULL DEFAULT TRUE,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO products(name,description,weight,price,image,active)
SELECT 'Wild Forest Honey','A rich, naturally sweet honey for your everyday use.','500 g',349.00,'honey-placeholder.svg',TRUE
WHERE NOT EXISTS (SELECT 1 FROM products);
