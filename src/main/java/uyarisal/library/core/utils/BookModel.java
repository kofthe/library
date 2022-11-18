package uyarisal.library.core.utils;

import uyarisal.library.dtos.book.response.BookListResponse;
import uyarisal.library.dtos.book.response.BookResponse;
import uyarisal.library.entity.Book;

public class BookModel {

    public static BookListResponse toBookListResponse(Book book) {
        BookListResponse bookListResponse = new BookListResponse();
        bookListResponse.setId(book.getId());
        bookListResponse.setName(book.getName());
        bookListResponse.setPageCount(book.getPageCount());
        return bookListResponse;
    }

    public static BookResponse toBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setPageCount(book.getPageCount());
        return bookResponse;
    }
}
