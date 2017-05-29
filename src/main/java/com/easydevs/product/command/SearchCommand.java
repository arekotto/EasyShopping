package com.easydevs.product.command;

/**
 * Created by Arek on 24.01.2017.
 */
public class SearchCommand {
    private String searchedPhrase;
    private Integer searchCategory;

    /**
     * Instantiates a new Search command.
     */
    public SearchCommand() {
    }

    /**
     * Instantiates a new Search command.
     *
     * @param searchedPhrase the searched phrase
     */
    public SearchCommand(String searchedPhrase) {
        this.searchedPhrase = searchedPhrase;
    }

    /**
     * Gets searched phrase.
     *
     * @return the searched phrase
     */
    public String getSearchedPhrase() {
        return searchedPhrase;
    }

    /**
     * Gets search category.
     *
     * @return the search category
     */
    public Integer getSearchCategory() {
        return searchCategory;
    }

    /**
     * Sets search category.
     *
     * @param searchCategory the search category
     */
    public void setSearchCategory(Integer searchCategory) {
        this.searchCategory = searchCategory;
    }

    /**
     * Sets searched phrase.
     *
     * @param searchedPhrase the searched phrase
     */
    public void setSearchedPhrase(String searchedPhrase) {
        this.searchedPhrase = searchedPhrase;
    }
}
