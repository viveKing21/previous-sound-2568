package com.ballot_box;

import java.time.LocalDate;

import com.ballot_box.entities.Profile;
import com.ballot_box.entities.User;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.services.UserService;
import com.ballot_box.services.UserServiceImp;

public class Test {
    public static void main(String[] args) throws SomethingWentWrongException {
        Profile profile = new Profile("Vivek", "Sharma", "test@gmail.com", LocalDate.now(), "Whats going on?");
        User user = new User("test@123", "test@123", profile);
        UserService userService = new UserServiceImp();

        userService.addUser(user);
    }
}
