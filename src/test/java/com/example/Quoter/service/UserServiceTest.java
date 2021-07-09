package com.example.Quoter.service;

import com.example.Quoter.domain.Role;
import com.example.Quoter.domain.User;
import com.example.Quoter.repos.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private MailSenderService mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    /***
     * Tests if a new user is added properly.
     */
    @Test
    public void addUser() {
        User user = new User();

        user.setEmail("danil-lomakin-02@mail.ru");

        boolean isUserCreated = this.userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getActivationCode());

        // can't understand the difference between these two asserts below.
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        Assert.assertTrue(user.getRoles().equals(Collections.singleton(Role.USER)));

        Mockito.verify(this.userRepo, Mockito.times(1)).save(user);
        Mockito.verify(this.mailSender, Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    /***
     * Tests if the method "addUser()" doesn't add the new user if he entered the username
     * that is busy by another user.
     */
    @Test
    public void addUserFailTest() {
        // Imitate a user with the username "Danil";
        // userRepo.findByUsername returns either null or a user's instance,
        // so to imitate that the user already exists in the database,
        // the "thenReturn()" method has to simply return a new user's object.
        Mockito.when(this.userRepo.findByUsername("Danil"))
                .thenReturn(new User());

        // create a new user with the same username.
        User user = new User();
        user.setUsername("Danil");

        boolean isUserCreated = this.userService.addUser(user);
        // we make sure that the user isn't added.
        Assert.assertFalse(isUserCreated);

        // we also make sure that no save() method have been executed and no emails have been sent.
        Mockito.verify(this.userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        Mockito.verify(this.mailSender, Mockito.times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    public void activateUser() {
        // imitate an activation code;
        User user = new User();
        user.setActivationCode("activation-code");

        Mockito.when(this.userRepo.findByActivationCode("activation-code"))
                .thenReturn(user);

        boolean isUserActivated = this.userService.activateUser("activation-code");

        // make sure the user has been activated.
        Assert.assertTrue(isUserActivated);
        // make sure the user's activation code has been reset to null,
        Assert.assertNull(user.getActivationCode());
        // make sure the user has been updated.
        Mockito.verify(this.userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = this.userService.activateUser("activation-code");

        // make sure the user hasn't been activated.
        Assert.assertFalse(isUserActivated);
        // make sure the user hasn't been saved in the database.
        Mockito.verify(this.userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }
}