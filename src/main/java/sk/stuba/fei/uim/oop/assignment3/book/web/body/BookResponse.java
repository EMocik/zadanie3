package sk.stuba.fei.uim.oop.assignment3.book.web.body;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private int pages;
    private double amount;
    private int lendCount;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.pages = book.getPages();
        this.amount = book.getAmount();
        this.lendCount = book.getLendCount();
    }
}
