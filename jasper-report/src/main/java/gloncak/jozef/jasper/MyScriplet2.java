package gloncak.jozef.jasper;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class MyScriplet2 extends JRDefaultScriptlet {

    public String title() throws JRScriptletException {
        return "Title from scriplet";
    }

    @Override
    public void beforeReportInit() {
        System.out.println("!!!!!before report initialization");
    }

    @Override
    public void afterReportInit() {
        System.out.println("!!!!!after report initialization");
    }
}
