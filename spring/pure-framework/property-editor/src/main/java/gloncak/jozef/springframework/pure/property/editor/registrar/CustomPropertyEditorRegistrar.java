package gloncak.jozef.springframework.pure.property.editor.registrar;

import gloncak.jozef.springframework.pure.property.editor.Address;
import gloncak.jozef.springframework.pure.property.editor.editor.AddressTypeEditor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
        propertyEditorRegistry.registerCustomEditor(Address.class, new AddressTypeEditor());
    }
}
