package gloncak.jozef.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Compiling Report Design ...");
        JasperReport jasperReport;
        try {
            /**
             * Compile the report to a file name same as
             * the JRXML file name
             */
//            JasperCompileManager.compileReportToFile("C:\\cygwin64\\home\\jgloncak\\repos" +
//                    "\\java" +
//                    "\\jasper-report\\src\\main\\resources\\jasper_report_template.jrxml");
//            FileOutputStream fileOutputStream = new FileOutputStream("jasper_report_template.jasper");
            String sourceFileName = isReadFromDB(args) ? "jasper_report_template_db.jrxml" : "jasper_report_template.jrxml";
            InputStream resourceAsStream = App.class.getClassLoader().getResourceAsStream(sourceFileName);
            jasperReport = JasperCompileManager.compileReport(resourceAsStream);

            ///parameters
            Map<String, Object> params = new HashMap<>();
            params.put("ReportTitle", "Report title");
            params.put("Author", "Author of report");

            JasperPrint jrPrint;
            if (isReadFromDB(args)) {
                DBManager dbManager = new DBManager();
                dbManager.executeUpdate("drop table simple");
                if (dbManager.executeUpdate("    CREATE TABLE IF NOT EXISTS SIMPLE " +
                        "(id INTEGER not NULL, " +
                        " name VARCHAR(255), " +
                        " country VARCHAR(255), " +
                        " PRIMARY KEY ( id ))")
                ) {
                    dbManager.executeUpdate(" INSERT INTO SIMPLE VALUES (1, 'A1', 'AA2')");
                    dbManager.executeUpdate(" INSERT INTO SIMPLE VALUES (2, 'A2', 'AA2')");
                    dbManager.executeUpdate(" INSERT INTO SIMPLE VALUES (3, 'A3', 'AA3')");
                    dbManager.executeUpdate(" INSERT INTO SIMPLE VALUES (4, 'A4', 'AA4')");
                    dbManager.executeUpdate(" INSERT INTO SIMPLE VALUES (5, 'A5', 'AA5')");
                }

                Connection conn = dbManager.provideDBConnection();
                jrPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            } else {
                //generate dummy data
                List<DataBean> data = Arrays.asList(
                        new DataBean("Name1", "Country1"),
                        new DataBean("Name2", "Country2"),
                        new DataBean("Name3", "Country3"),
                        new DataBean("Name4", "Country4")
                );

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
                jrPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            }
//            String filePrintName = JasperFillManager.fillReportToFile(jasperReport, new HashMap(), dataSource);

            //print manager
//            JasperPrintManager.printReport(jrPrint, true);

            //export to other format
            JasperExportManager.exportReportToPdfFile(jrPrint, "pdf_output.pdf");
            JasperExportManager.exportReportToHtmlFile(jrPrint, "html_output.html");
            JasperExportManager.exportReportToXmlFile(jrPrint, "xml_output.xml", false);
            JRXlsExporter xlsExporter = new JRXlsExporter();
            xlsExporter.setExporterInput(new SimpleExporterInput(jrPrint));
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput("output_jasper_report_template.xls"));
            xlsExporter.exportReport();


        } catch (JRException e) {
            e.printStackTrace();
        }
        System.out.println("Done compiling!!! ...");

    }

    private static boolean isReadFromDB(String[] args) {
        return args.length > 0 && "db".equalsIgnoreCase(args[0]);
    }
}
