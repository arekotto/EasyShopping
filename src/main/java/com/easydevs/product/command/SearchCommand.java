package com.easydevs.product.command;

/**
 * Created by Arek on 24.01.2017.
 */
public class SearchCommand {
    private String searchedPhrase;
    private Integer searchCategory;

    public SearchCommand() {
    }

    public SearchCommand(String searchedPhrase) {
        this.searchedPhrase = searchedPhrase;
    }

    public String getSearchedPhrase() {
        return searchedPhrase;
    }

    public Integer getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(Integer searchCategory) {
        this.searchCategory = searchCategory;
    }

    public void setSearchedPhrase(String searchedPhrase) {
        this.searchedPhrase = searchedPhrase;
    }
}
