package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book create(BookRequest bookRequest) throws NotFoundException{
        Book book = new Book(bookRequest);
        book = this.bookRepository.save(book);
        this.authorService.getById(bookRequest.getAuthor()).getBooks().add(book);
        return book;

    }


    @Override
    public Book getById(long id) throws NotFoundException {
        Book book = this.bookRepository.findBookById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }
    @Override
    public Book update(long id, BookUpdateRequest bookUpdateRequest) throws NotFoundException {
        Book book = this.getById(id);
        if (bookUpdateRequest.getName() != null) {
            book.setName(bookUpdateRequest.getName());
        }
        if (bookUpdateRequest.getDescription() != null) {
            book.setDescription(bookUpdateRequest.getDescription());
        }
        return this.bookRepository.save(book);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        Book book = this.bookRepository.findBookById(id);
        if(book != null){
            Author author = authorRepository.findAuthorById(book.getAuthor());
            if(author != null){
                List<Book> books = author.getBooks();
                books.remove(book);
                author.setBooks(books);
                authorRepository.save(author);
            }
        }
        this.bookRepository.delete(this.getById(id));
    }

    @Override
    public int getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int addAmount(Long id, int amount) throws NotFoundException {
        Book book = this.getById(id);
        book.setAmount(book.getAmount() + amount);
        this.bookRepository.save(book);
        return book.getAmount();
    }
}
