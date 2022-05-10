package sk.stuba.fei.uim.oop.assignment3.author;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;

@Repository
public interface IAuthorRepository extends CrudRepository<Author, Long> {
}
