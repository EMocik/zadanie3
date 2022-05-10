package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Book.class)
    private List<Book> books;
    private String name;
    private String surname;

    public Author(AuthorRequest authorRequest){
        this.name = authorRequest.getName();
        this.surname = authorRequest.getSurname();
        this.books = new ArrayList<>();
    }


    public Author() {
        this.books = new ArrayList<>();
    }
}
