package drupal.ui.pages.add_content.equipment;

import drupal.enums.content.EquipmentCategory;
import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.enums.content.EquipmentCategory.ELECTRONICS_RADARS;
import static drupal.enums.content.EquipmentCategory.MILITARY_AIRCRAFT_BOMBERS;
import static drupal.enums.content.EquipmentCategory.ORDNANCE;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_EXISTING_SLIDESHOW_BUTTON;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_EXISTING_SLIDESHOW_CONTAINER_LIST;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_EXISTING_SLIDESHOW_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_NEW_SLIDESHOW_BUTTON;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_NEW_SLIDESHOW_CAPTION_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_NEW_SLIDESHOW_TITLE_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_SLIDESHOW_BUTTON;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_SPECIFICATIONS_BUTTON;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ATTACH_AN_IMAGE_BUTTON;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.CATEGORIES_CONTAINER_DROP_WEB_ELEMENT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.CATEGORIES_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.CREATE_NEW_SLIDESHOW_BUTTON;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.KEYWORDS_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.RELATED_VIDEOS_INPUT;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class DetailsModel extends AdministrationToolbar implements ISectionDataModel, AttachAnImage {

    // TODO java:S1192 String literals should not be duplicated
    // TODO create private static final member "{behavior: 'instant', block: 'center', inline: 'center'}"

    private final String thumbnailImageMediaName;
    private final boolean addNewSlideshow;
    private final String newSlideshowTitle;
    private final String newSlideshowCaption;
    private final boolean existingSlideshow;
    private final String addExistingSlideshow;
    private final List<String> relatedVideos;
    private final List<EquipmentCategory> equipmentCategories;
    private final List<String> keywords;

    public DetailsModel() {
        thumbnailImageMediaName = "Adobe Stock";
        addNewSlideshow = false;
        newSlideshowTitle = null;
        newSlideshowCaption = null;
        existingSlideshow = true;
        addExistingSlideshow = "Carrier";
        relatedVideos = null;
        equipmentCategories = List.of(ELECTRONICS_RADARS, MILITARY_AIRCRAFT_BOMBERS, ORDNANCE);
        keywords = null;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        uploadThumbNailImage();
        enterRelatedVideos();
        selectCategories();
        enterKeywords();
        slideshowSection();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    public DetailsModel addExistingSlideshow() {

        if (addExistingSlideshow != null) {
            ADD_SPECIFICATIONS_BUTTON.should(visible, appear)
                                     .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}");
            ADD_EXISTING_SLIDESHOW_BUTTON.should(appear, enabled)
                                         .click();
            ADD_EXISTING_SLIDESHOW_INPUT.should(appear, enabled)
                                        .sendKeys(addExistingSlideshow);
            ADD_EXISTING_SLIDESHOW_CONTAINER_LIST.first()
                                                 .should(appear, visible)
                                                 .click();
            ADD_SLIDESHOW_BUTTON.should(appear, enabled)
                                .click();
        }
        return this;
    }

    public DetailsModel addNewSlideshow() {
        if (newSlideshowTitle != null) {
            ADD_NEW_SLIDESHOW_BUTTON.should(appear, enabled)
                                    .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                                    .click();
            ADD_NEW_SLIDESHOW_TITLE_INPUT.should(appear, enabled)
                                         .setValue(newSlideshowTitle);
            ADD_NEW_SLIDESHOW_CAPTION_INPUT.should(appear, enabled)
                                           .setValue(newSlideshowCaption);
            CREATE_NEW_SLIDESHOW_BUTTON.should(appear, enabled)
                                       .click();
        }

        return this;
    }

    private DetailsModel enterRelatedVideos() {
        if (relatedVideos != null) {
            for (String relatedVideo : relatedVideos) {
                RELATED_VIDEOS_INPUT.should(appear, enabled)
                                    .scrollTo()
                                    .setValue(relatedVideo);
            }
        }
        return this;
    }

    private DetailsModel selectCategories() {
        if (equipmentCategories != null) {
            for (EquipmentCategory category : equipmentCategories) {
                CATEGORIES_INPUT.should(exist, appear)
                                .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                                .click();
                if (category != null) {
                    CATEGORIES_INPUT.should(appear, enabled)
                                    .setValue(category.getCategoryName());
                }

                if (CATEGORIES_CONTAINER_DROP_WEB_ELEMENT.should(appear, visible)
                                                         .exists()) {
                    CATEGORIES_AVAILABLE_LIST.first()
                                             .should(appear, exist)
                                             .click();
                }
            }
        }
        return this;
    }

    private DetailsModel enterKeywords() {

        if (keywords != null) {
            for (String keyword : keywords) {
                KEYWORDS_INPUT.should(appear, enabled)
                              .scrollTo()
                              .setValue(keyword);
            }
        }
        return this;
    }

    private DetailsModel uploadThumbNailImage() {

        ATTACH_AN_IMAGE_BUTTON.should(appear, visible, enabled)
                              .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                              .click();
        waitAjaxJQueryMet(120);
        uploadOrAttachExistingImage(new ImageUploadModel(thumbnailImageMediaName, null, null, true, false, null));

        return this;
    }

    private DetailsModel slideshowSection() {
        if (existingSlideshow) {
            addExistingSlideshow();
        } else if (addNewSlideshow) {
            addNewSlideshow();
        }
        return this;
    }

}
