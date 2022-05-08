package drupal.models;

import lombok.Getter;

import javax.annotation.Nullable;

@Getter
public class FilesModel {

    @Nullable
    private final String fileName;
    @Nullable
    private final String mimeType;

    public FilesModel(String fileName, String mimeType) {
        this.fileName = fileName;
        this.mimeType = mimeType;
    }

}
