package com.example.surveytool;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.QuestionData;
import com.example.surveytool.datamodels.SurveyData;

import io.realm.Realm;
import io.realm.RealmResults;

public class AdminSurveyResults extends AppCompatActivity {

    RecyclerView recyclerView;
    String survey_id;
    RealmResults<QuestionData> realmResults;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_results_layout);

        recyclerView=findViewById(R.id.survey_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        survey_id= intent.getStringExtra("id");


        Realm realm=Realm.getDefaultInstance();
        realmResults=realm.where(QuestionData.class).equalTo("servey_id",survey_id).findAll();
        int attempts= realm.where(SurveyData.class).equalTo("survey_id",survey_id).findFirst().getAtempts();

        AdminResultAdapter adapter = new AdminResultAdapter(this,realmResults,attempts);
        recyclerView.setAdapter(adapter);
    }
}
