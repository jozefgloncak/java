package gloncak.jozef.jsp.integratespringjsp2.model.request.analyze;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestScope
public class Data {

    private Map<String, String> headers = new LinkedHashMap(); //output
    private String name; //input

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void analyzeRequest() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            this.headers.put(headerName, request.getHeader(headerName));
        }
    }

}
