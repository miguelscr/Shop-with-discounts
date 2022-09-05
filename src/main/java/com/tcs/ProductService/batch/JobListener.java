package com.tcs.ProductService.batch;

import com.tcs.ProductService.models.ProductModel;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JobListener extends JobExecutionListenerSupport {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        ProductModel model = new ProductModel();
        List myList = new ArrayList<>();
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            jdbcTemplate
                    .query("SELECT name, unit_price FROM product",
                            (rs, row) -> myList.add(rs.getString(1)));
            model.setName((String) myList.get(0));

        }
    }
}
