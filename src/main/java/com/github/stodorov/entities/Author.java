package com.github.stodorov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="authors")
public class Author implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="author_id")
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public Author(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
