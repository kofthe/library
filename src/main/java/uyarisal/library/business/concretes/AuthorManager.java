package uyarisal.library.business.concretes;

import org.springframework.stereotype.Service;
import uyarisal.library.business.abstracts.AuthorService;
import uyarisal.library.dataAccess.AuthorRepository;
import uyarisal.library.entity.Author;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorManager implements AuthorService {

    AuthorRepository authorRepository;

    public AuthorManager(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    //Author is present - if içerisinde yazmamız gerekmesin, kod kısa olsun diye orElse olarak kullandık.
    @Override
    public Author add(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author, Long id) {
        Optional<Author> inDbAuthor = authorRepository.findById(id);
        if (inDbAuthor.isPresent()) {
            Author author1 = inDbAuthor.get();
            author1.setName(author.getName());
            return authorRepository.save(author1);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
