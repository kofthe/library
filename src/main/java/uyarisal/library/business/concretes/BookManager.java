package uyarisal.library.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uyarisal.library.business.abstracts.BookService;
import uyarisal.library.dataAccess.BookRepository;
import uyarisal.library.entity.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookManager implements BookService {

    BookRepository bookRepository;

    @Autowired
    public BookManager(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        return null;
    }

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book, Long id) {
        Optional<Book> inDbBook = bookRepository.findById(id);
        if (inDbBook.isPresent()) {
            Book book1 = inDbBook.get();
            book1.setName(book.getName());
            book1.setPageCount(book.getPageCount());
            return bookRepository.save(book1);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
