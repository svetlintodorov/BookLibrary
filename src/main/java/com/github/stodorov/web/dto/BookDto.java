package com.github.stodorov.web.dto;

import com.github.stodorov.entities.Book;
import com.github.stodorov.entities.enums.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class BookDto implements ModelConvertable<Book> {
    private String name;
    private int pageCount;
    private AuthorDto authorDto;
    private Genre genre;
    private String description;
    private byte[] coverPicture;

    public BookDto(String name, int pageCount, AuthorDto authorDto, Genre genre, String description, byte[] coverPicture) {
        this.name = name;
        this.pageCount = pageCount;
        this.authorDto = authorDto;
        this.genre = genre;
        this.description = description;
        this.coverPicture = coverPicture;
    }

    @Override
    public Book asModel() {
        Book book = new Book();
        book.setName(this.name);
        book.setPageCount(this.pageCount);
        book.setAuthor(this.authorDto.asModel());
        book.setGenre(genre);
        book.setDescription(this.description);
        book.setCoverPicture(this.coverPicture);

        return book;
    }
}
