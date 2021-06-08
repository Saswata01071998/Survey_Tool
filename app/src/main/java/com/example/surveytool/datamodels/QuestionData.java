package com.example.surveytool.datamodels;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class QuestionData extends RealmObject {

    String question_id;
    String servey_id;
    String question_body;




    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getServey_id() {
        return servey_id;
    }

    public void setServey_id(String servey_id) {
        this.servey_id = servey_id;
    }

    public String getQuestion_body() {
        return question_body;
    }

    public void setQuestion_body(String question_body) {
        this.question_body = question_body;
    }


}
