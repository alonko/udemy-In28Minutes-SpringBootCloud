package com.ak.minutes.rest.webservices.akrestfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
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
        return new SomeBean("value1", "value2", "value3", "value4", "value5", "value6");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBean() {
        return Arrays.asList(new SomeBean("value11", "value12", "value13", "value14", "value15", "value16"), new SomeBean("value21", "value22", "value23", "value24", "value25", "value26"));
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue retrieveSomeBeanDynamically() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3", "value4", "value5", "value6");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field5", "field6");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);

        mapping.setFilters(filters);
        return mapping;
    }


    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue retrieveSomeBeansListDynamically() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value11", "value12", "value13", "value14", "value15", "value16"), new SomeBean("value21", "value22", "value23", "value24", "value25", "value26"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field4");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);

        mapping.setFilters(filters);
        return mapping;
    }
}
