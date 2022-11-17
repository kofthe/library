package uyarisal.library.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uyarisal.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
