package gloncak.jozef.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String HELP;
    private static final int IDX_MERGE_DIR = 1;
    private static final int IDX_MERGE_PASSWORD = 2;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("Program spracuvava pdf subory.\n");
        sb.append("- nacita nezakryptovany PDF subor a vytvori na rovnakom mieste subor s priponou _pass chraneny " +
                "heslom" +
                ".pdf\n");
        sb.append("\n");
        sb.append("SYNTAX:\n");
        sb.append("java -jar pdf{suffix}.jar --encrypt {path_to_pdf_file} {password}\n\n");
        sb.append("PRIKLAD:\n");
        sb.append("java -jar pdf-encrypt-1.0-SNAPSHOT.jar c:\\Users\\jhrasko\\Documents\\super_secret.pdf " +
                "tajneHeslo123\n");
        sb.append("\n");
        sb.append("- nacita zo specifikovaneho adresara vsetky PDF subory a spoji ich do jedneho - output.pdf. Ak su " +
                "subory opatrene heslom je mozne ho specifikovat. Aj vystupny subor ma potom rovnake heslo. Heslo " +
                "musi byt pre vsetky vstupne subory rovnake resp. je pripustne aby sa pouzili aj niektore " +
                "nezaheslovane vstupne subory.");
        sb.append("\n");
        sb.append("\n");
        sb.append("SYNTAX:\n");
        sb.append("java -jar pdf{suffix}.jar --merge {path_to_dir} [password]\n\n");
        HELP = sb.toString();

    }

    public static final void main(String args[]) throws IOException {

        if (containsParam(args, "--help")) {
            displayHelp();
        } else if (containsParam(args, "--merge")) {
            handleMerge(args);
        } else if (containsParam(args, "--encrypt"))  {
            handleEncrypt(args);
        } else {
            displayHelp();
        }

    }

    private static void handleEncrypt(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Nespravny pocet argumentov. Nespravna syntax");
            System.exit(-1);
        }
        String path = args[1].replace("\\", "\\\\");
        String password = args[2];

        File inputFile = new File(path);
        PDDocument doc = PDDocument.load(inputFile);

        addPassword(doc, password);

        String passProtectedFilePath = path.substring(0, path.lastIndexOf(".")) + "_pass.pdf";
        doc.save(passProtectedFilePath);
        doc.close();
        System.out.println(String.format("Vytvoreny PDF %s subor chraneny heslom", passProtectedFilePath));
    }

    private static boolean containsParam(String[] args, String searchedValue) {
        return Stream.of(args).filter(arg -> arg.equals(searchedValue)).findAny().isPresent();
    }

    private static void handleMerge(String[] args) throws IOException {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Nespravny pocet argumentov. Nespravna syntax");
            System.exit(-1);
        }
        boolean hasPassword = false;
        String password = "";
        if (args.length == 3) {
            hasPassword = true;
            password = args[IDX_MERGE_PASSWORD].replace("\\", "\\\\");
        };
        //load files from dir
        File pdfDir = new File(args[IDX_MERGE_DIR]);
        if (!pdfDir.exists()) {
            System.out.println("Specifikovany adresar neexistuje");
            System.exit(-1);
        }
        Pattern ptrn = Pattern.compile(".*[.]pdf");

        PDDocument outPdf = new PDDocument();
        File outputFile = new File(pdfDir.getAbsolutePath() + "\\" + "output.pdf");
        //vymazanie povodneho vystupu
        if (outputFile.exists()) {
            Files.delete(outputFile.toPath());
        }
        List<PDDocument> partialPdfs = new ArrayList<>();

        //vyfiltrovat iba PDF subory
        List<File> pdfFiles = Arrays.stream(pdfDir.listFiles())
                .filter(pdfFile -> ptrn.matcher(pdfFile.getName().toLowerCase()).matches())
                .collect(Collectors.toList());
        System.out.println("Na spracovanie najdene subory:");
        pdfFiles.forEach(System.out::println);

        for (File pdfFile : pdfFiles) {
            PDDocument doc = null;
            if (hasPassword) {
                try {
                    doc = PDDocument.load(pdfFile, password); //heslom chranene
                } catch (InvalidPasswordException e) {
                    System.out.println(String.format("Specifikovane nespravne heslo pre subor %s.", pdfFile));
                    System.exit(-1);
                }
            } else {
                try {
                    doc = PDDocument.load(pdfFile); //bez hesla
                } catch (InvalidPasswordException e) {
                    String msg = String.format("Jeden zo vstupnych suborov - %s - je chraneny heslom. Heslo " +
                            "nebolo specifikovane.", pdfFile.getName());
                    System.out.println(msg);
                    System.exit(-1);
                }
            }
            partialPdfs.add(doc);

            doc.setAllSecurityToBeRemoved(true);
            PDPageTree pages = doc.getPages();
            for (PDPage page : pages) {
                outPdf.addPage(page);
            }
        }
        if (hasPassword) {
            addPassword(outPdf, password);
        }

        outPdf.save(outputFile);
        outPdf.close();
        for (PDDocument partialPdf : partialPdfs) {
            partialPdf.close();
        }
    }

    private static PDDocument addPassword(PDDocument pdDoc, String password) throws IOException {
        int keyLength = 128;

        AccessPermission ap = new AccessPermission();
        // Owner password (to open the file with all permissions) is "12345"
        // User password (to open the file but with restricted permissions, is empty here)
        StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, ap);
        spp.setEncryptionKeyLength(keyLength);
        spp.setPermissions(ap);
        pdDoc.protect(spp);
        return pdDoc;
    }

    private static final void displayHelp() {
        System.out.println(HELP);
        System.exit(0);
    }

}
