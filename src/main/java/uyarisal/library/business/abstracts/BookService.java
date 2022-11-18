package uyarisal.library.business.abstracts;

import uyarisal.library.dtos.book.request.BookRequest;
import uyarisal.library.dtos.book.response.BookListResponse;
import uyarisal.library.dtos.book.response.BookResponse;
import uyarisal.library.entity.Book;

import java.util.List;

public interface BookService {

    List<BookListResponse> getAll();

    BookResponse getById(Long id);

    BookResponse add(BookRequest bookRequest);

    BookResponse update(Book book, Long id);

    void delete(Long id);
    


}
