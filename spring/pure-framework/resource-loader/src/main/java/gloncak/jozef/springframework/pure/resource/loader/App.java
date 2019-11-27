package gloncak.jozef.springframework.pure.resource.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");
        Resource resource = appCtx.getResource("whatever_resource.txt");
        LOG.info("resource description: {}", resource.getDescription());
        LOG.info("file name: {}", resource.getFilename());
        LOG.info("is resource open: {}", resource.isOpen());
        LOG.info("is resource file: {}", resource.isFile());

        try {
            File file = resource.getFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            LOG.info("FILE CONTENT:");
            try {
                String line;
                while ((line = br.readLine())!=null){
                    LOG.info(line);
                }
            } catch (IOException e) {
                LOG.info("end of file reached.");
            } finally {
                br.close();
                LOG.info("closing file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
