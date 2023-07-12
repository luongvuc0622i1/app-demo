package com.example.demoexceltopdf;

import com.example.demoexceltopdf.service.EmployeeReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoExcelToPdfApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoExcelToPdfApplication.class, args);
    }

    @Bean
    public String generateReport(final EmployeeReportService employeeReportService) {
        String msg = employeeReportService.generateReport();

        System.out.println(msg);

        return msg;
    }

}
