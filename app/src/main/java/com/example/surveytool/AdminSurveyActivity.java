package com.example.surveytool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.SurveyData;

import java.util.ArrayList;
import java.util.List;

public class AdminSurveyActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_admin_layout);

        recyclerView = findViewById(R.id.survey_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //FOR TESTING ONLY --->>
        List<SurveyData> surveyDataList = new ArrayList<SurveyData>();
        surveyDataList.add(new SurveyData(1,"Food Survey",""));
        surveyDataList.add(new SurveyData(2,"Railway Survey",""));
        surveyDataList.add(new SurveyData(3,"Airport Survey",""));
        surveyDataList.add(new SurveyData(4,"Metro Survey",""));
        surveyDataList.add(new SurveyData(5,"Restaurant Survey",""));

        AdminSurveyAdapter adapter = new AdminSurveyAdapter(this,surveyDataList);
        recyclerView.setAdapter(adapter);

    }
    

    public void addSurvey(View view) {
        Intent intent = new Intent(getApplicationContext(),SurveyCreate.class);
        startActivity(intent);

    }
}
