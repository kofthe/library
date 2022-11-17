package uyarisal.library.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uyarisal.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
