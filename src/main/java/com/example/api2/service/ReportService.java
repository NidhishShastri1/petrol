package com.example.api2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api2.model.CustomerReport;
import com.example.api2.repository.CustomerRepository; // Assuming you have a repository class for customer data

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private CustomerRepository customerRepository;

    // Fetch report data from the database
    public List<CustomerReport> getAllCustomerReports() {
        return customerRepository.findAllCustomerReports(); // A custom query to fetch the report data
    }
}

