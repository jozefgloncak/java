package gloncak.jozef.jsp.integratespringjsp2.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.view.ViewScoped;
import java.util.*;

@Component
@ViewScoped
public class cElkomUdalostDetailController {

    private String[] dnyOpakovaniList;
    private boolean showDnyOpakovani = false;

    private static final String FREKVENCE_DENNE = "D";
    private static final String FREKVENCE_MESICNE = "M";
    private static final String FREKVENCE_TYDNE = "T";
    private static final String FREKVENCE_ROCNE = "R";
    private static final String FREKVENCE_OPAKOVAT_KAZDY = "o";
    private static final String FREKVENCE_NEURCENO = "N";
    private String frekvencia;
    private String beforeFrekvencia;

    public void acZmenaFrekvencia() {
        if (frekvencia.equals(beforeFrekvencia)) {
            frekvencia = FREKVENCE_NEURCENO;
        }
        System.out.println("test4");
        this.showDnyOpakovani = false;
        if (FREKVENCE_OPAKOVAT_KAZDY.equals(frekvencia)) {
            this.showDnyOpakovani = true;
        }
        beforeFrekvencia = frekvencia;
    }

    public boolean getShowDnyOpakovani() {
        return showDnyOpakovani;
    }

    public void setShowDnyOpakovani(boolean showDnyOpakovani) {
        this.showDnyOpakovani = showDnyOpakovani;
    }

    public String[] getDnyOpakovaniList() {
        return dnyOpakovaniList;
    }

    public void setDnyOpakovaniList(String[] dnyOpakovaniList) {
        this.dnyOpakovaniList = dnyOpakovaniList;
    }

    public String getFREKVENCE_DENNE() {
        return FREKVENCE_DENNE;
    }

    public String getFREKVENCE_MESICNE() {
        return FREKVENCE_MESICNE;
    }

    public String getFREKVENCE_TYDNE() {
        return FREKVENCE_TYDNE;
    }

    public String getFREKVENCE_ROCNE() {
        return FREKVENCE_ROCNE;
    }

    public String getFREKVENCE_OPAKOVAT_KAZDY() {
        return FREKVENCE_OPAKOVAT_KAZDY;
    }

    public String getFREKVENCE_NEURCENO() {
        return FREKVENCE_NEURCENO;
    }

    public String getFrekvencia() {
        return frekvencia;
    }

    public void setFrekvencia(String frekvencia) {
        this.frekvencia = frekvencia;
    }

    public void odoslatData() {
        System.out.println("data odoslane");
    }



    private Integer rok;
    private Integer mesiac;
    private Integer den;

    public Integer getRok() {
        return rok;
    }

    public void setRok(Integer rok) {
        this.rok = rok;
    }

    public Integer getMesiac() {
        return mesiac;
    }

    public void setMesiac(Integer mesiac) {
        this.mesiac = mesiac;
    }

    public Integer getDen() {
        return den;
    }

    public void setDen(Integer den) {
        this.den = den;
    }

    public List<Integer> getRoky() {
        return CalendarUtil.roky;
    }

    public List<Integer> getMesiace() {
        return CalendarUtil.mesiace;
    }

    public List<Integer> getDni() {
        return CalendarUtil.vratListSDnamiPreMesiac(this.mesiac, this.rok);
    }

}
