USE bookstore; #this is the database

CREATE TABLE books
(
book_id int unsigned Primary key AUTO_INCREMENT NOT NULL, #unique id
title varchar(255) NOT NULL,
author_id int unsigned not null,
price double(10,2) NOT NULL,
foreign key (author_id) references authors(author_id))
engine = InnoDB;

insert into books(title, author_id, price) 
values ('Some Book', 0, 10.99); 
select * from books;