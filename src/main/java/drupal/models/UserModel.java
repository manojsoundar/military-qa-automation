package drupal.models;

import io.restassured.path.json.JsonPath;
import lombok.Data;

import static mgs.qa.utils.fileutils.FileUtils.getFileFromClassPath;

@Data
public class UserModel {
    protected String username;
    protected String password;

    public static UserModel loadDefaultUserModel() {
        return loadUserModel("default", "Envs/QAX6/users-military.json");
    }

    public static UserModel loadUserModel(final String userNode) {
        return loadUserModel(userNode, "Envs/QAX6/users-military.json");
    }

    public static UserModel loadUserModel(final String userNode, final String jsonFile) {
        final JsonPath json = new JsonPath(getFileFromClassPath(jsonFile));
        return json.getObject(userNode, UserModel.class);
    }

}
