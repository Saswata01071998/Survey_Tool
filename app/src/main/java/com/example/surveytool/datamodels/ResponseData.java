package com.example.surveytool.datamodels;

import io.realm.RealmObject;

public class ResponseData extends RealmObject {

    Integer response_id;
    String response;
    Integer question_id;

    public Integer getResponse_id() {
        return response_id;
    }

    public void setResponse_id(Integer response_id) {
        this.response_id = response_id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }
}
