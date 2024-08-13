CREATE DATABASE PracticalTest

Use PracticalTest

 CREATE TABLE tbl_Kite (
	id VARCHAR(10) PRIMARY KEY,
	name NVARCHAR(20) NOT NULL,
	size NVARCHAR(5) NOT NULL,
	color NVARCHAR(20) NOT NULL,
	level NVARCHAR(20) NOT NULL,
	outline INT NOT NULL,
	status BIT,
	quantity INT NOT NULL,
	price FLOAT NOT NULL
 );


  CREATE TABLE tbl_User (
	userId VARCHAR(20) PRIMARY KEY,
	password INT NOT NULL,
	fullName NVARCHAR(50) NOT NULL,
	role BIT
  );


  INSERT INTO tbl_Kite (id, name, size, color, level, outline, status, quantity, price) VALUES 
  ('001', 'Dieu nang tien ca', 'nho', 'hong', 'nguoi moi choi', 3, 1, 32, 25.05),
  ('002', 'Dieu ca map', 'trung', 'xanh', 'nguoi choi pro', 4, 1, 45, 39.99),
  ('003', 'Dieu phuong hoang', 'vang', 'hong', 'nguoi choi lau nam', 4, 1, 19, 19.20),
  ('004', 'Dieu ca heo', 'to', 'den', 'nguoi moi choi', 3, 1, 20, 51.33);

  INSERT INTO tbl_User (userId, password, fullName, role) VALUES 
  ('hutrud', '123456', 'Hu Truc', 0),
('IA1301', '123456', 'Class IA1301', 0),
('khanh', '11321', 'Khanh Kieu', 1),
('khanh@Spring', '123456', 'Spring annotation', 0),
('khanhSpring', '123456', 'khanh', 0),
('khanhSpringa', '4315', 'dddscaaaa', 0),
('duonglnt', '171916', 'Class SE1847', 0);