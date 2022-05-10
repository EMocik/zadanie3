package sk.stuba.fei.uim.oop.assignment3.author;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;

import java.util.List;

public interface IAuthorService {
    Author create(Author author);
    Author getAuthor(Long id);
    List<Author> getAllAuthors();
    void deleteAuthor(Long id);
    Author updateAuthor(Long id, AuthorRequest body);
}
