package com.example.demoexceltopdf.dto;

public class ReqBody {
    private String fileName;
    private String filePath;
    private int reportId;

    public ReqBody() {};

    public ReqBody(String fileName, String filePath, int reportId) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.reportId = reportId;
    };

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}
