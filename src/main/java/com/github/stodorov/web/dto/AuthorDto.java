package com.github.stodorov.web.dto;

import com.github.stodorov.entities.Author;
import com.github.stodorov.entities.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AuthorDto implements  ModelConvertable<Author> {
    private String name;
    private String description;
    private Set<BookDto> booksDto = new HashSet<>();

    public AuthorDto(String name, String description, Set<BookDto> booksDto) {
        this.name = name;
        this.description = description;
        this.booksDto = booksDto;
    }

    @Override
    public Author asModel() {
        Author author = new Author();
        author.setName(this.name);
        author.setDescription(this.description);
        Set<BookDto> bookDtos = this.booksDto;
        Set<Book> books = new HashSet<>();
        for (BookDto dto: bookDtos) {
            books.add(dto.asModel());
        }
        author.setBooks(books);;
        return author;
    }
}
