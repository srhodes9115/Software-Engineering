USE bookstore; #this is the database

CREATE TABLE authors
(
author_id int unsigned PRIMARY KEY AUTO_INCREMENT NOT NULL, #unique id
firstname varchar(255) NOT NULL,
lastname varchar(255) NOT NULL,
genre varchar(255) NOT NULL,
country varchar(255) NOT NULL
)ENGINE = InnoDB;

insert into authors (firstname, lastname, genre, country) 
values ('John', 'Smith', 'abc', 'USA'); 
commit;
select * from authors;