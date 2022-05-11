package sk.stuba.fei.uim.oop.assignment3.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest body) throws NotFoundException {
        return new ResponseEntity<>(new BookResponse(this.bookService.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        return new ResponseEntity<>(this.bookService.getAll().stream().map(BookResponse::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(new BookResponse(this.bookService.getById(id)), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") Long id, @RequestBody BookUpdateRequest body) throws NotFoundException {

        return new ResponseEntity<>(new BookResponse(this.bookService.update(id, body)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long id) throws NotFoundException {
        this.bookService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Amount> getAmount(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(new Amount(this.bookService.getAmount(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/amount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Amount> addAmount(@PathVariable("id") Long id, @RequestBody Amount body) throws NotFoundException {
        return new ResponseEntity<>(new Amount(this.bookService.addAmount(id, body.getAmount())), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/lendCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Amount> getlendCount(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(new Amount(this.bookService.getById(id).getLendCount()), HttpStatus.OK);
    }
}
