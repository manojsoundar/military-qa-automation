package military.www.ui;

import common.IMilitaryEnvironment;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.config.TestProperties;
import mgs.qa.enums.Application;
import mgs.qa.webdriver.WebDriverConfig;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static mgs.qa.base.config.TestProperties.getBrowser;
import static mgs.qa.base.config.TestProperties.getConnectionType;
import static mgs.qa.base.config.TestProperties.getHubUrl;
import static mgs.qa.base.config.TestProperties.getSeleniumProxy;
import static mgs.qa.base.config.TestProperties.setUITestVariables;

@Log4j2
public class MilitaryUITestBase extends mgs.qa.base.test.UITestBase implements IMilitaryEnvironment {

    @Override
    @Parameters({"BROWSER", "HUB", "OPTIONS"})
    @BeforeSuite(dependsOnMethods = "initTestData", alwaysRun = true)
    public void initWebDriver(@Optional(DEFAULT_BROWSER) String browser, @Optional String hub, @Optional String options) {
        setUITestVariables(browser, hub);
        WebDriverConfig.initWebDriver(getConnectionType(),
                                      getBrowser(),
                                      getHubUrl(),
                                      isProxyEnabled() ? getSeleniumProxy() : null,
                                      options);

        log.info("Environment: " + (System.getProperty("env") == null ? TestProperties.getEnvironmentPropertyValue() : System.getProperty("env")));
        militarySelenideTest((System.getProperty("env") == null ? TestProperties.getEnvironmentPropertyValue()  : System.getProperty("env")));
    }

    @Override
    protected Application getApplication() {
        return Application.MILITARY;
    }

}
