package com.example.demoexceltopdf.controller;

import com.example.demoexceltopdf.service.EmployeeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class GenerateReportController {

    @Autowired
    private EmployeeReportService employeeReportService;

    @GetMapping("/generate-report/{fileName}/{reportId}")
    public ResponseEntity<?> generateReport(@PathVariable String fileName, @RequestBody String filePath, @PathVariable int reportId) {
        String msg = employeeReportService.generateReport(fileName, filePath, reportId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    private List<String> list = new ArrayList<>();

    @GetMapping("/list-report")
    public ResponseEntity<?> getReportName() {
        list.add("employee-rpt");
        list.add("employee-rpt2");
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
