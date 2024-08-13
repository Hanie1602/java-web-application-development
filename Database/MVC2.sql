CREATE DATABASE text

Use text

-- Create the tables
CREATE TABLE Registration (
    username VARCHAR(20) NOT NULL PRIMARY KEY,
    password VARCHAR(30) NOT NULL,
    lastname NVARCHAR(100) NOT NULL,
    isAdmin BIT NOT NULL
);

CREATE TABLE tbl_Product (
id varchar(8) PRIMARY KEY,
name nvarchar(50),
description nvarchar(200),
unitprice float,
quantity int,
status bit
);

CREATE TABLE tOrder (
    id varchar(4) PRIMARY KEY,
    date date,
    name nvarchar(80),
    total float
);

CREATE TABLE OrderDetail (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(50),
    price FLOAT,
    quantity INT,
    total FLOAT,
    productid varchar(8),
    tOrderid varchar(4),
    FOREIGN KEY (productid) REFERENCES tbl_Product(id),
    FOREIGN KEY (tOrderid) REFERENCES tOrder(id)
);


-- Sample data for demonstration purposes
INSERT INTO Registration (username, password, lastname, isAdmin) VALUES
('hutrud', '123456', 'Hu Truc', 0),
('IA1301', '123456', 'Class IA1301', 0),
('khanh', 'kieu123', 'Khanh Kieu', 1),
('khanh@Spring', '123456', 'Spring annotation', 0),
('khanhSpring', '123456', 'khanh', 0),
('khanhSpringa', '123456', 'dddscaaaa', 0),
('duonglnt', 'se171916', 'Class SE1847', 0);

INSERT INTO tbl_Product (id, name, description, unitprice, quantity, status)
VALUES 
('SKU001', N'Ao thun', N'Ao thun mau den size M', 15.99, 100, 1),
('SKU002', N'Quan jeans', N'Quan jeans mau hong size 32', 29.99, 50, 1),
('SKU003', N'Giay the thao', N'Giay the thao nam mau trang size 9', 49.99, 30, 1),
('SKU004', N'Tui xach', N'Tui xach nu mau nau', 39.99, 20, 1);

INSERT INTO tOrder (id, date, name, total)
VALUES 
('ORD1', '2024-03-13', N'Order 1', 120.50),
('ORD2', '2024-03-14', N'Order 2', 210.75),
('ORD3', '2024-03-15', N'Order 3', 55.25);


INSERT INTO OrderDetail (name, price, quantity, total, productid, tOrderid)
VALUES 
(N'Ao thun', 15.99, 2, 31.98, 'SKU001', 'ORD1'),
(N'Quan jeans', 29.99, 1, 29.99, 'SKU002', 'ORD1'),
(N'Giay the thao', 49.99, 1, 49.99, 'SKU003', 'ORD2'),
(N'Tui xach', 39.99, 1, 39.99, 'SKU004', 'ORD2');