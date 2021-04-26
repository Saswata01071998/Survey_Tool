package com.example.surveytool.datamodels;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class QuestionData extends RealmObject {

    Integer question_id;
    Integer servey_id;
    String question_body;
    RealmList<String> options;

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getServey_id() {
        return servey_id;
    }

    public void setServey_id(Integer servey_id) {
        this.servey_id = servey_id;
    }

    public String getQuestion_body() {
        return question_body;
    }

    public void setQuestion_body(String question_body) {
        this.question_body = question_body;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(RealmList<String> options) {
        this.options = options;
    }
}
