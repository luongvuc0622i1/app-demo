package com.example.demoexceltopdf.dto;

public class ResponseData {
    private byte[] msg;

    public ResponseData() {
    }

    public ResponseData(byte[] msg) {
        this.msg = msg;
    }

    public byte[] getMsg() {
        return msg;
    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }
}
