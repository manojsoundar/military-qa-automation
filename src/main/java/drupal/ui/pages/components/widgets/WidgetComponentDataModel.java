package drupal.ui.pages.components.widgets;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.HTML_SNIPPET_INPUT;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.TITLE_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WidgetComponentDataModel extends MasterPage implements ISectionDataModel {

    private String title;
    private String htmlSnippet;

    public WidgetComponentDataModel() {

        title = "Test Widget " + timeStampFormat(PATTERN);
        htmlSnippet = "<div id=\"wufoo-z1pmkj9j1niu3pq\">\n" + "Fill out my <a href=\"https://military.wufoo.com/forms/z1pmkj9j1niu3pq\">online form</a> </div>\n" + "<script type=\"text/javascript\">var z1pmkj9j1niu3pq;(function(d, t) { \n" + "var s = d.createElement(t), options = { \n" +
                "'userName':'military',\n" + "'formHash':'z1pmkj9j1niu3pq',\n" + "'autoResize':true,\n" + "'height':'575',\n" + "'async':true,\n" + "'host':'wufoo.com',\n" + "'header':'show',\n" + "'ssl':true};\n" + "s.src = ('https:' == d.location.protocol ? 'https://' : 'http://') + 'secure.wufoo.com/scripts/embed/form.js';\n" +
                "s.onload = s.onreadystatechange = function() {\n" + "var rs = this.readyState; if (rs) if (rs != 'complete') if (rs != 'loaded') return;\n" + "try { z1pmkj9j1niu3pq = new WufooForm();z1pmkj9j1niu3pq.initialize(options);z1pmkj9j1niu3pq.display(); } catch (e) {}};\n" + "var scr = d.getElementsByTagName(t)[0], par = scr.parentNode; par.insertBefore(s, scr);\n" +
                "})(document, 'script');</script>\n";

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterTitle(expectedClass);
        enterHtmlSnippet(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterTitle(Class<P> expectedClass) {
        if (title != null) {
            TITLE_INPUT.should(appear, exist, enabled)
                       .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                       .setValue(title);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterHtmlSnippet(Class<P> expectedClass) {
        if (htmlSnippet != null) {
            HTML_SNIPPET_INPUT.should(appear, exist, enabled)
                              .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                              .setValue(htmlSnippet);
        }

        return returnInstanceOf(expectedClass);
    }

}
