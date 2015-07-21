USE bookstore; #this is the database

DROP TABLE IF EXISTS topSeller;

CREATE TABLE topSeller
(
topSeller_id int unsigned primary key auto_increment NOT NULL, #unique id
author_id int unsigned NOT NULL,
book_id int unsigned NOT NULL,
ranking double (2,0) NOT NULL,
FOREIGN KEY (author_id) references authors (author_id),
FOREIGN KEY (book_id) references books (book_id)
)ENGINE = InnoDB;

INSERT INTO topSeller (author_id,book_id,ranking)
VALUES (0,1,8);
commit;

SELECT * FROM topSeller; #all fields from books