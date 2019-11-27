package gloncak.jozef.springframework.pure.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class);
        LOG.info(appCtx.getMessage("msg001", null, Locale.getDefault()));
        LOG.info(appCtx.getMessage("msg001", null, Locale.forLanguageTag("sk")));
        LOG.info(appCtx.getMessage("msg002", new Object[]{"Marcus"}, Locale.getDefault()));
        LOG.info(appCtx.getMessage("msg002", new Object[]{"Marcus"}, Locale.forLanguageTag("sk")));
    }
}
