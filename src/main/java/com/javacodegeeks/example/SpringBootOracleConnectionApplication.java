package com.javacodegeeks.example;

import dnl.utils.text.table.TextTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootOracleConnectionApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOracleConnectionApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println("Reading employees records...");
        String[] columnHeaders = {
                "First Name",
                "Last Name",
                "Email",
                "Phone Number",
                "Salary"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);

        jdbcTemplate.query("SELECT * FROM employees", (rs) -> {
            Object[] row = {rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("phone_number"), rs.getString("salary")};
            tableModel.addRow(row);
        });

        TextTable tt = new TextTable(tableModel);
        tt.setAddRowNumbering(true);
        tt.setSort(0);
        tt.printTable();
    }
}
