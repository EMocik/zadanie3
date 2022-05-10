package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListResponse> createList(@RequestBody ListRequest body) throws NotFoundException {
        return new ResponseEntity<>(new ListResponse(this.listService.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ListResponse>> getAllLists(){
        return new ResponseEntity<>(this.listService.getAll().stream().map(ListResponse::new).collect(Collectors.toList()), HttpStatus.OK);
    }
}
