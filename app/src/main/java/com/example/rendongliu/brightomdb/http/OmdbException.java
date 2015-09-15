package com.example.rendongliu.brightomdb.http;

/**
 * Created by rendong.liu on 15/09/15.
 */
public class OmdbException extends Exception {
    private ErrorType errorType;
    private int http_code;

    public ErrorType getErrorType() {
        return errorType;
    }

    public int getHttp_code() {
        return http_code;
    }

    public OmdbException(String msg, int code) {
        super(msg);
        this.errorType=errorType.ServerError;
        this.http_code = code;
    }

    public OmdbException(String msg) {
        super(msg);
        this.errorType=errorType.ClientError;
    }


    public enum ErrorType {
        ServerError,
        ClientError
    }
}
