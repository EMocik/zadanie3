package sk.stuba.fei.uim.oop.assignment3.book.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    Book findBookById(Long id);
}
