package uyarisal.library.business.abstracts;

import uyarisal.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getById(Long id);

    Book add(Book book);

    Book update(Book book, Long id);

    void delete(Long id);


}
