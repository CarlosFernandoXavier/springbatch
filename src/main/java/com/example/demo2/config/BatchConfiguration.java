package com.example.demo2.config;

import com.example.demo2.JobCompletionNotificationListener;
import com.example.demo2.PersonItemProcessor;
import com.example.demo2.PersonItemWriter;
import com.example.demo2.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Configuration
public class BatchConfiguration {

    @Bean
    public JdbcCursorItemReader<Person> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Person>()
                .name("personItemReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM people")
                .rowMapper(new DataClassRowMapper<>(Person.class))
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      JdbcCursorItemReader reader, PersonItemProcessor processor, PersonItemWriter writer) {
        return new StepBuilder("step1", jobRepository)
                .<List<Person>, List<Person>>chunk(1000, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public FileWriter fileWriter() {
        FileWriter myWriter = null;
        try {
            File myObj = new File("src/main/resources/filename.txt");
            myWriter = new FileWriter(myObj.getPath());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return myWriter;
    }

    @Bean
    public PersonItemWriter personItemWriter(FileWriter fileWriter) {
        return new PersonItemWriter(fileWriter);
    }

    @Bean
    public JobCompletionNotificationListener jobCompletionNotificationListener(JdbcTemplate jdbcTemplate,
                                                                               FileWriter fileWriter) {
        return new JobCompletionNotificationListener(jdbcTemplate, fileWriter);
    }
}
