DROP TABLE IF EXISTS information;

CREATE TABLE information (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seoul_price INT,
    gyeonggi_price INT,
    etc_price INT,
    phone_number VARCHAR(13)
);

INSERT INTO information(seoul_price, gyeonggi_price, etc_price, phone_number)
       VALUES (40000, 60000, 20000, '010-4656-5799');
       