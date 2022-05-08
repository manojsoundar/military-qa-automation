package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_SLIDESHOW;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;


@Getter
@AllArgsConstructor
public class FlexibleContentSpaceSlideshowModel extends MasterPage implements ISectionDataModel, AttachAnImage, IFlexibleContentSpace {

    private static final String ADD_NEW_SLIDESHOW_BUTTON = "input[id*='%s-%s-subform-field-slideshow-actions-ief-add'][value='Add new slideshow']";
    private static final String ADD_NEW_SLIDESHOW_FIELD_SET_WRAPPER = "[id*='%s-%s-subform-field-slideshow-actions-ief-add]";
    private static final String CREATE_NEW_SLIDESHOW_BUTTON = "input[id*='%s-%s-subform-field-slideshow-form'][value='Create slideshow']";
    private static final String ADD_NEW_SLIDESHOW_TITLE_INPUT = "input.form-text[id*='%s-%s-subform-field-slideshow-form'][id*='title']";
    private static final String ADD_NEW_SLIDESHOW_CAPTION_INPUT = "input.form-text[id*='%s-%s-subform-field-slideshow-form'][id*='field-caption']";
    private static final String ADD_NEW_SLIDESHOW_ATTACH_AN_IMAGE_BUTTON = "input[id*='%s-%s-subform-field-slideshow-form'][value='Attach an image']";
    private static final String ADD_EXISTING_SLIDESHOW_INPUT = "input.form-text[id*='%s-%s-subform-field-slideshow-form']";
    private static final String ADD_EXISTING_SLIDESHOW_BUTTON = "input.button[id*='%s-%s-subform-field-slideshow-actions-ief-add-existing']";
    private static final String ADD_SLIDESHOW_BUTTON = "input.button[id*='%s-%s-subform-field-slideshow-form'][id*='actions-ief-reference-save']";
    private static final String EDIT_SLIDESHOW_BUTTON = "input[id*=-%s-%s-subform-field-slideshow][value=Edit]";
    private static final String UPDATE_SLIDESHOW_BUTTON = "input.button[id*='%s-%s-subform-field-slideshow-form-inline-entity-form'][value='Update slideshow']";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final boolean addNewSlideshow;
    private final String newSlideshowTitle;
    private final String newSlideshowCaption;
    private final ImageUploadModel imageData;
    private final boolean existingSlideshow;
    private final String addExistingSlideshow;

    public static ImageUploadModel getLandingPageWithComponentsData() {

        return new ImageUploadModel(" ", null, null, true, false, null);
    }

    public static ImageUploadModel getSidebarWithSlideshowData() {

        return new ImageUploadModel("Test Image", null, null, true, true, null);
    }

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_SLIDESHOW, position) - 1;
        slideshowSection();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        updateSlideshow();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceSlideshowModel slideshowSection() {
        if (existingSlideshow) {
            addExistingSlideshow(index);
        } else if (addNewSlideshow) {
            addNewSlideshow(index);
        }
        return this;
    }

    private FlexibleContentSpaceSlideshowModel addExistingSlideshow(int index) {

        if (addExistingSlideshow != null) {
            $(format(ADD_EXISTING_SLIDESHOW_BUTTON, position.name()
                                                            .toLowerCase(), index)).should(appear, enabled)
                                                                                   .click();
            waitAjaxJQueryMet(90);
            $(format(ADD_EXISTING_SLIDESHOW_INPUT, position.name()
                                                           .toLowerCase(), index)).should(appear, enabled)
                                                                                  .setValue(addExistingSlideshow);
            $(format(ADD_SLIDESHOW_BUTTON, position.name()
                                                   .toLowerCase(), index)).should(appear, enabled)
                                                                          .click();
        }
        return this;
    }

    private FlexibleContentSpaceSlideshowModel addNewSlideshow(int index) {
        if (newSlideshowTitle != null) {
            $(format(ADD_NEW_SLIDESHOW_BUTTON, position.name()
                                                       .toLowerCase(), index)).should(appear, enabled)
                                                                              .click();
            waitAjaxJQueryMet(300);
            $(format(ADD_NEW_SLIDESHOW_TITLE_INPUT, position.name()
                                                            .toLowerCase(), index)).should(appear, enabled)
                                                                                   .setValue(newSlideshowTitle);
            $(format(ADD_NEW_SLIDESHOW_CAPTION_INPUT, position.name()
                                                              .toLowerCase(), index)).should(appear, enabled)
                                                                                     .setValue(newSlideshowCaption);
            $(format(ADD_NEW_SLIDESHOW_ATTACH_AN_IMAGE_BUTTON, position.name()
                                                                       .toLowerCase(), index)).should(appear, enabled)
                                                                                              .click();
            waitAjaxJQueryMet(150);
            uploadOrAttachExistingImage(imageData);
            waitAjaxJQueryMet(150);
            $(format(CREATE_NEW_SLIDESHOW_BUTTON, position.name()
                                                          .toLowerCase(), index)).should(appear, enabled)
                                                                                 .click();
            waitAjaxJQueryMet(300);
        }
        return this;
    }

    private FlexibleContentSpaceSlideshowModel updateSlideshow() {
        if (newSlideshowTitle != null) {
            $(format(EDIT_SLIDESHOW_BUTTON, position.name()
                                                    .toLowerCase(), index)).should(appear, enabled)
                                                                           .click();
            waitAjaxJQueryMet(300);
            $(format(ADD_NEW_SLIDESHOW_TITLE_INPUT, position.name()
                                                            .toLowerCase(), index)).should(appear, enabled)
                                                                                   .setValue(newSlideshowTitle);
            $(format(ADD_NEW_SLIDESHOW_CAPTION_INPUT, position.name()
                                                              .toLowerCase(), index)).should(appear, enabled)
                                                                                     .setValue(newSlideshowCaption);
            $(format(UPDATE_SLIDESHOW_BUTTON, position.name()
                                                      .toLowerCase(), index)).should(appear, enabled)
                                                                             .click();
            waitAjaxJQueryMet(300);
        }
        return this;
    }
}


