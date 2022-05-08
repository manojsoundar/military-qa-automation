package drupal.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoUploadModel {

    protected final String mediaName;
    protected final boolean existingVideo;
    protected final boolean multipleVideo;
    protected final boolean bodyVideo;

    public VideoUploadModel() {
        mediaName = null;
        existingVideo = true;
        multipleVideo = false;
        bodyVideo = false;
    }

}
