package com.mangopay.teamcity.runscope.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestAssertion {
    private String source;
    private String comparison;
    private String actualValue;
    private String targetValue;
    private String error;
    private String property;
    private BinaryStatus result;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public String getActualValue() {
        return actualValue;
    }

	@JsonProperty("actual_value")
    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getTargetValue() {
        return targetValue;
    }

	@JsonProperty("target_value")
    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public BinaryStatus getResult() {
        return result;
    }

    public void setResult(BinaryStatus result) {
        this.result = result;
    }
}
