package com.example.surveytool;

import java.util.ArrayList;
import java.util.List;

public class QuestionBusinessModel {

    String question;
    List<String> option = new ArrayList<String>();
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestionBusinessModel(String question, List<String> option) {
        this.question = question;
        this.option.add(option.get(0));
        this.option.add(option.get(1));
        this.option.add(option.get(2));
        this.option.add(option.get(3));

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }
}
