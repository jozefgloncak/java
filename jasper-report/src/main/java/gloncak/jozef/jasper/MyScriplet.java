package gloncak.jozef.jasper;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class MyScriplet extends JRDefaultScriptlet {

    public String hello() throws JRScriptletException {
        return "Hello from scriplet";
    }

}
