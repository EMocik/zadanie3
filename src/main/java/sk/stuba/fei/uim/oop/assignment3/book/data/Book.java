package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int pages;
    private int amount;
    private int lendCount;
    private Long author;

    public Book(BookRequest bookRequest) {
        this.name = bookRequest.getName();
        this.description = bookRequest.getDescription();
        this.pages = bookRequest.getPages();
        this.amount = bookRequest.getAmount();
        this.lendCount = bookRequest.getLendCount();
        this.author = bookRequest.getAuthor();
    }
}
