package com.easydevs.news;

import java.util.List;

/**
 * Created by arekotto on 02/02/2017.
 */
public interface NewsService {
    void insertNews(News news);

    List<News> findAll();
}
