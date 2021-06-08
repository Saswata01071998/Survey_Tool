package com.example.surveytool;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.SurveyData;


import io.realm.Realm;
import io.realm.RealmResults;

public class UserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RealmResults<SurveyData> realmResults;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_landing_page);

        recyclerView = findViewById(R.id.survey_list1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Realm realm = Realm.getDefaultInstance();
        realmResults = realm.where(SurveyData.class).findAll();

       UserSurveyAdapter adapter = new UserSurveyAdapter(this,realmResults);
       recyclerView.setAdapter(adapter);

    }

    public void gotoAdmin(View view) {
        Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
}
