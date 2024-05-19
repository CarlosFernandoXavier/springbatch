//package com.example.demo2;

import com.example.demo2.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/*public class PersonItemReader implements ItemReader {


    private static final Logger log = LoggerFactory.getLogger(PersonItemReader.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        try {
            Integer c = 90;
            List<Person> people = jdbcTemplate
                    .query("SELECT * FROM people", new DataClassRowMapper<>(Person.class));

            Integer a = 90;
            return people;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }*/
//}