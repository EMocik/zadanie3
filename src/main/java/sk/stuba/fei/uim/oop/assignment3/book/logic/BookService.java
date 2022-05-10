package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book create(BookRequest bookRequest) {
        return this.bookRepository.save(new Book(bookRequest));
    }

    @Override
    public Book update(long id, BookUpdateRequest request) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(long id) throws NotFoundException {

    }

    @Override
    public long getAmount(long id) throws NotFoundException {
        return 0;
    }
}
