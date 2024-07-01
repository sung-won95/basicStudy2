package com.example.erd.controller.response;

import com.example.erd.dto.BookInfoDTO;
import com.example.erd.entity.Author;
import com.example.erd.entity.Book;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorInfoDTO {
    private Long id;
    private String name;

    private List<BookInfoDTO> books;

    public AuthorInfoDTO(Author author){
        this.id = author.getId();
        this.name = author.getName();
        List<BookInfoDTO> books = new ArrayList<>();

        for (Book book : author.getBooks()){
            books.add(BookInfoDTO.builder()
                            .id(book.getId())
                            .title(book.getTitle())
                    .build());
        }


        this.books = books;
    }

}
