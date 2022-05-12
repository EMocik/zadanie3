package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.BookIDRequest;

import java.util.List;

public interface IListService {

    List<ListOfLendedBooks> getAll();

    ListOfLendedBooks create();

    ListOfLendedBooks getById(Long id);

    void delete(Long id) throws NotFoundException;

    void lendTheList(Long id) throws IllegalOperationException;

    ListOfLendedBooks addToList(Long id, BookIDRequest bookIDRequest) throws NotFoundException, IllegalOperationException;

    void deleteFromList(Long id, BookIDRequest bookIDRequest) throws NotFoundException;

}
