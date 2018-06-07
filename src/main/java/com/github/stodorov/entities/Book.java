package com.github.stodorov.entities;

import com.github.stodorov.entities.enums.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private Long id;
    private String name;
    private int pageCount;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String description;
    @Column(columnDefinition = "bytea")
    private byte[] coverPicture;
    @Column(name="creation_date")
    private Date creationDate;

    public Book(String name, int pageCount, Author author, Genre genre, String description, byte[] coverPicture, Date creationDate) {
        this.name = name;
        this.pageCount = pageCount;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.coverPicture = coverPicture;
        this.creationDate = creationDate;
    }
}
