package sk.stuba.fei.uim.oop.assignment3.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{
    @Autowired
    private IAuthorRepository authorRepository;


    public AuthorService(IAuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    @Override
    public Author create(Author author){
        return this.authorRepository.save(author);
    }

    @Override
    public Author getAuthor(Long id) {
        return this.authorRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Author> getAllAuthors() {
//        return List<authorRepository
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Long id) {
        Author authorToDelete = this.authorRepository.findById(id).orElseThrow();
        this.authorRepository.delete(authorToDelete);
    }

    @Override
    public Author updateAuthor(Long id, AuthorRequest body) {
        return null;
    }

}
