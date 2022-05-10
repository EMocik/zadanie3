package sk.stuba.fei.uim.oop.assignment3.author;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorResponse {
    private final Long id;
    private String name;
    private String surname;
    private List<Long> books;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.books = new ArrayList<>();

    }

//    private List<Long> getBooksIds(){
//        for(Book book){
//
//        }
//    }
}
