package drupal.ui.pages.add_content.author;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.AUTHORS_NAME_INPUT;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.BIO_IFRAME;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.BIO_INPUT;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.BLURB_TEXTAREA;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.BREADCRUMB_CATEGORY;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.CONTENT_RELEASE;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.EMAIL_INPUT;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.FIRST_NAME_INPUT;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.INACTIVE_SELECTION;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.LAST_NAME_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthorModel extends MasterPage implements ISectionDataModel, AttachAnImage, Cloneable {

    String name;
    String firstName;
    String lastName;
    String blurb;
    String bio;
    String email;
    String contentRelease;
    String breadCrumbCategory;
    String inActiveSelection;

    public AuthorModel() {

        name = "Test Author " + timeStampFormat(PATTERN);
        firstName = "TEST";
        lastName = "AUTHOR";
        blurb = null;
        bio = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
        email = null;
        contentRelease = null;
        breadCrumbCategory = null;
        inActiveSelection = null;

    }

    // TODO Sonar java:S2975 "clone" should not be overridden
    //  use a copy constructor or copy factory instead
    @Override
    public AuthorModel clone() {
        AuthorModel author = null;
        try {
            author = (AuthorModel) super.clone();
        } catch (CloneNotSupportedException e) {
            author = new AuthorModel(
                    this.getName(),
                    this.getFirstName(),
                    this.getLastName(),
                    this.getBlurb(),
                    this.getBio(),
                    this.getEmail(),
                    this.getContentRelease(),
                    this.getBreadCrumbCategory(),
                    this.getInActiveSelection()
            );
        }

        return author;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        typeFirstName(expectedClass);
        typeLastName(expectedClass);
        typeBlurb(expectedClass);
        typeBio(expectedClass);
        typeEmail(expectedClass);
        typeContentRelease(expectedClass);
        typeBreadCrumbCategory(expectedClass);
        selectInActiveSelection(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeName(Class<P> expectedClass) {
        if (name != null) {
            AUTHORS_NAME_INPUT.should(visible, enabled)
                              .setValue(name);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeFirstName(Class<P> expectedClass) {
        if (firstName != null) {
            FIRST_NAME_INPUT.should(visible, enabled)
                            .setValue(firstName);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLastName(Class<P> expectedClass) {
        if (lastName != null) {
            LAST_NAME_INPUT.should(visible, enabled)
                           .setValue(lastName);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBlurb(Class<P> expectedClass) {
        if (blurb != null) {
            BLURB_TEXTAREA.should(appear, visible, enabled)
                          .setValue(blurb);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBio(Class<P> expectedClass) {
        if (bio != null) {
            switchTo().frame(BIO_IFRAME);
            BIO_INPUT.should(visible, enabled)
                     .setValue(bio);
            getDriver().switchTo()
                       .parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeEmail(Class<P> expectedClass) {
        if (email != null) {
            EMAIL_INPUT.should(appear, visible, enabled)
                       .setValue(email);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeContentRelease(Class<P> expectedClass) {
        if (contentRelease != null) {
            CONTENT_RELEASE.should(appear, visible, enabled)
                           .setValue(contentRelease);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBreadCrumbCategory(Class<P> expectedClass) {
        if (breadCrumbCategory != null) {
            BREADCRUMB_CATEGORY.should(appear, visible, enabled)
                               .setValue(breadCrumbCategory);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectInActiveSelection(Class<P> expectedClass) {
        if (inActiveSelection != null) {
            INACTIVE_SELECTION.should(appear, visible, enabled)
                              .selectOptionContainingText(inActiveSelection);
        }
        return returnInstanceOf(expectedClass);
    }

}
