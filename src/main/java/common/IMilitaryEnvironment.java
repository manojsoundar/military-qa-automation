package common;

import com.codeborne.selenide.Configuration;
import lombok.AllArgsConstructor;
import mgs.qa.base.config.TestProperties;
import mgs.qa.enums.TestEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimeZone;

import static common.IMilitaryEnvironment.MilitaryUrlEnvironment.URL_MILDEV;
import static common.IMilitaryEnvironment.MilitaryUrlEnvironment.URL_MILDEV2;
import static common.IMilitaryEnvironment.MilitaryUrlEnvironment.URL_MILQA;

public interface IMilitaryEnvironment {

    Logger LOG = LogManager.getLogger(IMilitaryEnvironment.class);

    default void militarySelenideTest(String env) {
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("selenide.play.timeZone", "EDT")));
        Configuration.timeout = TestProperties.getSelenideTimeoutSec() * 1000L;
        Configuration.pageLoadStrategy = "normal";
        Configuration.pageLoadTimeout = 300000;
        //assertTrue(check if env is belong the enum environment)
        switch (TestEnvironment.valueOf(env.toUpperCase())) {
            case MILDEV:
                Configuration.baseUrl = URL_MILDEV;
                TestProperties.setBaseUrl(URL_MILDEV);
                LOG.info("Configuration.baseUrl and TestProperties.baseUrl set to " + URL_MILDEV);
                break;
            case MILDEV2:
                Configuration.baseUrl = URL_MILDEV2;
                TestProperties.setBaseUrl(URL_MILDEV2);
                LOG.info("Configuration.baseUrl and TestProperties.baseUrl set to " + URL_MILDEV2);
                break;
            case QAX6:
            case MILQA:
                Configuration.baseUrl = URL_MILQA;
                TestProperties.setBaseUrl(URL_MILQA);
                LOG.info("Configuration.baseUrl and TestProperties.baseUrl set to " + URL_MILQA);
                break;
            default:
                throw new RuntimeException("Environment not valid ! : " + env);
        }

    }


    @AllArgsConstructor
    final class MilitaryUrlEnvironment {

        public static final String URL_MILDEV = "https://militarydev.prod.acquia-sites.com";
        public static final String URL_MILDEV2 = "https://militarydev2.prod.acquia-sites.com";
        public static final String URL_MILQA = "https://militarystg.prod.acquia-sites.com";

    }

}
