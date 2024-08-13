create database SampleDB
use SampleDB
create table Items (
	ItemID varchar(15) not null,
	ItemName varchar(50) not null,
	Quantity int
);

insert Items (ItemID, ItemName, Quantity) values
('001', 'Coffee', 100),
('002', 'Milk', 200),
('003', 'Cake', 300);