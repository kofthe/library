package uyarisal.library.dtos.book.response;

import lombok.Data;

@Data
public class BookListResponse {

    private Long id;
    private String name;
    private int pageCount;
    private Long authorId;
}
