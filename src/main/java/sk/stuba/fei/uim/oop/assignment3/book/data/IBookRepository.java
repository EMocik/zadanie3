package sk.stuba.fei.uim.oop.assignment3.book.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    @Override
    <S extends Book> S save(S s);

    @Override
    Optional<Book> findById(Long aLong);

    @Override
    List<Book> findAll();

    @Override
    void delete(Book book);
}
