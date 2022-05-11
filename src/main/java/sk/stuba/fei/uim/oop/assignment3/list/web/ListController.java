package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.logic.IListService;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private IListService listService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListResponse> createList(){
        return new ResponseEntity<>(new ListResponse(this.listService.create()), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ListResponse> getAllLists(){
        return this.listService.getAll().stream().map(ListResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse getListById(@PathVariable("id") long id) throws NotFoundException {
        return new ListResponse(this.listService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id) throws NotFoundException {
        this.listService.delete(id);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse addToList(@PathVariable("id") Long id, @RequestBody Book body) throws NotFoundException, IllegalOperationException {
        return new ListResponse(this.listService.addToList(id, body));
    }

    @GetMapping(value = "/{id}/lend", produces = MediaType.APPLICATION_JSON_VALUE)
    public void lendTheList(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException {
        this.listService.lendTheList(id);
    }
}
