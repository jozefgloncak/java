package gloncak.jozef.jsp.integratespringjsp2.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Arrays;
import java.util.List;

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

    private List<String> countryValues = Arrays.asList("Slovensko", "Cesko", "Madarsko", "Polsko");
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
}
