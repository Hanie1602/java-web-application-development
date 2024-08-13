Qui tắc đặt tên package
- <<tên sv viết tắt>>.<<tên package>>

Xây dựng filter để làm điều phối chính và chứa trong package filter
- Ví dụ: khanhkt.filter

Các servlet chứa trong package controllers
-  Ví dụ: khanhkt.controller

Các code hỗ trợ chức năng đặt trong package utils
- Ví dụ: khanhkt.utils

DAO, DTO được mapping theo đúng chính sách tên bảng dưới DB và chứa trong package tên bảng viết thường
- Ví dụ: bảng trong DB có tên tbl_Student
- Tên package: khanhkt.tbl_Student
       - DAO : Tbl_Student_DAO
       - DTO : Tbl_Student_DTO
       - Các Errors theo qui tắt <<tên bảng>><tên chức năng>><<error>>
             : Tbl_StudentInsertError
             : Tbl_StudentUpdateError


Viết code phải xử lý lỗi cụ thể và ghi dùng log trong servlet, filter 
Tất cả các chức năng phải bắt lỗi khi cần thiết