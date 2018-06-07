package com.github.stodorov.services;

import com.github.stodorov.entities.Author;
import com.github.stodorov.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findByName(String name) {
        Optional<Author> author = authorRepository.findByName(name);
        if (!author.isPresent()) {
            return author.get();
        }

        return author.get();
    }

    public void save(Author author){
        authorRepository.save(author);
    }
}
