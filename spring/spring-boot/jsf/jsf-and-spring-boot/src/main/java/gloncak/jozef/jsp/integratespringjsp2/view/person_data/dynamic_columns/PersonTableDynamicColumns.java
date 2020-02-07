package gloncak.jozef.jsp.integratespringjsp2.view.person_data.dynamic_columns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import org.primefaces.component.column.Column;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class PersonTableDynamicColumns implements Serializable {

//    private List<ColumnModel> columns = new ArrayList<>(Arrays.asList(new ColumnModel("Age", "age"), new ColumnModel("First name", "firstName")));
    private Map<String, ColumnModel> columnsToEnable = new HashMap<>();

    public PersonTableDynamicColumns() {
        columnsToEnable.put("age", new ColumnModel("Age", "age", true));
        columnsToEnable.put("firstName", new ColumnModel("First name", "firstName", false));
        columnsToEnable.put("height", new ColumnModel("Height", "height", true));
        columnsToEnable.put("jobStartDate", new ColumnModel("Job start", "jobStartDate", false));
    }

    public List<ColumnModel> getColumns() {
        return columnsToEnable.values().stream().filter(column -> column.getEnabled()).collect(Collectors.toList());        
    }

    public Map<String, ColumnModel> getColumnsToEnable() {
        return columnsToEnable;
    }

    public void setColumnsToEnable(Map<String, ColumnModel> columnsToEnable) {
        this.columnsToEnable = columnsToEnable;
    }
    
    

    public class ColumnModel {

        private String header;
        private String attrName;
        private Boolean enabled;

        public ColumnModel() {
        }

        public ColumnModel(String header, String attrName, Boolean enabled) {
            this.header = header;
            this.attrName = attrName;
            this.enabled = enabled;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }
}
