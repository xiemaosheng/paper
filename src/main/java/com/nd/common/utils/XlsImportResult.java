package com.nd.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0011.
 */
public class XlsImportResult {

    static Logger logger = LoggerFactory.getLogger(XlsImportResult.class);
    @JsonIgnore
    private boolean xlsError;

    private boolean success = true;
    private List<Error> errors = new ArrayList<>();

    public boolean isXlsError() {
        return xlsError;
    }

    public void addError(String message) {
        success = false;
        errors.add(new Error(message));
    }

    public void addError(int rowIdx, int colIdx, String message) {
        success = false;
        xlsError = true;
        errors.add(new Error(message, rowIdx, colIdx));
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = new ArrayList<>(errors);
    }

    public static class Error {
        public Position position;
        public String message;

        public Error(String message) {
            this.message = message;
        }

        public Error(String message, int rowIdx, int colIdx) {
            this.message = message;
            this.position = new Position(rowIdx, colIdx);
        }
    }

    public static class Position {
        public int rowIdx;
        public int colIdx;

        public Position(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }
}
