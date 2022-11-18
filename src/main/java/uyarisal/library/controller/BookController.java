package uyarisal.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyarisal.library.business.abstracts.BookService;
import uyarisal.library.dtos.book.request.BookRequest;
import uyarisal.library.dtos.book.response.BookListResponse;
import uyarisal.library.dtos.book.response.BookResponse;
import uyarisal.library.entity.Book;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    AuthorController authorController;
    BookService bookService;

    public BookController(AuthorController authorController, BookService bookService) {
        this.authorController = authorController;
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookListResponse>> getAll() {
        List<BookListResponse> bookList = bookService.getAll();
        if (bookList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        BookResponse bookResponse = bookService.getById(id);
        if (Objects.nonNull(bookResponse)) {
            return new ResponseEntity<>(bookResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addbook")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.add(bookRequest);
        if (Objects.nonNull(bookResponse)) {
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody Book book) {
        BookResponse bookResponse = bookService.update(book, id);
        if (Objects.nonNull(bookResponse)) {
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);

    }
}
