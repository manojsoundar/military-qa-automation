package www.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mgs.qa.model.AbstractModel;
import www.enums.ServiceType;

import static mgs.qa.utils.DataGenerators.generateUniqueEmail;
import static www.enums.ServiceType.AIR_FORCE;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDataModel extends AbstractModel {

    protected ServiceType service;
    protected int zipCode;
    protected String email;
    protected String status;
    protected String securityClearance;

    public static UserRegistrationDataModel random() {
        return new UserRegistrationDataModel(
                AIR_FORCE,
                12345,
                generateUniqueEmail(),
                "None",
                "None"
        );
    }

}
