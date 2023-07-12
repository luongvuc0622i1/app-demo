package com.example.demoexceltopdf.service;

import com.example.demoexceltopdf.dto.Employee;
import com.example.demoexceltopdf.dto.ExcelEmployee;
import com.poiji.bind.Poiji;
import org.springframework.stereotype.Service;

import java.io.File;
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
    File file = new File("C:\\Users\\dev\\Downloads\\Report\\data1.xlsx");
    List<ExcelEmployee> invoices = Poiji.fromExcel(file, ExcelEmployee.class);

    private List<Employee> empList = new ArrayList<>();

    public String generateReport() {
        try {

            String reportPath = "C:\\Users\\dev\\Downloads\\Report";

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\employee-rpt.jrxml");

            for (ExcelEmployee e : invoices) {
                Employee empl = new Employee(e.id, e.name, e.oraganization, e.designation, e.salary);
                empList.add(empl);
            }

            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(empList);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "Websparrow.org");

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Emp-Rpt.pdf");

            System.out.println("Done");

            return "Report successfully generated @path= " + reportPath;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
