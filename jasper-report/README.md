# Application

- if run:
    - without parameters then report *jasper_report_template.jrxml* is used.
    - with parameters "db" then report *jasper_report_template_db.jrxml* is used (data are loaded z H2 database).
## Display view on created jasper report

Run/Edit configuration

### display from jrxml or jasper or jrprint
specify:
- *target class*: net.sf.jasperreports.view.JasperDesignViewer
- *params*: -XML -F{path_to_project}\jasper-report\src\main\resources\jasper_report_template.(jrxml|jasper|jrprint)
