package drupal.ui.pages.components.slideshows;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditSlideshowPage extends AdministrationToolbar {

    protected static final SelenideElement SAVE_BUTTON = $("input#edit-submit").as("Save button");
    private static final String URL_REGEX = "/edit";


    public EditSlideshowPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "EditSlideshowPage not loaded");
        log.info("EditSlideshowPage loaded");
    }

    public EditSlideshowPage editAllSlideShow(List<ISectionDataModel> dataModel) {

        for (ISectionDataModel data : dataModel) {
            data.editData(EditSlideshowPage.class);
        }
        return this;
    }

    public SlideShowResultPage clickSaveButton() {
        AddSlideshowContentPage.SAVE_BUTTON.should(Condition.enabled, Condition.visible, Condition.appear)
                                           .click();
        return new SlideShowResultPage();
    }

}
