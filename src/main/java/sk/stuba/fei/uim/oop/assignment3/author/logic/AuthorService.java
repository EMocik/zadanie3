package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private IBookService bookService;


    @Override
    public List<Author> getAll() {return this.authorRepository.findAll();}

    @Override
    public Author create(AuthorRequest authorRequest){
        return this.authorRepository.save(new Author(authorRequest));
    }

    @Override
    public Author getById(Long id) throws NotFoundException {
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
        List<Book> books = bookService.getAll();
        for (Book book : books) {
            if (book.getAuthor().equals(author.getId())) {
                this.bookService.delete(book.getId());
            }
        }
        author.getBooks().clear();
        this.authorRepository.delete(author);
    }



}
