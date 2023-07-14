package com.example.demoexceltopdf.controller;

import com.example.demoexceltopdf.dto.ReportItem;
import com.example.demoexceltopdf.dto.ReqBody;
import com.example.demoexceltopdf.service.EmployeeReportService;
import com.example.demoexceltopdf.service.ReportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class GenerateReportController {

    @Autowired
    private EmployeeReportService employeeReportService;

    @Autowired
    private ReportItemService reportItemService;

    @PostMapping("/generate-report")
    public ResponseEntity<?> generateReport(@RequestBody ReqBody reqBody) {
        String msg = employeeReportService.generateReport(reqBody.getFileName(), reqBody.getFilePath(), reqBody.getReportId());
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/list-report")
    public ResponseEntity<?> getReportName() {
        List<ReportItem> items = reportItemService.findAll();
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
}
