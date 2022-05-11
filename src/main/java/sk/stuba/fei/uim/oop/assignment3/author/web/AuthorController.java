package sk.stuba.fei.uim.oop.assignment3.author.web;


import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorResponse> createAuthor (@RequestBody AuthorRequest body){
        return new ResponseEntity<>(new AuthorResponse(this.authorService.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorResponse>> getAllAuthors(){
        return new ResponseEntity<>(this.authorService.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse getAuthorById(@PathVariable("id") Long id) throws NotFoundException {
        return new AuthorResponse(this.authorService.getById(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorUpdateRequest body) throws NotFoundException{
        return new ResponseEntity<>(new AuthorResponse(this.authorService.updateAuthor(id, body)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAuthorById(@PathVariable("id") Long id) throws NotFoundException{
        this.authorService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
    }

}
