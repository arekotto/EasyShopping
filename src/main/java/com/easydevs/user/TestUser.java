package com.easydevs.user;

/**
 * Created by arekotto on 09/12/2016.
 */
public class TestUser implements User {

    private Integer id;

    public TestUser(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void seteId(Integer id) {
        this.id = id;
    }

    // for testing
    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                '}';
    }
}
