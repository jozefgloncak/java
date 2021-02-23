package gloncak.jozef.jsp.integratespringjsp2.view.dialog;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class DialogOptions {
    
    private Map<String, String> effects = new HashMap<>();

    public DialogOptions() {
        effects.put ("blind", "blind");
        effects.put ("clip", "clip");
        effects.put ("drop", "drop");
        effects.put ("explode", "explode");
        effects.put ("fold", "fold");
        effects.put ("puff", "puff");
        effects.put ("slide", "slide");
        effects.put ("scale", "scale");
        effects.put ("bounce", "bounce");
        effects.put ("highlight", "highlight");
        effects.put ("pulsate", "pulsate");
        effects.put ("shake", "shake");
        effects.put ("size", "size");
        effects.put ("transfer", "transfer");    
    }
    
    public Map<String, String> getEffects() {
        return effects;
    }
    
}
