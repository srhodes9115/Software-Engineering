show tables;
explain books;
INSERT INTO books (title,author,price )
VALUES ("The Grapes of Wrath", "John Steinbeck", 12.99);
INSERT INTO books (title,author,price)
VALUES ("Nineteen Eighty-Four", "George Orwell", 8.99),
("The Wind-Up Bird Chronicle", "Haruki Murakami", 7.99);


SELECT * FROM books; #all fields from books
SELECT * FROM books WHERE id=2;
SELECT * FROM books WHERE title = "Nineteen Eighty-Four";
SELECT * FROM books WHERE price < 10 and price > 5;
SELECT title, author FROM books;

#select * from books where author IN <'Shannon Rhodes','s money','rhodesy'>;

SELECT * FROM books WHERE author LIKE '%eor%';

SELECT * FROM books ORDER BY price;

SELECT * FROM books LIMIT 1 OFFSET 2;
select * from books limit 2,1;

SET SQL_SAFE_UPDATES=0;
update books set
title = 'Miracle on 39th Street',
author = 'Mike Lupica'
WHERE price = '7.99';

show tables;
explain books;

DELETE FROM books WHERE price = '12.99';

#select TOP  from books;

#DELETE FROM books; -- deletes entire table

#title = getRequestString("title");

select * from books where author like 'j%'; #part of the word
select * from books where author not like 'j%'; #not part of word
select * from books; #total table
select * from books where author like '_ike'; #meant to produce other that starts with any character and continues with ike
select * from books where author like '[JM]%'; #supposed to produce author that start with j or m

select * from books where author in ('John Steinbeck');
select * from books where price between 10 and 14; 
select * from books where title between 't' and 'u';
select * from books where title in ('Grapes of Wrath');

alter table books add publishdate date;
select * from books;

