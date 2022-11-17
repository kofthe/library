package uyarisal.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyarisal.library.business.abstracts.BookService;
import uyarisal.library.entity.Book;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> bookList = bookService.getAll();
        if (bookList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        if (Objects.nonNull(book)) {
            return new ResponseEntity<>(book,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addbook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book book1 = bookService.add(book);
        if (Objects.nonNull(book1)) {
            return new ResponseEntity<>(book1, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book book1 = bookService.update(book, id);
        if (Objects.nonNull(book1)) {
            return new ResponseEntity<>(book1, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);

    }
}
