CREATE TABLE USER(
id INT PRIMARY KEY AUTO_INCREMENT,
usernmae VARCHAR(20) NOT NULL,
PASSWORD VARCHAR(20) NOT NULL,
sex VARCHAR(2) NOT NULL,
hobbys VARCHAR(10),
phone VARCHAR(15),
email VARCHAR(30),
addrs VARCHAR(50),
flag BOOLEAN DEFAULT '1'
);

CREATE TABLE goodsinfo(
id INT PRIMARY KEY AUTO_INCREMENT,
goodsInfo_name VARCHAR(20) NOT NULL,
goodsInfo_pic VARCHAR(30) NOT NULL,
goodsInfo_price INT NOT NULL,
goodsInfo_description VARCHAR(50),
goods_stock INT NOT NULL,
flag BOOLEAN DEFAULT '1'
);

