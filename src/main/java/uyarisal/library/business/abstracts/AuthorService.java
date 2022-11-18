package uyarisal.library.business.abstracts;

import uyarisal.library.dtos.author.request.AuthorRequest;
import uyarisal.library.dtos.author.response.AuthorListResponse;
import uyarisal.library.dtos.author.response.AuthorResponse;
import uyarisal.library.dtos.book.response.BookListResponse;
import uyarisal.library.entity.Author;

import java.util.List;

public interface AuthorService {

    List<AuthorListResponse> getAll();

    AuthorResponse findById(Long id);

    AuthorResponse add(AuthorRequest authorRequest);

    AuthorResponse update(Author author, Long id);

    void delete(Long id);

    AuthorResponse toAuthorResponse(Author author);

    AuthorListResponse toAuthorListResponse(Author author);

    Author getAuthorById(Long id);

    List<BookListResponse> getAuthorBookList(Long id);


}
