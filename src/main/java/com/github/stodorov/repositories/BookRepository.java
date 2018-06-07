package com.github.stodorov.repositories;

import com.github.stodorov.entities.Author;
import com.github.stodorov.entities.Book;
import com.github.stodorov.entities.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
    Optional<Book> findByName(String bookName);
    List<Book> findByNameOrPageCountGreaterThanAndPageCountLessThanOrGenre(String name, int min, int max, Genre genre);
}
