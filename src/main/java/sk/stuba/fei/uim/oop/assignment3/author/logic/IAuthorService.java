package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface IAuthorService {
    List<Author> getAll();

    Author create(AuthorRequest authorRequest);

    Author getById(long id) throws NotFoundException;

    Author updateAuthor(Long id, AuthorUpdateRequest authorUpdateRequest) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

}
