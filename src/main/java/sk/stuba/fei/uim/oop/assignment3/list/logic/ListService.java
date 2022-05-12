package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.IListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListOfLendedBooks;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.BookIDRequest;

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
    public ListOfLendedBooks getById(Long id){
        ListOfLendedBooks listOfLendedBooks = this.listRepository.findLendingListById(id);
        if (listOfLendedBooks == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return listOfLendedBooks;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        ListOfLendedBooks listOfLendedBooks = this.listRepository.findLendingListById(id);
        if(listOfLendedBooks == null){
            throw new NotFoundException();
        }
        listOfLendedBooks.getLendingList().forEach(book -> book.setLendCount(book.getLendCount()-1));
        this.listRepository.delete(listOfLendedBooks);
    }

    @Override
    public void lendTheList(Long id) throws IllegalOperationException {
        ListOfLendedBooks listOfLendedBooks = this.getUnlended(id);
        listOfLendedBooks.setLended(true);
        listOfLendedBooks.getLendingList().forEach(book -> book.setLendCount(book.getLendCount()+1));
        this.listRepository.save(listOfLendedBooks);
    }

    @Override
    public ListOfLendedBooks addToList(Long id, BookIDRequest bookIDRequest) throws NotFoundException {
        Book book = this.bookService.getById(bookIDRequest.getId());
        ListOfLendedBooks listOfLendedBooks = this.getById(id);
        if(listOfLendedBooks.isLended()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for (Book b :  listOfLendedBooks.getLendingList()) {
            if(b.getId().equals(bookIDRequest.getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        listOfLendedBooks.getLendingList().add(book);
        return this.listRepository.save(listOfLendedBooks);
    }

    @Override
    public void deleteFromList(Long id, BookIDRequest bookIDRequest) throws NotFoundException{
        Book book = this.bookService.getById(bookIDRequest.getId());
        if(book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ListOfLendedBooks listOfLendedBooks = this.getById(id);
        for (Book b :  listOfLendedBooks.getLendingList()) {
            if(b.getId().equals(bookIDRequest.getId())){
                listOfLendedBooks.getLendingList().remove(book);
                this.listRepository.save(listOfLendedBooks);
                throw new ResponseStatusException(HttpStatus.OK);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    private ListOfLendedBooks getUnlended(Long id) throws IllegalOperationException {
        ListOfLendedBooks listOfLendedBooks = this.getById(id);
        if (listOfLendedBooks.isLended()) {
            throw new IllegalOperationException();
        }
        return listOfLendedBooks;
    }
}
