package drupal.ui.pages.content;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NewMembersBuddyFinderPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/newmembers/buddy-finder";
    private static final ElementsCollection BUDDY_FINDER_REGISTRATION_LABELS_LIST = $$(".widget-content-container .row-wrapper label");

    public NewMembersBuddyFinderPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Buddy Finder page not loaded.");
        log.info("New Member Buddy Finder page loaded properly.");
    }

    public NewMembersBuddyFinderPage verifyNewMemberBuddyFinder(LoginRegFormFieldListModel loginRegFormModel) {
        List<String> expectedFieldsList = loginRegFormModel.getLoginFormFieldByNameList();
        List<String> actualFieldsList = BUDDY_FINDER_REGISTRATION_LABELS_LIST.stream()
                                                                             .map(SelenideElement::getText)
                                                                             .collect(Collectors.toList());
        if (!expectedFieldsList.equals(actualFieldsList)) {
            log.error(format("Fields are not displayed in order. Expected Order is: %s , expectedFieldsList is: %s", actualFieldsList, expectedFieldsList));
            throw new AssertionError(format("Fields are not displayed in order %s ", actualFieldsList));
        }
        return this;
    }
}