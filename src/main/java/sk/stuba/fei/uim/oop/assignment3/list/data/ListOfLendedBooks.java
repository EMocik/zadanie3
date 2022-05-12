package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ListOfLendedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean lended;

    @OneToMany
    private List<Book> lendingList;

    public ListOfLendedBooks(){this.lendingList = new ArrayList<>();}
}
