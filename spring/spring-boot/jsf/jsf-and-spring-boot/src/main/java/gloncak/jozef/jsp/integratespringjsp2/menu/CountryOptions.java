package gloncak.jozef.jsp.integratespringjsp2.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ApplicationScope
public class CountryOptions {

    private static final Logger LOG = LoggerFactory.getLogger(CountryOptions.class);
    //selection one element - data
    private List<Country> countries = Arrays.asList(
            new Country("Slovensko", "SK")
            , new Country("Cesko", "CR")
            , new Country("Madarsko", "HU")
            , new Country("Polsko", "PL"));

    private String[] countryValuesAsArray = new String[]{"Slovensko", "Cesko", "Madarsko", "Polsko"};
    private List<String> countryValues = Arrays.asList(countryValuesAsArray);
    private Map<String, List<Country>> codeToCountry =
            countries.stream().collect(Collectors.groupingBy(country -> country.getCode()));
    //:selection one element




    public CountryOptions() {
        LOG.debug("Instantiation of {}", CountryOptions.class.getCanonicalName());
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<String> getCountryValues() {
        return countryValues;
    }

    public void setCountryValues(List<String> countryValues) {
        this.countryValues = countryValues;
    }

    public String[] getCountryValuesAsArray() {
        return countryValuesAsArray;
    }

    public void setCountryValuesAsArray(String[] countryValuesAsArray) {
        this.countryValuesAsArray = countryValuesAsArray;
    }

    public Map<String, List<Country>> getCodeToCountry() {
        return codeToCountry;
    }

    public void setCodeToCountry(Map<String, List<Country>> codeToCountry) {
        this.codeToCountry = codeToCountry;
    }
}
