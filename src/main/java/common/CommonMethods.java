package common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

@Log4j2
//TODO move to commons library
public class CommonMethods {

    public static void sortAndCompare(ElementsCollection elementsList) {
        List<String> beforeSort = elementsList.stream()
                                              .map(SelenideElement::getText)
                                              .collect(Collectors.toList());
        List<String> afterSort = elementsList.stream()
                                             .map(SelenideElement::getText)
                                             .sorted()
                                             .collect(Collectors.toList());
        Collections.reverse(afterSort);
        assertEquals(beforeSort, afterSort, "Not displayed Chronologically");
    }

    /**
     * @param currentDateFormat Current date format eg: "yyyy-MM-dd"
     * @param newDateFormat     New date format eg: "yyyy/MM/dd"
     * @param dateToBeConverted Date to be converted 2021-05-13
     * @return Converted date returned in String format
     */
    public static String convertDateFormat(String currentDateFormat, String newDateFormat, String dateToBeConverted) {
        if (dateToBeConverted == null) {
            dateToBeConverted = LocalDate.now()
                                         .toString();
        }
        LocalDate date = LocalDate.parse(dateToBeConverted, DateTimeFormatter.ofPattern(currentDateFormat, Locale.US));
        return date.format(DateTimeFormatter.ofPattern(newDateFormat));
    }

    //TODO remove this method, and call from DateTimeUtils once Jenkins build issue is resolved
    public static String timeStampFormat(String timeStampPattern) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(timeStampPattern);
        LocalDateTime dateTime = LocalDateTime.now();

        return dateTimeFormat.format(dateTime);
    }

    //TODO remove this method, and call from FileUtils once method is added there
    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return " ";
        }
    }

    public static void verifyGridDisplayInChronologicalOrder(ElementsCollection dateList, String dateFormat) {
        DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        List<Date> beforeDateSort = new ArrayList<>();
        for (SelenideElement element : dateList) {
            try {
                beforeDateSort.add(dateFormatter.parse(element.getText()));
            } catch (ParseException pe) {
                log.error(pe.getMessage());
            }
        }

        List<Date> afterDateSort = beforeDateSort.stream()
                                                 .sorted()
                                                 .collect(Collectors.toList());
        Collections.reverse(afterDateSort);
        assertEquals(beforeDateSort, afterDateSort, "Not Displayed Chronologically");
        log.info("Grid displayed in Chronological order");

    }

    /**
     * @param daysToAdd number of days to be added for current date
     * @return Converted endDate(current date + daysToAdd) returned in String format
     */
    public static String getFutureDate(String pattern, int daysToAdd) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime endDate = LocalDateTime.now()
                                             .plusDays(daysToAdd);

        return dateFormat.format(endDate);
    }

}