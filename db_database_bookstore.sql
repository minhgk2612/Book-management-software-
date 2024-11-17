create schema db_book_store;
use db_book_store;

create table books (
    book_code varchar(50) Primary key,
    book_title varchar(255) not null,
    author varchar(255) not null,
    publication_year int,
    genre varchar(50),
    publisher varchar(50),
    selling_price int,
    page_number int
);