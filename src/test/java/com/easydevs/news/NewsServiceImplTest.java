package com.easydevs.news;

import com.mongodb.Mongo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by arekotto on 30/05/2017.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"/com/easydevs/applicationContextTest.xml"})
public class NewsServiceImplTest {

    @Autowired
    NewsService newsService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void cleanUpDb(){
        mongoTemplate.dropCollection(News.class);
        mongoTemplate.dropCollection("newsIdSequence");
    }

    @Test
    public void someTest() {
        newsService.findAll();
        assertEquals(3, 3);
    }

    @Test
    public void insertNewsTest() {
        News news = new News();

        String title = "title";
        String content = "content";
        String author = "author";

        news.setAuthor(author);
        news.setContent(content);
        news.setTitle(title);

        newsService.insertNews(news);

        List<News> newsList = newsService.findAll();
        News testNews = newsList.get(0);

        assertEquals(testNews.getAuthor(), author);
        assertEquals(testNews.getTitle(), title);
        assertEquals(testNews.getContent(), content);
    }
}