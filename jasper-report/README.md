TUTORIAL ENDED AT
https://www.tutorialspoint.com/jasper_reports/jasper_report_expression.htm

# Application

- if run:
    - without parameters
      - report *jasper_report_template.jrxml*
      - datasource dummy data from code.
    - with parameters "db" 
      - report *jasper_report_template_db.jrxml*
      - datasource data from H2 database.
    - with parameter "csv"
      - report *jasper_report_template.jrxml"
      - datasource enclosed *country.csv* file 


# Display view on created jasper report

Run/Edit configuration

## display from jrxml or jasper or jrprint
specify:
- *target class*: net.sf.jasperreports.view.JasperDesignViewer
- *params*: -XML -F{path_to_project}\jasper-report\src\main\resources\jasper_report_template.(jrxml|jasper|jrprint)
