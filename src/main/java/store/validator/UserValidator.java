package store.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.Objects;

@Component
public class UserValidator {

    public String getErrorMessage(FieldError fieldError){

        String errorMessage;
        switch (fieldError.getField()) {
            case "email":
                errorMessage = "Invalid email format";
                break;
            case "password":
                errorMessage = "Your password must have minimum eight characters and at least one letter and one number";
                break;
            default:
                errorMessage = this.getErrorMessageFromCode(Objects.requireNonNull(fieldError.getCode()));
        }

        return errorMessage;
    }

    private String getErrorMessageFromCode(String errorCode){

        if (errorCode.equals("Pattern")) {
            return "This field contains invalid characters";
        } else {
            return "This field cannot be empty";
        }
    }
}
