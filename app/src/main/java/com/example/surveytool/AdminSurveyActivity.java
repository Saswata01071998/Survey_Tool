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

import io.realm.Realm;
import io.realm.RealmResults;

public class AdminSurveyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RealmResults<SurveyData> realmResults;
    String adminId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_admin_layout);

        Intent intent = getIntent();
        adminId=intent.getStringExtra("id");

        recyclerView = findViewById(R.id.survey_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Realm realm = Realm.getDefaultInstance();
        realmResults = realm.where(SurveyData.class).equalTo("admin_id",adminId).findAll();


        AdminSurveyAdapter adapter = new AdminSurveyAdapter(this,realmResults);
        recyclerView.setAdapter(adapter);

    }
    

    public void addSurvey(View view) {

        Intent intent = new Intent(getApplicationContext(),SurveyCreate.class);
        intent.putExtra("id",adminId);
        startActivityForResult(intent,2);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Realm realm = Realm.getDefaultInstance();
        realmResults = realm.where(SurveyData.class).equalTo("admin_id",adminId).findAll();


        AdminSurveyAdapter adapter = new AdminSurveyAdapter(this,realmResults);
        recyclerView.setAdapter(adapter);
    }
}
