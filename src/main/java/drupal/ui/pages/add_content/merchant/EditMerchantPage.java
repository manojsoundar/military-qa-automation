package drupal.ui.pages.add_content.merchant;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;
import www.ui.pages.discounts.DiscountsHomePage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditMerchantPage extends AdministrationToolbar implements AttachAnImage {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_MERCHANT_H_1_TAG = $("#block-pagetitle h1.page-title em").as("Edit Merchant Page H1 Tag");
    private static final SelenideElement SAVE_INPUT = $("input[id=edit-submit]").as("Save button");
    private static final SelenideElement UPLOADED_THUMBNAIL_IMAGE = $("img[alt^='Applebee']");

    public EditMerchantPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Merchant Page isn't loaded.");
        EDIT_MERCHANT_H_1_TAG.should(exist, appear)
                             .shouldHave(text("Edit Merchant"));
        log.info("Edit Merchant Page loaded properly.");
    }

    public DiscountsHomePage editMerchantPage(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditMerchantPage.class);
        }
        return clickSaveButton();
    }

    public DiscountsHomePage clickSaveButton() {
        SAVE_INPUT.should(exist, appear)
                  .scrollTo()
                  .click();
        return new DiscountsHomePage();
    }
}
