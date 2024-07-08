package com.example.erd.entity;

import com.example.erd.controller.request.BookCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Book> books = new ArrayList<>();

    public Book createBook(BookCreateRequest bookCreateRequest){
        Book book = Book.builder()
                .title(bookCreateRequest.getTitle())
                .author(this)
                .build();

        this.books.add(book);
        return book;
    }

    public void deleteBookBySerial(int serial){
        this.books.remove(serial);
    }
}