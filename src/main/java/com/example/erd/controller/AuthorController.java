package com.example.erd.controller;

import com.example.erd.controller.request.BookCreateRequest;
import com.example.erd.controller.response.AuthorInfoDTO;
import com.example.erd.dto.BookInfoDTO;
import com.example.erd.entity.Author;
import com.example.erd.entity.Book;
import com.example.erd.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping("/{id}")
    public AuthorInfoDTO getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id).orElseThrow(EntityNotFoundException::new);

        return new AuthorInfoDTO(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/{id}/books")
    public BookInfoDTO createBook(@PathVariable(value = "id")Long id, @RequestBody BookCreateRequest bookCreateRequest){
        Book book = authorService.createBook(id,bookCreateRequest);

        return BookInfoDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
    }

    @DeleteMapping("/{id}/serial/{serial}")
    public void deleteBooks(@PathVariable(value = "id")Long id, @PathVariable(value = "serial")Integer serial){
        authorService.deleteBook(id,serial);
    }
}