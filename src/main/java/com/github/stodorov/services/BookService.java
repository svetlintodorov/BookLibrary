package com.github.stodorov.services;

import com.github.stodorov.entities.Book;
import com.github.stodorov.entities.enums.Genre;
import com.github.stodorov.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> findByName(String bookName) {
        return bookRepository.findByName(bookName);
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public List<Book> findBySearchCriteria(String name, int min, int max, Genre genre) {
        return bookRepository.findByNameOrPageCountGreaterThanAndPageCountLessThanOrGenre(name, min, max, genre);
    }

    public void save(Book book){
        bookRepository.save(book);
    }
}
