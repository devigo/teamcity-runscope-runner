package com.mangopay.teamcity.runscope.client;

import com.mangopay.teamcity.runscope.model.Test;

class TestResponse extends Response {
    private Test data;

    public Test getData() {
        return data;
    }

    public void setData(Test data) {
        this.data = data;
    }
}