package uyarisal.library.business.abstracts;

import uyarisal.library.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author findById(Long id);

    Author add(Author author);

    Author update(Author author, Long id);

    void delete(Long id);

}
