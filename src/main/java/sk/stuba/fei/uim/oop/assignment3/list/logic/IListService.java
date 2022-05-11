package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.BookIDRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.LendingListUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListRequest;

import java.util.List;

//@Qualifier("myList")
public interface IListService {

    List<ListOfLendedBooks> getAll();

    ListOfLendedBooks create();

    ListOfLendedBooks getById(long id);

    void delete(long id) throws NotFoundException;

    void lendTheList(long id) throws NotFoundException, IllegalOperationException;

    ListOfLendedBooks addToList(long id, BookIDRequest bookIDRequest) throws NotFoundException, IllegalOperationException;

    void deleteFromList(long id, BookIDRequest bookIDRequest) throws NotFoundException;

}
