package uyarisal.library.business.concretes;


import org.springframework.stereotype.Service;
import uyarisal.library.business.abstracts.AuthorService;
import uyarisal.library.business.abstracts.BookService;
import uyarisal.library.core.utils.BookModel;
import uyarisal.library.dataAccess.BookRepository;
import uyarisal.library.dtos.book.request.BookRequest;
import uyarisal.library.dtos.book.response.BookListResponse;
import uyarisal.library.dtos.book.response.BookResponse;
import uyarisal.library.entity.Author;
import uyarisal.library.entity.Book;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookManager implements BookService {

    BookRepository bookRepository;
    AuthorService authorService;

    public BookManager(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<BookListResponse> getAll() {
        List<Book> bookList= bookRepository.findAll();

        return bookList.stream().map(BookModel :: toBookListResponse).collect(Collectors.toList());
    }

    @Override
    public BookResponse getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(BookModel::toBookResponse).orElse(null);
    }

    @Override
    public BookResponse add(BookRequest bookRequest) {

        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setPageCount(bookRequest.getPageCount());
        Author author = authorService.getAuthorById(bookRequest.getAuthorId());
        if (Objects.nonNull(author)) {
            book.setAuthor(author);
        }

        return BookModel.toBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse update(Book book, Long id) {
        Optional<Book> inDbBook = bookRepository.findById(id);
        if (inDbBook.isPresent()) {
            Book book1 = inDbBook.get();
            book1.setName(book.getName());
            book1.setPageCount(book.getPageCount());
            book1.setAuthor(authorService.getAuthorById(book.getAuthor().getId()));
            return BookModel.toBookResponse(bookRepository.save(book1));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
