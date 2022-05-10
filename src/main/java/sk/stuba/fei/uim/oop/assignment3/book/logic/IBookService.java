package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();

    Book create(BookRequest bookRequest) throws NotFoundException;

    Book getById(long id) throws NotFoundException;

    Book update(long id, BookUpdateRequest request) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    int getAmount(long id) throws NotFoundException;

    int addAmount(Long id, int amount) throws NotFoundException;
}
