package sk.stuba.fei.uim.oop.assignment3.author.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    @Override
    Author save(Author a);

    @Override
    Optional<Author> findById(Long aLong);

    @Override
    List<Author> findAll();

    @Override
    void delete(Author author);

    Author findAuthorById(long id);
}
