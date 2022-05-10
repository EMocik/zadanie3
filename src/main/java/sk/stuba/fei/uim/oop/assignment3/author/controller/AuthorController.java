package sk.stuba.fei.uim.oop.assignment3.author.controller;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.author.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorResponse> getAllAuthors(){
        return authorService.getAllAuthors().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse createAuthor (@RequestBody AuthorRequest body){
        return new AuthorResponse(this.authorService.create(new Author(body)));
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable("id") Long id){
        return new AuthorResponse(this.authorService.getAuthor(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequest body) throws NotFoundException{
        return new AuthorResponse((authorService.updateAuthor(id, body)));
    }

    @DeleteMapping("/{id}")
    void deleteAuthorById(@PathVariable("id") Long id){
        this.authorService.deleteAuthor(id);
    }

}
