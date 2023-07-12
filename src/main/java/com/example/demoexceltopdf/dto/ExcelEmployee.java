package com.example.demoexceltopdf.dto;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class ExcelEmployee {
    @ExcelRow
    public int id;

    @ExcelCellName("Name")
    public String name;

    @ExcelCellName("Oraganization")
    public String oraganization;

    @ExcelCellName("Designation")
    public String designation;

    @ExcelCellName("Salary")
    public int salary;

    @Override
    public String toString() {
        return "EmployeeEx [id=" + id + ", name=" + name + ", oraganization=" + oraganization + ", designation=" + designation
                + ", salary=" + salary + "]";
    }
}
