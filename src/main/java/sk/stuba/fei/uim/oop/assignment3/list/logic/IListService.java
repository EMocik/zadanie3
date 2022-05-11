package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.LendingListUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListRequest;

import java.util.List;

//@Qualifier("myList")
public interface IListService {

    List<ListOfLendedBooks> getAll();

    ListOfLendedBooks create();

    ListOfLendedBooks getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    ListOfLendedBooks addToList(long id, Book body) throws NotFoundException, IllegalOperationException;

    void lendTheList(long id) throws NotFoundException, IllegalOperationException;
}
