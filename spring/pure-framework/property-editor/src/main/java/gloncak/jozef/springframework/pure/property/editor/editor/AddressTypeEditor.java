package gloncak.jozef.springframework.pure.property.editor.editor;

import gloncak.jozef.springframework.pure.property.editor.Address;

import java.beans.PropertyEditorSupport;

public class AddressTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] splittedAddress = text.split("\\|");
        Address address = new Address(splittedAddress[0], Integer.valueOf(splittedAddress[1]), splittedAddress[2]);
        setValue(address);
    }
}
