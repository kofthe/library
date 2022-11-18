package uyarisal.library.business.concretes;

import org.springframework.stereotype.Service;
import uyarisal.library.business.abstracts.AuthorService;
import uyarisal.library.business.abstracts.BookService;
import uyarisal.library.core.utils.BookModel;
import uyarisal.library.dataAccess.AuthorRepository;
import uyarisal.library.dtos.author.request.AuthorRequest;
import uyarisal.library.dtos.author.response.AuthorListResponse;
import uyarisal.library.dtos.author.response.AuthorResponse;
import uyarisal.library.dtos.book.response.BookListResponse;
import uyarisal.library.entity.Author;
import uyarisal.library.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorManager implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorManager(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorListResponse> getAll() {

        return authorRepository.findAll().stream().map(author -> toAuthorListResponse(author)).collect(Collectors.toList());
    }

    @Override
    public AuthorResponse findById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(author1 -> toAuthorResponse(author1)).orElse(null);
    }

    //Author is present - if içerisinde yazmamız gerekmesin, kod kısa olsun diye orElse olarak kullandık.
    @Override
    public AuthorResponse add(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        return toAuthorResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponse update(Author author, Long id) {
        Optional<Author> inDbAuthor = authorRepository.findById(id);
        if (inDbAuthor.isPresent()) {
            Author author1 = inDbAuthor.get();
            author1.setName(author.getName());
            return toAuthorResponse(authorRepository.save(author1));
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public AuthorListResponse toAuthorListResponse(Author author) {
        AuthorListResponse authorListResponse = new AuthorListResponse();
        authorListResponse.setId(author.getId());
        authorListResponse.setName(author.getName());
        return authorListResponse;
    }

    public AuthorResponse toAuthorResponse(Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());

        return authorResponse;
    }

    public Author getAuthorById(Long id) {
       return  authorRepository.findById(id).orElse(null);
    }

    public List<BookListResponse> getAuthorBookList(Long id){
        Optional<Author> author = authorRepository.findById(id);
        List<BookListResponse> bookListResponseList = new ArrayList<>();
        if (author.isPresent()) {
             List<Book> bookList = author.get().getBookList();
             for (Book book : bookList) {
                 BookListResponse bookListResponse = BookModel.toBookListResponse(book);
                 bookListResponseList.add(bookListResponse);
             }
             return bookListResponseList;
        }
        return null;
    }
}
