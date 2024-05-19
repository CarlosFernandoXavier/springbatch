package com.example.demo2;

import com.example.demo2.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class PersonItemWriter implements ItemWriter<Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemWriter.class);
    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        log.info("Escrevendo no banco");
    }
}
