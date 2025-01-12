package com.example.api2.controller;

import com.example.api2.model.CustomerReport;
import com.example.api2.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for your front-end
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // API to fetch all customer reports
    @GetMapping("/customer")
    public ResponseEntity<List<CustomerReport>> getCustomerReports() {
        List<CustomerReport> reports = reportService.getAllCustomerReports();
        return ResponseEntity.ok(reports); // Return the list of customer reports
    }
}

