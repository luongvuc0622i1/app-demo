package com.example.demoexceltopdf.service;

import com.example.demoexceltopdf.dto.Employee;
import com.example.demoexceltopdf.dto.ExcelEmployee;
import com.poiji.bind.Poiji;
import net.sf.jasperreports.engine.data.ExcelDataSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeReportService {

    public String generateReport(String fileName, String filePath, int reportId) {

        URL url = null;

        try {
            url = new URL(filePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get("C:\\Users\\dev\\Downloads\\Report\\aaa.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("C:\\Users\\dev\\Downloads\\Report\\aaa.xlsx");

        List<ExcelEmployee> invoices = Poiji.fromExcel(file, ExcelEmployee.class);

        List<Employee> empList = new ArrayList<>();

        String reportName = "";
        if (reportId == 1) {
            reportName = "employee-rpt";
        }

        try {

            String reportPath = "C:\\Users\\dev\\Downloads\\Report";

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\"+reportName+".jrxml");

            for (ExcelEmployee e : invoices) {
                Employee empl = new Employee(e.id, e.name, e.oraganization, e.designation, e.salary);
                empList.add(empl);
            }

            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(empList);

            ExcelDataSource excelDataSource;

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "PMS Team");

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\"+fileName+".pdf");

            System.out.println("Done");

            return "Report successfully generated @path= " + reportPath;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
