package gloncak.jozef.jsp.integratespringjsp2.view.color;

import gloncak.jozef.jsp.integratespringjsp2.model.Color;
import gloncak.jozef.jsp.integratespringjsp2.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class ColorView {

    @Autowired
    ColorRepository colorRepository;

    private Iterable<Color> colors;

    public ColorView() {
         colors = this.colorRepository.findAll();
    }

    public Iterable<Color> getColors() {
        return colors;
    }

    public ColorRepository getColorRepository() {
        return colorRepository;
    }

    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }
}
