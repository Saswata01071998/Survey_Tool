package com.example.surveytool.datamodels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ResponseData extends RealmObject {

    @PrimaryKey
    String response_id;

    String response;
    String question_id;
    Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getResponse_id() {
        return response_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
}
