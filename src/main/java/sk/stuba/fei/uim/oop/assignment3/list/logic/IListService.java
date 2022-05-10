package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.LendingListUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListRequest;

import java.util.List;

//@Qualifier("myList")
public interface IListService {

    List<ListOfLendedBooks> getAll();

    ListOfLendedBooks create(ListRequest listRequest) throws NotFoundException;

    ListOfLendedBooks getById(long id) throws NotFoundException;

    ListOfLendedBooks update(long id, LendingListUpdateRequest lendingListUpdateRequest) throws NotFoundException;

    void delete(long id) throws NotFoundException;
}
