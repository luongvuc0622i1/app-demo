package com.example.demoexceltopdf.service;

import com.example.demoexceltopdf.dto.ReportItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportItemService {
    private static Map<Integer, ReportItem> items;

    static {
        items = new HashMap<>();
        items.put(1, new ReportItem(1, "employee-rpt", "Employee Report"));
        items.put(2, new ReportItem(2, "student-rpt", "Student Report"));
    }

    public List<ReportItem> findAll() {
        return new ArrayList<>(items.values());
    }
}
