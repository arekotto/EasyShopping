package com.easydevs.product.command;

/**
 * Created by Arek on 24.01.2017.
 */
public class SearchCommand {
    private String searchedPhrase;

    public SearchCommand() {
    }

    public SearchCommand(String searchedPhrase) {
        this.searchedPhrase = searchedPhrase;
    }

    public String getSearchedPhrase() {
        return searchedPhrase;
    }

    public void setSearchedPhrase(String searchedPhrase) {
        this.searchedPhrase = searchedPhrase;
    }
}
