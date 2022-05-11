package sk.stuba.fei.uim.oop.assignment3.list.web.body;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;

import java.util.List;

@Getter
public class ListResponse {
    private long id;
    private List<Book> lendingList;
    private boolean lended;


    public ListResponse(ListOfLendedBooks lendingList) {
        this.id = lendingList.getId();
        this.lendingList = lendingList.getLendingList();
        this.lended = lendingList.isLended();
    }
}
