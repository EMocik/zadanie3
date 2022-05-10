package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Book> books;
    private String name;
    private String surname;

    public Author(AuthorRequest authorRequest){
        this.name = authorRequest.getName();
        this.surname = authorRequest.getSurname();
        this.books = new ArrayList<>();
    }

}
