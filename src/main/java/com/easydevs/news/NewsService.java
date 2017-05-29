package com.easydevs.news;

import java.util.List;

/**
 * Created by arekotto on 02/02/2017.
 */
public interface NewsService {
    /**
     * Insert news.
     *
     * @param news the news
     */
    void insertNews(News news);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<News> findAll();
}
