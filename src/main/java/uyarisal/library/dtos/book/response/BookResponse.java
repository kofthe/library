package uyarisal.library.dtos.book.response;

import lombok.Data;
import uyarisal.library.dtos.author.response.AuthorResponse;
import uyarisal.library.entity.Author;

import java.util.List;

@Data
public class BookResponse {

    private Long id;
    private String name;
    private int pageCount;
    private List<AuthorResponse> authorId;

}
//kullanıcı hesabı açıldığında password'u geri dönemezsiniz.
//nesneyi başka bir hale dönüştürmeye yarayacak.