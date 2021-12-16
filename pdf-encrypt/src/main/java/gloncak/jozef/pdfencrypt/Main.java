package gloncak.jozef.pdfencrypt;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final String HELP;
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("Program opatri specifikovany pdf subor heslom a vytvori na rovnakom mieste subor s priponou _pass" +
                ".pdf\n");
        sb.append("  1. param - absolutna cesta k pdf suboru.\n");
        sb.append("  2. param - heslo na otvorenie pdf suboru.\n");
        sb.append("\n");
        sb.append("SYNTAX:\n");
        sb.append("java -jar pdf-encrypt{suffix}.jar {path_to_pdf_file} {password}\n\n");
        sb.append("PRIKLAD:\n");
        sb.append("java -jar pdf-encrypt-1.0-SNAPSHOT.jar c:\\Users\\jhrasko\\Documents\\super_secret.pdf " +
                "tajneHeslo123\n");
        HELP = sb.toString();

    }

    public static final void main(String args[]) throws IOException {
        String path = "";
        String password = "";
        if (args.length > 0 && "--help".equals(args[0])) {
            displayHelp();
        }
        if (args.length == 2) {
            path = args[0];
            password  = args[1];
        } else {
            displayHelp();
        }

        File inputFile = new File(path);
        PDDocument doc = PDDocument.load(inputFile);

        int keyLength = 128;

        AccessPermission ap = new AccessPermission();


        // Owner password (to open the file with all permissions) is "12345"
        // User password (to open the file but with restricted permissions, is empty here)
        StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, ap);
        spp.setEncryptionKeyLength(keyLength);
        spp.setPermissions(ap);
        doc.protect(spp);

        String passProtectedFilePath = path.substring(0, path.lastIndexOf(".")) + "_pass.pdf";
        doc.save(passProtectedFilePath);
        doc.close();
        System.out.println(String.format("Vytvoreny PDF %s subor chraneny heslom", passProtectedFilePath));
    }

    private static final void displayHelp() {
        System.out.println(HELP);
        System.exit(0);
    }

}
