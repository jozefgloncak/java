package gloncak.jozef.jsp.integratespringjsp2.validator;

import org.primefaces.validate.ClientValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Map;
import java.util.regex.Pattern;

@FacesValidator("birthNumberValidator")
public class BirthNumberValidator implements Validator, ClientValidator {

    private static final Pattern PATTERN = Pattern.compile("^[0-9]{10}$");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        if (!PATTERN.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                    "Do not match pattern" + PATTERN.toString()));
        }

        long valueAsNumber = 0;
        try {
            valueAsNumber = Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "It isn't number."));
        }
        if (valueAsNumber % 11 != 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Birth number isn't divisible " +
                    "with 11"));
        }
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return "birthNumberValidator";
    }
}
