package gloncak.jozef.jsp.integratespringjsp2.view;

import gloncak.jozef.jsp.integratespringjsp2.model.DokDotaznikRENTEA;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;


@Component
@javax.faces.view.ViewScoped
public class PlusMinusController implements Serializable {
    
    enum Akcia {
        naZmenu,
        naZmazanie,
        naPridanie,
        ziadna;
        
//        @Override
//        public String toString() {
//            return this.name();
//        }
    }

    private Map<Integer, PolozkaSAkciou> polozky = new LinkedHashMap<>();
    
    private int pocitadlo = 3;


    public PlusMinusController() {
        PolozkaSAkciou.resetIDProvider(); 
        DokDotaznikRENTEA dokDotaznik1 = new DokDotaznikRENTEA();
        dokDotaznik1.setIdDok(new BigDecimal(5090188));
        dokDotaznik1.setIdDokDotaznik(new BigDecimal(1));
        dokDotaznik1.setOdpoved("odpoved1");
        dokDotaznik1.setOtazka("otazka1");        
        System.out.println(Akcia.ziadna);
        PolozkaSAkciou polozkaSAkciou = new PolozkaSAkciou(dokDotaznik1, Akcia.ziadna);
        polozky.put(polozkaSAkciou.getId(), polozkaSAkciou);

        DokDotaznikRENTEA dokDotaznik2 = new DokDotaznikRENTEA();

        dokDotaznik2.setIdDok(new BigDecimal(5090188));
        dokDotaznik2.setIdDokDotaznik(new BigDecimal(2));
        dokDotaznik2.setOdpoved("odpoved2");
        dokDotaznik2.setOtazka("otazka2");
        polozkaSAkciou = new PolozkaSAkciou(dokDotaznik2, Akcia.ziadna);
        polozky.put(polozkaSAkciou.getId(), polozkaSAkciou);
    }

    public Map<Integer, PolozkaSAkciou> getPolozky() {
        return polozky;
    }

    public void setPolozky(Map<Integer, PolozkaSAkciou> polozky) {
        this.polozky = polozky;
    }

    public void acOdobratPolozku(int idPolozky) {
        System.out.println("akcia 'odobratie resp. neodobratie' nastavena pre " + idPolozky);  
        PolozkaSAkciou polozkaNaOdobratie = polozky.get(idPolozky);
        polozkaNaOdobratie.setAkcia(Akcia.naZmazanie);
    }
    
    public void acRowEdited(int idPolozky) {
        System.out.println("Riadok editovany s id "+ idPolozky);
        PolozkaSAkciou polozka = polozky.get(idPolozky);
        if (!Akcia.naPridanie.equals(polozka.getAkcia())) {
            polozka.setAkcia(Akcia.naZmenu);
        }
    }
    
    public void acPridatPolozku() {
        DokDotaznikRENTEA dokDotaznik = new DokDotaznikRENTEA();        
        dokDotaznik.setIdDokDotaznik(new BigDecimal(1));
        PolozkaSAkciou polozkaSAkciou = new PolozkaSAkciou(dokDotaznik, Akcia.naPridanie);
        polozky.put(polozkaSAkciou.getId(), polozkaSAkciou );


        System.out.println("akcia 'pridanie' nastavena: " + polozkaSAkciou.dokDotaznik.getIdDokDotaznik());
        
    }
    
    public void acSave() {
        System.out.println("save");
    }
    
    public void acClose() {
        
    }

    public static class PolozkaSAkciou {
       
        private static int IDProvider = 1;
        
        DokDotaznikRENTEA dokDotaznik;
        Akcia akcia;
        int id;
        

        public PolozkaSAkciou(DokDotaznikRENTEA dokDotaznik, Akcia akcia) {
            this.dokDotaznik = dokDotaznik;
            this.akcia = akcia;
            this.id = PolozkaSAkciou.getNextID();
        }

        public DokDotaznikRENTEA getDokDotaznik() {
            return dokDotaznik;
        }

        public void setDokDotaznik(DokDotaznikRENTEA dokDotaznik) {
            this.dokDotaznik = dokDotaznik;
        }

        public Akcia getAkcia() {
            return akcia;
        }

        public void setAkcia(Akcia akcia) {
            this.akcia = akcia;
        }

        public int getId() {
            return id;
        }

       
        public static void resetIDProvider() {
            IDProvider = 1;
        }
        
        public static int getNextID() {
            return IDProvider++;
        }
        
    }
    

}
