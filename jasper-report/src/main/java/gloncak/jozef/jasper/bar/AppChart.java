package gloncak.jozef.jasper.bar;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppChart {
    public static void main(String[] args) {
        ArrayList<DataBean> dataList = new ArrayList<DataBean>();

        dataList.add(produce("Bratislava", 441000));
        dataList.add(produce("Kosice", 238000));
        dataList.add(produce("Presov", 88000));
        dataList.add(produce("Zilina", 80000));
        dataList.add(produce("Banska Bystrica", 78000));
        dataList.add(produce("Nitra", 76000));
        dataList.add(produce("Trnava", 65000));
        dataList.add(produce("Trencin", 55500));



        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

        Map parameters = new HashMap();

        try {
            InputStream resourceAsStream = AppChart.class.getClassLoader().getResourceAsStream("jasper_report_chart.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resourceAsStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "pdf_output_chart.pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private static DataBean produce(String city, Integer population) {
        DataBean dataBean = new DataBean();

        dataBean.setCity(city);
        dataBean.setPopulation(population);

        return dataBean;
    }
}
