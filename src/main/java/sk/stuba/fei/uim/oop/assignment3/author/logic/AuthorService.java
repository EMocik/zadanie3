package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.logic.BookService;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookRepository bookRepository;


    @Override
    public List<Author> getAll() {return this.authorRepository.findAll();}

    @Override
    public Author create(AuthorRequest authorRequest){
        return this.authorRepository.save(new Author(authorRequest));
    }

    @Override
    public Author getById(long id) throws NotFoundException {
        Author author = this.authorRepository.findAuthorById(id);
        if (author == null) {
            throw new NotFoundException();
        }
        return author;
    }

    @Override
    public Author updateAuthor(Long id, AuthorUpdateRequest authorUpdateRequest) throws NotFoundException {
        Author author = this.getById(id);
        if (authorUpdateRequest.getName() != null) {
            author.setName(authorUpdateRequest.getName());
        }
        if (authorUpdateRequest.getSurname() != null) {
            author.setSurname(authorUpdateRequest.getSurname());
        }
        return this.authorRepository.save(author);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Author author = this.getById(id);
        List<Book> books = bookRepository.findAll();
        for (int i = 0; i < books.size() ; i++) {
            if(books.get(i).getAuthor().equals(author.getId())){
                this.bookRepository.delete(books.get(i));
            }
        }
        author.getBooks().clear();
        this.authorRepository.delete(author);
    }



}
