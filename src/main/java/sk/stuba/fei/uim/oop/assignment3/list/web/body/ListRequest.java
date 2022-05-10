package sk.stuba.fei.uim.oop.assignment3.list.web.body;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookResponse;

import java.util.List;

@Getter
@Setter
public class ListRequest {
    private Long id;
    private boolean lended;
    private List<Book> lendingList;
}
