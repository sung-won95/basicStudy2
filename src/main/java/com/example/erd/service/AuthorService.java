package com.example.erd.service;

import com.example.erd.controller.request.BookCreateRequest;
import com.example.erd.entity.Author;
import com.example.erd.entity.Book;
import com.example.erd.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Book createBook(Long id, BookCreateRequest bookCreateRequest){
        Author findAuthor = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Book book = findAuthor.createBook(bookCreateRequest);

        authorRepository.save(findAuthor);

        return book;
    }

    public void deleteBook(Long authorId, Integer serial){
        Author findAuthor = authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);

        findAuthor.deleteBookBySerial(serial);

        authorRepository.save(findAuthor);
    }
}
