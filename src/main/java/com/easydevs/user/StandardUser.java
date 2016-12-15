package com.easydevs.user;

/**
 * Created by ibm on 2016-12-15.
 */
public class StandardUser implements User{

    private Integer id;
    private String name;
    private String login;
    private String password;

    public StandardUser(Integer id,
                        String name,
                        String login,
                        String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLogin() {
        return this.login;
    }
}
