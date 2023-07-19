package com.example.demoexceltopdf.service;

import com.example.demoexceltopdf.dto.Employee;
import com.example.demoexceltopdf.dto.ExcelEmployee;
import com.example.demoexceltopdf.dto.ReportItem;
import com.example.demoexceltopdf.dto.ResponseData;
import com.poiji.bind.Poiji;
import net.sf.jasperreports.engine.data.ExcelDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
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
    private String reportPath = "C:\\Users\\dev\\Downloads\\Report\\";

    public ResponseData generateReport(String fileName, String filePath, int reportId) {
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        URL url = null;

        try {
            url = new URL(filePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(".\\aaa.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(".\\aaa.xlsx");

        List<ExcelEmployee> invoices = Poiji.fromExcel(file, ExcelEmployee.class);

        file.delete();

        List<Employee> empList = new ArrayList<>();

        String reportName = "";
        ReportItemService reportItemService = new ReportItemService();
        List<ReportItem> items = reportItemService.findAll();
        for (ReportItem reportItem: items) {
            if (reportItem.getId() == reportId) {
                reportName = reportItem.getName();
            }
        }

        try {

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + reportName + ".jrxml");

            for (ExcelEmployee e : invoices) {
                Employee empl = new Employee(e.id, e.name, e.oraganization, e.designation, e.salary);
                empList.add(empl);
            }

            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(empList);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "PMS Team");

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            exporter.exportReport();
            return new ResponseData(byteArrayOutputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
