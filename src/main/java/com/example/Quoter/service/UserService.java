package com.example.Quoter.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.Quoter.domain.Role;
import com.example.Quoter.domain.User;
import com.example.Quoter.repos.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSenderService mailSender;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDB = this.userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        // save the user into the database if there is no users with the same username.
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        this.userRepo.save(user);

        this.sendMessage(user);
        return true;
    }

    public boolean activateUser(String code) {
        User user = this.userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        this.userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    public void saveUser(Long userId, String username, Map<String, String> form) {
        User user = this.userRepo.findById(userId).get();
        
        user.setUsername(username);
        
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        
        user.getRoles().clear();
        
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        
        this.userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                                 (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);
            
            // set a new activation code if the user's new email isn't empty.
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        this.userRepo.save(user);

        // send the new activation code by email if the user's new email isn't empty.
        if (isEmailChanged) {
            this.sendMessage(user);
        }
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s \n" +
                            "Welcome to Quoter. Please, visit the next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }
}
