package com.easydevs.user;

import com.easydevs.support.DbIdSequence;
import com.easydevs.user.model.StandardUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by arekotto on 29/05/2017.
 */

// These two lines have to be here in order to run the test in the test context.
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"/com/easydevs/applicationContextTest.xml"})
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * This method is launched before every test. It cleans up the test_db in order to ensure each test passes.
     * Tests are executed in a random order, so having a good clean up method is key for consistent passing of tests.
     */
    @Before
    public void cleanUpDb() {
        mongoTemplate.dropCollection(StandardUser.class);
        mongoTemplate.dropCollection("userIdSequence");

    }

    /**
     * This method checks if inserting and querying from db works as expected.
     */
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

    @Test
    public void removeTest() {
        StandardUser standardUser = (StandardUser) userService.createNewUser(UserType.STANDARD);
        long id = standardUser.getId();

        userService.removeUser(id);

        assertNull(userService.getUserById(id));
    }
}