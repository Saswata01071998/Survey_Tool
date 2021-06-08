package com.example.surveytool.datamodels;

import io.realm.RealmObject;

public class SurveyData extends RealmObject {

    String survey_id;
    String survey_name;
    String survey_purpose;
    String admin_id;
    Integer atempts;

    public Integer getAtempts() {
        return atempts;
    }

    public void setAtempts(Integer atempts) {
        this.atempts = atempts;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getSurvey_purpose() {
        return survey_purpose;
    }

    public void setSurvey_purpose(String survey_purpose) {
        this.survey_purpose = survey_purpose;
    }

    public SurveyData() {
    }

    public SurveyData(String survey_id, String survey_name, String survey_purpose) {
        this.survey_id = survey_id;
        this.survey_name = survey_name;
        this.survey_purpose = survey_purpose;
    }



    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getSurvey_name() {
        return survey_name;
    }

    public void setSurvey_name(String survey_name) {
        this.survey_name = survey_name;
    }

    public String getSurvey_id() {
        return survey_id;
    }
}
