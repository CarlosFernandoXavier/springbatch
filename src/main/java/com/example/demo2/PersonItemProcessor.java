package com.example.demo2;

import com.example.demo2.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) {

      //  Person person = people.get(0);
        final String firstName = person.firstName().toUpperCase();
        final String lastName = person.lastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}