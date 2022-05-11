package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.IListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.LendingListUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListRequest;

import java.util.List;

@Service
public class ListService implements IListService{
    @Autowired
    private IListRepository listRepository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<ListOfLendedBooks> getAll() {
        return this.listRepository.findAll();
    }

    @Override
    public ListOfLendedBooks create() {return this.listRepository.save(new ListOfLendedBooks());}

    @Override
    public ListOfLendedBooks getById(long id) throws NotFoundException {
        ListOfLendedBooks listOfLendedBooks = this.listRepository.findLendingListById(id);
        if (listOfLendedBooks == null) {
            throw new NotFoundException();
        }
        return listOfLendedBooks;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.listRepository.delete(this.getById(id));
    }

    @Override
    public void lendTheList(long id) throws NotFoundException, IllegalOperationException {
        ListOfLendedBooks listOfLendedBooks = this.getUnlended(id);
        listOfLendedBooks.setLended(true);
        this.listRepository.save(listOfLendedBooks);
    }

    @Override
    public ListOfLendedBooks addToList(long id, Book body) throws NotFoundException, IllegalOperationException {
        ListOfLendedBooks listOfLendedBooks = this.getUnlended(id);


        return null;
    }

    private ListOfLendedBooks getUnlended(long id) throws NotFoundException, IllegalOperationException {
        ListOfLendedBooks listOfLendedBooks = this.getById(id);
        if (listOfLendedBooks.isLended()) {
            throw new IllegalOperationException();
        }
        return listOfLendedBooks;
    }
}
