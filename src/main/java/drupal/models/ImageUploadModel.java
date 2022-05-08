package drupal.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUploadModel {

    protected final String mediaName;
    protected final String date;
    protected final String keywords;
    protected final boolean existingImage;
    protected final boolean multipleImage;
    protected String imageTitle;

    public ImageUploadModel() {
        mediaName = "Test Image";
        date = "";
        keywords = "";
        existingImage = true;
        multipleImage= false;
        imageTitle = null;
    }

}
