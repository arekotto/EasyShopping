package com.easydevs.Content.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by arekotto on 08/12/2016.
 */
public class UserService {

    @Autowired
    @Qualifier("livestatsMongoTemplate")
    private MongoTemplate mongoTemplate;

}
