package org.mycompany.mywebapp;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mycompany.mywebapp.user.User;
import org.mycompany.mywebapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("b4@gmail.com");
        user.setFirstName("Boaz");
        user.setLastName("Gori");
        user.setPassword("Abutwalib12@#");

      User savedUser =  repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user : users) {
            System.out.println(user);

        }
    }
    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("newpassword");
        repo.save(user);

        User updatedUser= repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("newpassword");
//        System.out.println(u);
    }
    @Test
    public void testGet(){
        Integer userId = 4;
        Optional<User> optionalUser = repo.findById(userId);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());

    }
    @Test
    public void testDelete(){
        Integer userId = 6;
        repo.deleteById(userId);
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user : users) {
            System.out.println(user);

        }



    }



}
