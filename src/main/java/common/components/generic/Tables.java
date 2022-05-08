package common.components.generic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static java.util.Objects.requireNonNull;
import static mgs.qa.utils.CollectionUtils.compareTwoStringLists;
import static org.testng.Assert.assertEquals;

@Log4j2
public class Tables {

    private static final String DEFAULT_TABLE_ROOT_LOCATOR = ".//table";
    private static final String TABLE_RECORD_CELLS_LOCATOR = ".//td";
    private static final String TABLE_RECORD_CHECKBOX_LOCATOR = ".//input[@type='checkbox']";
    private static final String TABLE_RECORD_RADIO_LOCATOR = ".//input[@type='radio']";
    private static final String TABLE_RECORD_INPUT_LOCATOR = ".//input";
    private static final String TABLE_RECORD_BUTTON_LOCATOR = ".//button";
    private static final String TABLE_RECORD_SPAN_LOCATOR = ".//span[@data-ng-click]";
    private static final String TABLE_RECORD_LINK_LOCATOR = ".//a";
    private static final SelenideElement TABLE_ROWS_PER_PAGE_COUNT_SELECT = $x(".//nav//select[@id = 'pageSizeReviewers']");
    private final SelenideElement defaultTableRoot;
    private ElementsCollection tableHeaderCells;
    private ElementsCollection tableRecords;

    public Tables() {
        defaultTableRoot = $x(DEFAULT_TABLE_ROOT_LOCATOR);
        generateTableElements();
    }

    public Tables(final String id) {
        defaultTableRoot = $x(DEFAULT_TABLE_ROOT_LOCATOR + "[@id='" + id + "']");
        generateTableElements();
    }

    public Tables(SelenideElement tableLocator) {
        defaultTableRoot = tableLocator;
        generateTableElements();
    }

    private void generateTableElements() {
        var tableHeader = defaultTableRoot.$x(".//thead");
        tableHeaderCells = tableHeader.$$x(".//th");
        var tableBody = defaultTableRoot.$x(".//tbody");
        tableRecords = tableBody.$$x(".//tr");
    }

    private List<String> headerToList() {
        var header = tableHeaderCells.texts()
                                     .stream()
                                     .map(String::strip)
                                     .collect(Collectors.toList());
        log.debug("Table header: " + header);
        return header;
    }

    public void checkRecordsContainsText(String expectedText) {
        for (SelenideElement record : tableRecords) {
            record.should(text(expectedText));
        }
    }

    public SelenideElement getRecordRowElement(final String searchTerm) {
        return tableRecords.find(text(searchTerm));
    }

    public SelenideElement getRecordRowElement(final String... searchTerms) {
        ElementsCollection toReturn = tableRecords;
        for (String currentString : searchTerms) {
            toReturn = toReturn.filter(Condition.matchText(currentString));
        }

        return (toReturn.size() == 1) ? toReturn.first() : null;
    }

    public int getNumberOfRecords() {
        return tableRecords.size();
    }

    public ElementsCollection getRowCells(final int rowIndex) {
        return tableRecords.get(rowIndex)
                           .$$x(TABLE_RECORD_CELLS_LOCATOR);
    }

    public String getCellValue(final String headerName, final int rowIndex) {
        final List<String> strippedStrings = tableHeaderCells.texts()
                                                             .stream()
                                                             .map(String::strip)
                                                             .collect(Collectors.toList());

        return getCellValue(strippedStrings.indexOf(headerName), rowIndex);
    }

    private String getCellValue(final int columnIndex, final int rowIndex) {
        return getCellValue(getCellElement(columnIndex, rowIndex));
    }

    private String getCellValue(final SelenideElement element) {
        return element.getText()
                      .strip();
    }

    private SelenideElement getCellElement(final int columnIndex, final int rowIndex) {
        return tableRecords.get(rowIndex)
                           .$$x(TABLE_RECORD_CELLS_LOCATOR)
                           .get(columnIndex);
    }

    public SelenideElement getCellElement(final String headerName, final int rowIndex) {
        return getCellElement(getColumnIndex(headerToList(), headerName), rowIndex);
    }

    public SelenideElement getCellElement(final String headerName, final String searchTerm) {
        return getRecordRowElement(searchTerm).$$x(TABLE_RECORD_CELLS_LOCATOR)
                                              .get(getColumnIndex(headerToList(), headerName));
    }

    public String getCellValue(final String headerName, final String searchTerm) {
        return getCellValue(getRecordRowElement(searchTerm).$$x(TABLE_RECORD_CELLS_LOCATOR)
                                                           .get(getColumnIndex(headerToList(), headerName)));
    }

    private int getColumnIndex(final List<String> header, final String headerName) {
        return header.indexOf(headerName);
    }

    public int getColumnHeaderIndex(final String headerName) {
        return headerToList().indexOf(headerName);
    }

    public int getRowsCount() {
        try {
            return tableRecords.size();
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public void changePageCount(String count) {
        TABLE_ROWS_PER_PAGE_COUNT_SELECT.selectOption(count);
        // TODO adjust code to handle spinner if active
    }

    public void waitForRowsCount(final int expectedRowsCount) {
        tableRecords.should(size(expectedRowsCount));
    }

    public Tables waitForFirstRecordDisplayed() {
        tableRecords.first()
                    .should(appear);
        return this;
    }

    public Tables waitForRecordDisplayed(final int recordIndex) {
        tableRecords.get(recordIndex)
                    .should(appear);
        return this;
    }

    public Tables waitForFirstRecordNotDisplayed() {
        tableRecords.first()
                    .should(not(appear));
        return this;
    }

    public Tables verifyTableHeaders(final List<String> expectedHeadersList) {
        compareTwoStringLists(headerToList(), expectedHeadersList);
        log.info("Table header is correct.");
        return this;
    }

    public Tables clickRadio(final String headerName, final int rowIndex) {
        getCellElement(headerName, rowIndex).$x(TABLE_RECORD_RADIO_LOCATOR)
                                            .click();
        return this;
    }

    public Tables clickRadio(final int columnIndex, final int rowIndex) {
        getCellElement(columnIndex, rowIndex).$x(TABLE_RECORD_RADIO_LOCATOR)
                                             .click();
        return this;
    }

    public Tables clickCheckbox(final int columnIndex, final int rowIndex) {
        getCellElement(columnIndex, rowIndex).$x(TABLE_RECORD_CHECKBOX_LOCATOR)
                                             .click();
        return this;
    }

    public Tables clickCheckbox(final String headerName, final int rowIndex) {
        getCellElement(headerName, rowIndex).$x(TABLE_RECORD_CHECKBOX_LOCATOR)
                                            .click();
        return this;
    }

    public Tables clickButton(final String headerName, final int rowIndex) {
        getCellElement(headerName, rowIndex).$x(TABLE_RECORD_BUTTON_LOCATOR)
                                            .click();
        return this;
    }

    public Tables verifyButton(final String headerName, final int rowIndex) {
        getCellElement(headerName, rowIndex).$x(TABLE_RECORD_BUTTON_LOCATOR)
                                            .should(appear);
        return this;
    }

    public Tables verifyCheckbox(final int columnIndex, final int rowIndex, final boolean expectedValue) {
        final var currentValue = requireNonNull(getCellElement(columnIndex, rowIndex).$x(TABLE_RECORD_CHECKBOX_LOCATOR)
                                                                                     .getAttribute("class"))
                .contains("checked");
        assertEquals(expectedValue, currentValue, "Verify checkbox value");

        return this;
    }

    public Tables clickHeaderCheckbox(final String headerName) {
        getHeaderElement(headerName).$x(TABLE_RECORD_CHECKBOX_LOCATOR)
                                    .click();
        return this;
    }

    public Tables verifyHeaderCheckbox(final String headerName, final boolean expectedValue) {
        if (expectedValue) {
            getHeaderElement(headerName).$x(TABLE_RECORD_CHECKBOX_LOCATOR)
                                        .shouldHave(attribute("checked"));
        } else {
            getHeaderElement(headerName).$x(TABLE_RECORD_CHECKBOX_LOCATOR)
                                        .shouldNotHave(attribute("checked"));
        }

        return this;
    }

    public SelenideElement getHeaderElement(final String headerName) {
        return tableHeaderCells.get(getColumnIndex(headerToList(), headerName));
    }

    public Tables setInputValue(final String headerName, final String valueToBeSet) {
        getCellElement(getColumnIndex(headerToList(), headerName), 0).$x(TABLE_RECORD_INPUT_LOCATOR)
                                                                     .setValue(valueToBeSet);

        return this;
    }

    public Tables setInputValue(final String headerName, final String rowSearchTerm, final String valueToBeSet) {
        getInputElement(headerName, rowSearchTerm).setValue(valueToBeSet);

        return this;
    }

    public SelenideElement getInputElement(final String headerName, final String rowSearchTerm) {
        return getCellElement(headerName, rowSearchTerm).$x(TABLE_RECORD_INPUT_LOCATOR);
    }

    public Tables clickLink(final String headerName, final int rowIndex) {
        getCellElement(headerName, rowIndex).$x(TABLE_RECORD_LINK_LOCATOR)
                                            .click();
        return this;
    }

    public Tables clickSpan(final int columnIndex, final int rowIndex) {
        getCellElement(columnIndex, rowIndex).$x(TABLE_RECORD_SPAN_LOCATOR)
                                             .click();
        return this;
    }

}