package com.github.stodorov.controllers;

import com.github.stodorov.entities.Book;
import com.github.stodorov.entities.enums.Genre;
import com.github.stodorov.services.BookService;
import com.github.stodorov.web.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/api"})
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public Page<Book> getBooks(Pageable pageable) {
        log.info("Get all books: ");
        Page<Book> books = bookService.getBooks(pageable);

        return books;
    }

    @PostMapping({"/book"})
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDto bookDto, MultipartFile coverPicture) {
        log.info("Creating Book " + bookDto.getName());

        Book book = bookDto.asModel();
        try {
            book.setCoverPicture(coverPicture.getBytes());
        } catch(IOException e) {
            log.error(e.getMessage());
            return null;
        }
        bookService.save(book);

        log.info("Book " + bookDto.getName() + " was created!");

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping("/books/searchByCriteria")
    public ResponseEntity<List<Book>> findBooksByCriteria(String bookName, int minPageCount, int maxPageCount,
                                                          Genre genre, @Param("page") Integer page,
                                                          @Param("size") Integer size, String sort) {
        // TODO need change when implement UI to pass params
        List<Book> books = bookService.findBySearchCriteria(bookName, minPageCount, maxPageCount, genre);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }
}
