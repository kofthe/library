package uyarisal.library.dtos.author.response;

import lombok.Data;
import uyarisal.library.dtos.book.response.BookListResponse;

import java.util.List;
@Data
public class AuthorResponse {

    private Long id;
    private String name;
    private List<BookListResponse> bookListResponseList;

}
