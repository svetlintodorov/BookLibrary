package com.github.stodorov.repository;

import com.github.stodorov.entities.*;
import com.github.stodorov.entities.enums.Genre;
import com.github.stodorov.repositories.BookRepository;
import com.github.stodorov.specification.BookSpecifications;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    private static final String ROLE_NAME1 = "USER";
    private static final String ROLE_NAME2 = "ACTUATOR";

    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    private static final String AUTHOR_NAME = "Agatha Christie";
    private static final String AUTHOR_DESCRIPTION = "More description..";

    private static final String BOOK_NAME = "The Clocks";
    private static final int BOOK_PAGE_COUNT = 364;
    private static final String BOOK_DESCRIPTION = "Book description here";
    private static byte[] COVER_PICTURE = null;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByAuthor() {
        List<Role> roles = buildListOfRoles();
        User user = buildUserObject();
        entityManager.persist(user);

        Author author = buildAuthorObject();
        entityManager.persist(author);

        Book book = buildBookObject(author);
        entityManager.persist(book);

        List<Book> foundedBooks = bookRepository.findByAuthor(author);

        assertNotNull(foundedBooks);
        assertEquals(book.getId(), foundedBooks.get(0).getId());
    }

    @Test
    public void testCreateBook() {
        List<Role> roles = buildListOfRoles();
        User user = buildUserObject();
        entityManager.persist(user);

        Author author = buildAuthorObject();
        entityManager.persist(author);

        Book book = buildBookObject(author);
        entityManager.persist(book);

        Optional<Book> foundedBook = bookRepository.findByName(book.getName());

        assertNotNull(foundedBook);
        assertEquals(book.getId(), foundedBook.get().getId());
    }

    @Test
    public void testSearchByCriteria() throws IOException {
        // TODO make changes
        // Path path = Paths.get("C:/Users/svetli/Desktop/bookLib/image.jpg");
        // COVER_PICTURE = Files.readAllBytes(path);

        User user = buildUserObject();
        entityManager.persist(user);

        Author author = buildAuthorObject();
        entityManager.persist(author);

        Book book = buildBookObject(author);
        entityManager.persist(book);

        Book book1 = buildBookObject1(author);
        entityManager.persist(book1);
        Book book2 = buildBookObject2(author);
        entityManager.persist(book2);
        Book book3 = buildBookObject3(author);
        entityManager.persist(book3);
        Book book4 = buildBookObject4(author);
        entityManager.persist(book4);

        List<Book> books = bookRepository.findByNameOrPageCountGreaterThanAndPageCountLessThanOrGenre("Lion King", 200, 350, Genre.BIOGRAPHY);
        assertNotNull(books);
        assertEquals(books.size(), 3);
    }

    @Test
    public void testFindAll() {
        List<Role> roles = buildListOfRoles();
        User user = buildUserObject();
        entityManager.persist(user);

        Author author = buildAuthorObject();
        entityManager.persist(author);

        Book book = buildBookObject(author);
        entityManager.persist(book);

        Book book1 = buildBookObject1(author);
        entityManager.persist(book1);
        Book book2 = buildBookObject2(author);
        entityManager.persist(book2);
        Book book3 = buildBookObject3(author);
        entityManager.persist(book3);
        Book book4 = buildBookObject4(author);
        entityManager.persist(book4);

        Pageable pageable = BookSpecifications.paginate(1, Sort.by("creationDate").descending());

        Page<Book> books = bookRepository.findAll(pageable);
        assertNotNull(books);
        assertEquals(books.getTotalPages(), 3);
        assertEquals(Sort.Direction.DESC, pageable.getSort().getOrderFor("creationDate").getDirection());
    }

    private List<Role> buildListOfRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ROLE_NAME1));
        roles.add(new Role(ROLE_NAME2));

        return roles;
    }

    private User buildUserObject() {
        return new User(USERNAME, PASSWORD, buildListOfRoles());
    }

    private Book buildBookObject(Author author) {
        return new Book(BOOK_NAME, BOOK_PAGE_COUNT, author, Genre.COMEDY, BOOK_DESCRIPTION, COVER_PICTURE, new Date());
    }

    private Book buildBookObject1(Author author) {
        return new Book("Prizes", 220, author, Genre.HORROR, "Description", null, new Date());
    }

    private Book buildBookObject2(Author author) {
        return new Book("The Class", 350, author, Genre.HORROR, "Description", null, new Date());
    }

    private Book buildBookObject3(Author author) {
        return new Book("Lion King", 298, author, Genre.COMEDY, "Description", null, new Date());
    }

    private Book buildBookObject4(Author author) {
        return new Book("A Bug's life", 330, author, Genre.BIOGRAPHY, "Description", null, new Date());
    }

    private Author buildAuthorObject() {
        return new Author(AUTHOR_NAME, AUTHOR_DESCRIPTION);
    }
}
