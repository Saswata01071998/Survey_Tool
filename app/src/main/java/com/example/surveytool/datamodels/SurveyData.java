package com.example.surveytool.datamodels;

import io.realm.RealmObject;

public class SurveyData extends RealmObject {
    Integer survey_id;
    String survey_name;
    String survey_purpose;

    public String getSurvey_purpose() {
        return survey_purpose;
    }

    public void setSurvey_purpose(String survey_purpose) {
        this.survey_purpose = survey_purpose;
    }

    public SurveyData() {
    }

    public SurveyData(Integer survey_id, String survey_name, String survey_purpose) {
        this.survey_id = survey_id;
        this.survey_name = survey_name;
        this.survey_purpose = survey_purpose;
    }

    public Integer getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(Integer survey_id) {
        this.survey_id = survey_id;
    }

    public String getSurvey_name() {
        return survey_name;
    }

    public void setSurvey_name(String survey_name) {
        this.survey_name = survey_name;
    }
}
