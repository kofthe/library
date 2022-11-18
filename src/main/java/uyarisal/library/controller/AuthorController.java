package uyarisal.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyarisal.library.business.abstracts.AuthorService;
import uyarisal.library.dtos.author.request.AuthorRequest;
import uyarisal.library.dtos.author.response.AuthorListResponse;
import uyarisal.library.dtos.author.response.AuthorResponse;
import uyarisal.library.dtos.book.response.BookListResponse;
import uyarisal.library.entity.Author;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AuthorListResponse>> getAll() {
        List<AuthorListResponse> authorListResponses = authorService.getAll();
        if (authorListResponses.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(authorListResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Long id) {
        AuthorResponse authorResponse = authorService.findById(id);
        if (Objects.nonNull(authorResponse)) {
            return new ResponseEntity<>(authorResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.delete(id);
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> add(@RequestBody AuthorRequest authorRequest) {
        AuthorResponse authorResponse = authorService.add(authorRequest);
        if (Objects.nonNull(authorResponse)) {
            return new ResponseEntity<>(authorResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@RequestBody Author author, @PathVariable Long id) {
        AuthorResponse authorResponse = authorService.update(author, id);
        if (Objects.nonNull(authorResponse)) {
            return new ResponseEntity<>(authorResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<BookListResponse>> getBookListAuthorId(@PathVariable Long id) {
        List<BookListResponse> bookListResponseList = authorService.getAuthorBookList(id);
        if (Objects.nonNull(bookListResponseList)) {
            return new ResponseEntity<>(bookListResponseList, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }


}
