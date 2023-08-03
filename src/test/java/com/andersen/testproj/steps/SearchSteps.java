package com.andersen.testproj.steps;

import io.cucumber.java.en.When;
import java.util.List;
import com.andersen.testproj.pages.SearchPage;
import org.junit.jupiter.api.Assertions;

public class SearchSteps {

    private final SearchPage searchPage;

    public SearchSteps() {
        searchPage = new SearchPage();
    }

    @When("User search {string} text in Search page")
    public void userSearchTextInSearchPage(String searchText) {
        searchPage.search(searchText);
    }

    @When("User click {int}th result at Search page")
    public void userScrollToResultByNumberSearchPage(int resultNumber) {
        Assertions.assertTrue(searchPage.isSearchResultPresent(), "There are no results at search page");
        List<String> productNames = searchPage.getDisplayedItems(resultNumber);
        Assertions.assertTrue(productNames.size() >= resultNumber,
                String.format("There are no %s products at search page", resultNumber));
        searchPage.clickListItem(productNames.get(resultNumber - 1));
    }
}
