package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import mgs.qa.utils.fileutils.FileUtils;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static common.CommonMethods.getFileExtension;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_AUDIO;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

// TODO intelliJ check usage callSuper=false
@Data
@AllArgsConstructor
@Log4j2
public class FlexibleContentSpaceAudioModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_AUDIO_TITLE_INPUT = "input[id*='%s-%s-subform-field-title']";
    private static final String FLEXIBLE_CONTENT_SPACE_ADD_NEW_MEDIA_ITEM_BUTTON = "input[id*='%s-%s-subform-field-audio-actions'][value*='new media']";
    private static final String FLEXIBLE_CONTENT_SPACE_NEW_MEDIA_NAME_INPUT = "//input[contains(@id,'%s-%s-subform-field-audio-form')and contains(@id, 'name')]";
    private static final String FLEXIBLE_CONTENT_SPACE_CHOOSE_FILE_BUTTON = "input[id*='%s-%s-subform-field-audio-form-0-field-file-0-upload']";
    private static final String FLEXIBLE_CONTENT_SPACE_URL_ALIAS_INPUT = "//input[contains(@id,'%s-%s-subform-field-audio-form')and contains(@id, 'path')]";
    private static final String FLEXIBLE_CONTENT_SPACE_ADD_EXISTING_MEDIA_ITEM_BUTTON = "input[id*='%s-%s-subform-field-audio-actions'][value*='existing media']";
    private static final String FLEXIBLE_CONTENT_SPACE_ADD_EXISTING_MEDIA_ITEM_INPUT = "input[id*='%s-%s-subform-field-audio-form-0-entity']";
    private static final String FLEXIBLE_CONTENT_SPACE_ADD_MEDIA_ITEM_BUTTON = "input[id*='%s-%s-subform-field-audio-form-0-actions'][value*='Add media']";
    private static final String FLEXIBLE_CONTENT_SPACE_EXISTING_MEDIA_ITEM_LIST = ".ui-widget-content .ui-menu-item a";
    private static final String FLEXIBLE_CONTENT_SPACE_EDIT_AUDIO_BUTTON = "//input[contains(@id,'-subform-field-audio')and contains(@id, 'entity-edit')]";

    private final FlexibleContentSpacePosition position;
    private int index;
    private String audioTitle;
    private boolean existingMediaItem;
    private String mediaName;
    private String urlAlias;
    private String filePath;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        index = selectFlexibleContentSpace(ADD_AUDIO, position) - 1;
        waitAjaxJQueryMet(150);
        addAudioTitle().addMediaItem();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        clickEditFlexibleContentSpace(position, index);
        addAudioTitle().clickEditButton()
                       .addNewMediaItem();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceAudioModel addAudioTitle() {

        if (audioTitle != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_AUDIO_TITLE_INPUT, position.name()
                                                                       .toLowerCase(), index)).should(appear, enabled)
                                                                                              .setValue(audioTitle);
        }
        return this;
    }

    private FlexibleContentSpaceAudioModel addMediaItem() {

        if (existingMediaItem) {
            clickAddExistingMedia().addExistingMediaItem();
        } else {
            clickAddNewMedia().addNewMediaItem();
        }
        return this;
    }

    private FlexibleContentSpaceAudioModel clickAddNewMedia() {
        $(format(FLEXIBLE_CONTENT_SPACE_ADD_NEW_MEDIA_ITEM_BUTTON, position.name()
                                                                           .toLowerCase(), index)).should(appear, enabled)
                                                                                                  .click();
        waitAjaxJQueryMet(300);
        return this;
    }

    private FlexibleContentSpaceAudioModel clickAddExistingMedia() {

        $(format(FLEXIBLE_CONTENT_SPACE_ADD_EXISTING_MEDIA_ITEM_BUTTON, position.name()
                                                                                .toLowerCase(), index)).should(appear, enabled)
                                                                                                       .click();
        waitAjaxJQueryMet(150);
        return this;
    }

    private FlexibleContentSpaceAudioModel addExistingMediaItem() {

        waitAjaxJQueryMet(90);
        $(format(FLEXIBLE_CONTENT_SPACE_ADD_EXISTING_MEDIA_ITEM_INPUT, position.name()
                                                                               .toLowerCase(), index)).should(appear, enabled)
                                                                                                      .setValue(mediaName);
        $$(FLEXIBLE_CONTENT_SPACE_EXISTING_MEDIA_ITEM_LIST).first()
                                                           .should(appear, ofSeconds(30))
                                                           .click();
        $(format(FLEXIBLE_CONTENT_SPACE_ADD_MEDIA_ITEM_BUTTON, position.name()
                                                                       .toLowerCase(), index)).should(appear, enabled)
                                                                                              .click();
        waitAjaxJQueryMet(150);


        return this;
    }

    private FlexibleContentSpaceAudioModel addNewMediaItem() {

        if (mediaName != null) {
            $x(format(FLEXIBLE_CONTENT_SPACE_NEW_MEDIA_NAME_INPUT, position.name()
                                                                           .toLowerCase(), index)).should(appear, enabled)
                                                                                                  .setValue(mediaName);
        }

        if (urlAlias != null) {
            $x(format(FLEXIBLE_CONTENT_SPACE_URL_ALIAS_INPUT, position.name()
                                                                      .toLowerCase(), index)).should(appear, enabled)
                                                                                             .setValue(urlAlias);
        }

        if (filePath != null) {
            File testFile = FileUtils.getFileFromClassPath(filePath);
            $(format(FLEXIBLE_CONTENT_SPACE_CHOOSE_FILE_BUTTON, position.name()
                                                                        .toLowerCase(), index)).scrollTo()
                                                                                               .uploadFile(FileUtils.getFileFromClassPath(filePath));
            waitAjaxJQueryMet(300);
            if (!getFileExtension(testFile.getName()).equals("mp3")) {
                log.error("Selected file type cannot be uploaded");
            }
        }

        return this;
    }

    private FlexibleContentSpaceAudioModel clickEditButton() {
        $x(format(FLEXIBLE_CONTENT_SPACE_EDIT_AUDIO_BUTTON, position.name()
                                                                    .toLowerCase(), index)).should(appear, enabled)
                                                                                           .click();
        waitAjaxJQueryMet(300);
        return this;
    }

}