package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
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
    private IListService listService;

    @Override
    public List<ListOfLendedBooks> getAll() {
        return this.listRepository.findAll();
    }

    @Override
    public ListOfLendedBooks create(ListRequest listRequest) throws NotFoundException {
        ListOfLendedBooks listOfLendedBooks = new ListOfLendedBooks(listRequest);
        listOfLendedBooks = this.listRepository.save(listOfLendedBooks);
        this.listService.getById(listRequest.getId());
        return listOfLendedBooks;
    }

    @Override
    public ListOfLendedBooks getById(long id) throws NotFoundException {
        return null;
    }

    @Override
    public ListOfLendedBooks update(long id, LendingListUpdateRequest lendingListUpdateRequest) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(long id) throws NotFoundException {

    }
}
