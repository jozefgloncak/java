package gloncak.jozef.springboot.restfulwebservice.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value2", "password", "value4");
    }

    /**
     * Request will return only {@code value1} of {@code SomeBeanForDynamicFilter}
     *
     * @return
     */
    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue retrieveSomeBeanDynamicFiltering() {
        SomeBeanForDynamicFiltering someBean = new SomeBeanForDynamicFiltering("value1", "value2", "password", "value4");
        return someBeanDynamicFiltering(someBean, "someBeanFilter", "value1");
    }

    /**
     * Request will return only {@code value1} and {@code value2} of {@code SomeBeanForDynamicFilter}
     *
     * @return
     */
    @GetMapping("/filtering-dynamic2")
    public MappingJacksonValue retrieveSomeBeanDynamicFiltering2() {
        SomeBeanForDynamicFiltering someBean = new SomeBeanForDynamicFiltering("value1", "value2", "password", "value4");
        return someBeanDynamicFiltering(someBean, "someBeanFilter", "value1", "value2");
    }

    private MappingJacksonValue someBeanDynamicFiltering(SomeBeanForDynamicFiltering someBean, String filterId,
                                                         String... values) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(values);

        filterProvider.addFilter("someBeanFilter", propertyFilter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filterProvider);
        return mapping;
    }


    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBean() {
        return Arrays.asList(
                new SomeBean("value11", "value12", "password", "value14"),
                new SomeBean("value21", "value22", "password", "value24"));
    }


}
