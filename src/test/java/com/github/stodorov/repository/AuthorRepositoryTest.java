package com.github.stodorov.repository;

import com.github.stodorov.entities.Author;
import com.github.stodorov.entities.Role;
import com.github.stodorov.entities.User;
import com.github.stodorov.repositories.AuthorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {

    private static final String ROLE_NAME1 = "USER";
    private static final String ROLE_NAME2 = "ACTUATOR";

    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    private static final String AUTHOR_NAME = "Agatha Christie";
    private static final String AUTHOR_DESCRIPTION = "More description..";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testFindByName() {
        List<Role> roles = buildListOfRoles();
        User user = buildUserObject();
        entityManager.persist(user);

        Author author = buildAuthorObject();
        entityManager.persist(author);

        Optional<Author> foundAuthor = authorRepository.findByName(AUTHOR_NAME);
        assertEquals(AUTHOR_NAME, foundAuthor.get().getName());
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

    private Author buildAuthorObject() {
        return new Author(AUTHOR_NAME, AUTHOR_DESCRIPTION);
    }
}
