package gloncak.jozef.springframework.pure.property.editor.config;

import gloncak.jozef.springframework.pure.property.editor.Address;
import gloncak.jozef.springframework.pure.property.editor.beans.Person;
import gloncak.jozef.springframework.pure.property.editor.editor.AddressTypeEditor;
import gloncak.jozef.springframework.pure.property.editor.registrar.CustomPropertyEditorRegistrar;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfiguration {

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setPropertyEditorRegistrars(
                new PropertyEditorRegistrar[]{new CustomPropertyEditorRegistrar()});
        return customEditorConfigurer;
    }

    @Bean
    Person person() {
        Person person = new Person();
        return person;
    }


}
