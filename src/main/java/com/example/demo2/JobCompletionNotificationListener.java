package com.example.demo2;

import com.example.demo2.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;


@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;
    private FileWriter fileWriter;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, FileWriter fileWriter) {
        this.jdbcTemplate = jdbcTemplate;
        this.fileWriter = fileWriter;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Integer a = 90;
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            try {
                fileWriter.close();

                log.info("!!! JOB FINISHED! Time to verify the results");

                jdbcTemplate
                        .query("SELECT first_name, last_name FROM people", new DataClassRowMapper<>(Person.class))
                        .forEach(person -> log.info("Found <{{}}> in the database.", person));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}