package gloncak.jozef.jsp.integratespringjsp2.model;;

import java.math.BigDecimal;

public class DokDotaznikRENTEA {
    private String mainTable;

    private BigDecimal idDokDotaznik;
    private BigDecimal idDok;
    private String otazka;
    private String odpoved;
    
    public DokDotaznikRENTEA() {
        mainTable = "dok_dotaznik";
    }

    public BigDecimal getIdDokDotaznik() {
        return idDokDotaznik;
    }

    public void setIdDokDotaznik(BigDecimal idDokDotaznik) {
        this.idDokDotaznik = idDokDotaznik;
    }
    

    public BigDecimal getIdDok() {
        return idDok;
    }

    public void setIdDok(BigDecimal idDok) {
        this.idDok = idDok;
    }
    public String getOtazka() {
        return otazka;
    }

    public void setOtazka(String otazka) {
        this.otazka = otazka;
    }


    public String getOdpoved() {
        return odpoved;
    }

    public void setOdpoved(String odpoved) {
        this.odpoved = odpoved;
    }

}
