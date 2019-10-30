package gloncak.jozef.java8.features.lambdas;

import gloncak.jozef.java8.features.lambdas.api.TransformationBi;
import gloncak.jozef.java8.features.lambdas.api.TransformationUni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        //lambda without parameter type and without return statement
        TransformationBi concatenation = (a, b) -> a + b;
        LOG.info(concatenation.transform("Martin", "Makovicky"));

        //lambda without parameter type and with return statement
        TransformationBi concatWithSpaceSeparator = (a, b) -> {
            return a + " " + b;
        };
        LOG.info(concatWithSpaceSeparator.transform("Martin", "Makovicky"));

        //lambda with parameter type and without return statement
        TransformationBi uppercaseAndConcate = (String a, String b) -> (a + b).toUpperCase();
        LOG.info(uppercaseAndConcate.transform("Martin", "Makovicky"));

        //lambda without parenthesis around parameter
        TransformationUni uppercase = a -> a.toUpperCase();
        LOG.info(uppercase.transform("Martin"));
    }
}
