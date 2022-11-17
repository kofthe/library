package uyarisal.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyarisal.library.business.abstracts.AuthorService;
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
    public ResponseEntity<List<Author>> getAll() {
        List<Author> authorList = authorService.getAll();
        if (authorList.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id) {
        Author author = authorService.findById(id);
        if (Objects.nonNull(author)) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.delete(id);
    }

    @PostMapping
    public ResponseEntity<Author> add(@RequestBody Author author) {
        Author author1 = authorService.add(author);
        if (Objects.nonNull(author)) {
            return new ResponseEntity<>(author1, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@RequestBody Author author, @PathVariable Long id) {
        Author author1 = authorService.update(author, id);
        if (Objects.nonNull(author1)) {
            return new ResponseEntity<>(author1, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }



}