package com.example.demoexceltopdf.controller;

import com.example.demoexceltopdf.dto.ReqBody;
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

    @GetMapping("/generate-report")
    public ResponseEntity<?> generateReport(@RequestBody ReqBody reqBody) {
        String msg = employeeReportService.generateReport(reqBody.getFileName(), reqBody.getFilePath(), reqBody.getReportId());
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
