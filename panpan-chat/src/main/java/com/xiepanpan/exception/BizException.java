package com.xiepanpan.exception;

/**
 * @author: xiepanpan
 * @Date: 2020/8/2 0002
 * @Description:
 */
public class BizException extends RuntimeException {

    private boolean success;
    private String errorMsg;


    public BizException(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }
}