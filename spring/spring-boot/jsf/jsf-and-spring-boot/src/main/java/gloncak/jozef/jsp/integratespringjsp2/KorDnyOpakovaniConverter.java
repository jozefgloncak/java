package gloncak.jozef.jsp.integratespringjsp2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KorDnyOpakovaniConverter /* extends ConverterBase */ {
    private static final int POCET_DNI_V_TYZDNI = 7;
    private static final char DEN_OPAKOVANI_VYBRATY = '1';

    //    @Override
    public List<Integer> getAsObject(String opakovatDniAsString) {
        List<Integer> result = new ArrayList<>();
        if (opakovatDniAsString == null) {
            //TODO: vylepsit logovanie
//            LOGGER.log(Level.WARNING, getResourceString("korMsg", "celkomudalost_opakovatDni")+ " je null", new Object[]{});
            return result;
        }
        if (opakovatDniAsString.length() != POCET_DNI_V_TYZDNI) {
//            LOGGER.log(Level.WARNING, "Dlzka retazca z DB pre dni opakovania nie je 7");
            return result;
        }

        int value = 1000000;
        for (int i = 0; i < POCET_DNI_V_TYZDNI; i++) {
            if (opakovatDniAsString.charAt(i) == DEN_OPAKOVANI_VYBRATY) {
                result.add(value);
            }
            value = value / 10;
        }
        return result;

    }

    //    @Override
    public String getAsString(Object opakovatDni) {
        if (!(opakovatDni instanceof List)) {
            //TODO: log error
        }

        Integer sucet = 0;
        for (Object opakovatDen : (List) opakovatDni) {
            Object opakovatDenIn = opakovatDen;
            if (opakovatDenIn instanceof String) {
                try {
                    opakovatDenIn = Integer.valueOf((String) opakovatDenIn);
                } catch (NumberFormatException e) {

                }
            }
            if (opakovatDenIn instanceof Integer && obsahujeIba1NaZaciatku((int)opakovatDenIn)) {
                sucet+=(int) opakovatDenIn;
            }
        }

        String sucetAsString = String.format("%7s", sucet);
        if (sucetAsString.length() > POCET_DNI_V_TYZDNI) {
            sucetAsString = sucetAsString.substring(sucetAsString.length() - POCET_DNI_V_TYZDNI);
        }
        return sucetAsString.replace(" ", "0");
    }

    /**
     * Testuje ci cislo na vstupe je v tvare 10000...000.
     *
     * Cize ci za uvodnou jednotkou su len same 0.
     *
     * @param cislo
     * @return
     */
    private boolean obsahujeIba1NaZaciatku(int cislo) {
        int cisloLocal = cislo;
        if (cisloLocal == 0) {
            return false;
        }
        while (cisloLocal != 1 ) {
            if (cisloLocal % 10 == 0) {
                cisloLocal = cisloLocal / 10;
            } else {
                return false;
            }
        }
        return true;
    }
}
