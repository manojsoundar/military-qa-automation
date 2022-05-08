package www.ui.pages.veteranJobs;

import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static org.testng.Assert.assertTrue;

@Log4j2
public class UploadYourResumePage extends GlobalHeaderComponent {

    private static final String URL = "/resumes";

    public UploadYourResumePage() {

        assertTrue(verifyURLLoaded(URL), "Upload Your Resume Page not loaded..");
        log.info("Upload Your Resume Page loaded..");
    }

}
