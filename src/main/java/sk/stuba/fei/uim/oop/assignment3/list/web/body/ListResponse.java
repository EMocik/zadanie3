package sk.stuba.fei.uim.oop.assignment3.list.web.body;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;

import java.util.List;

@Getter
public class ListResponse {
    private Long id;
    private boolean lended;
    private List<Book> lendingList;

    public ListResponse(ListOfLendedBooks lendingList) {
        this.id = lendingList.getId();
        this.lended = lendingList.isLended();
        this.lendingList = lendingList.getLendingList();
    }
}
