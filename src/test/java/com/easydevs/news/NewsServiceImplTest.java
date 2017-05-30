package com.easydevs.news;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by arekotto on 30/05/2017.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"/com/easydevs/applicationContextTest.xml"})
public class NewsServiceImplTest {

    @Autowired
    NewsService newsService;

    @Before
    public void cleanUpDb(){
        // TODO clean up db
    }

    @Test
    public void someTest() {
        newsService.findAll();
        assertEquals(3, 3);
    }
}