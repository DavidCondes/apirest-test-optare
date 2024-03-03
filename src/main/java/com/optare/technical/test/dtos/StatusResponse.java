package com.optare.technical.test.dtos;

import com.optare.technical.test.models.IpAddress;

public class StatusResponse {
    private boolean success;
    private String message;

    private IpAddress ipAddress;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void markAsSucced(String message) {
        this.success = true;
        this.message = message;
    }

    public void markAsFailed(String message) {
        this.success = false;
        this.message = message;
    }
}
