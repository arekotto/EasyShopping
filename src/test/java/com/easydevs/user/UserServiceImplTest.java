package com.easydevs.user;

import com.easydevs.user.model.StandardUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by arekotto on 29/05/2017.
 */
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void insertTest() {
        StandardUser standardUser = (StandardUser) userService.createNewUser(UserType.STANDARD);
        String email = "test@test.com";
        standardUser.setEmail(email);
        String city = "Krk";
        standardUser.setCity(city);
        String country = "Pl";
        standardUser.setCountry(country);
        String name = "Loool";
        standardUser.setName(name);
        userService.updateUser(standardUser);

        StandardUser testedUser = (StandardUser) userService.getUserByEmail(email);


        // asserting
        assertEquals(city, testedUser.getCity());
        assertEquals(country, testedUser.getCountry());
        assertEquals(name, testedUser.getName());
    }
}