package com.example.demo2;

import com.example.demo2.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;


@Component
public class PersonItemWriter implements ItemWriter<Person> {

    private FileWriter fileWriter;

    private static final Logger log = LoggerFactory.getLogger(PersonItemWriter.class);

    public PersonItemWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void write(Chunk<? extends Person> chunk) {
        try {
            fileWriter.write("Cabeçalho do arquivo" + System.lineSeparator());
            chunk.getItems().forEach(person -> {
                try {
                    fileWriter.write(person.firstName() + "\t\t\t\t\t\t" + person.lastName()+ System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.write("Rodapé do arquivo" + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
