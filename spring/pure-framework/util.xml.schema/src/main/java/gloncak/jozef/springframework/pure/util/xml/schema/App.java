package gloncak.jozef.springframework.pure.util.xml.schema;

import gloncak.jozef.springframework.pure.util.xml.schema.helper.Constants;
import gloncak.jozef.springframework.pure.util.xml.schema.helper.OutterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private static final List<String> NAMES = Arrays.asList("Magdalena", "Martin", "Marian", "Maria");

    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");

        CollectorBean collectorBean = appCtx.getBean("dummyBean", CollectorBean.class);
        LOG.info("{} - constant value", collectorBean.getConstValue().equals(Constants.CONST1) ? "OK" : "NOK");
        LOG.info("{} - inner bean value", collectorBean.getValueOfInnerBean().equals("value inner 1") ? "OK" : "NOK");
        LOG.info("{} - list", containAllNames(collectorBean.getFirstNames()) ? "OK" : "NOK");

        OutterClass outterClass = appCtx.getBean("outterClass", OutterClass.class);
        LOG.info("{} - injected list is equal", outterClass.getLst().equals(collectorBean.getFirstNames()) ? "OK" : "NOK");
    }

    private static boolean containAllNames(List<String> checkedNames) {
        Comparator<String> compare = String::compareTo;
        checkedNames.sort(compare);
        NAMES.sort(compare);

        if (checkedNames.size() != NAMES.size()) {
            return false;
        }

        for(int i = 0; i<checkedNames.size(); i++) {
            if (!checkedNames.get(i).equals(NAMES.get(i))) {
                return false;
            }
        }
        return true;
    }
}
