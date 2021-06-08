package com.example.surveytool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.QuestionData;
import com.example.surveytool.datamodels.ResponseData;
import com.example.surveytool.datamodels.SurveyData;

import io.realm.Realm;
import io.realm.RealmResults;

public class SurveyAttemptActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String survey_id;
    RealmResults<QuestionData> realmResults;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_attempt_layout);


        recyclerView=findViewById(R.id.survey_attempt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        survey_id= intent.getStringExtra("id");


        Realm realm=Realm.getDefaultInstance();
        realmResults=realm.where(QuestionData.class).equalTo("servey_id",survey_id).findAll();

        SurveyAttemptAdapter surveyAttemptAdapter = new SurveyAttemptAdapter(this,realmResults);
        recyclerView.setAdapter(surveyAttemptAdapter);


    }

    public void submitSurveyData(View view) {

        Realm realm = Realm.getDefaultInstance();
        RadioButton radioButton;
        TextView qid;
        int childCount=recyclerView.getChildCount();
        for(int i=0;i<childCount;i++)
        {
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
            RadioGroup radioGroup= holder.itemView.findViewById(R.id.options_radio);
            radioButton = holder.itemView.findViewById(radioGroup.getCheckedRadioButtonId());
            qid=holder.itemView.findViewById(R.id.qid);
            String question_id=qid.getText().toString();
            String option=radioButton.getText().toString();

            realm.beginTransaction();
            try {
                ResponseData responseData = realm.where(ResponseData.class).equalTo("question_id",question_id).equalTo("response",option).findFirst();
                responseData.setCount(responseData.getCount()+1);
                realm.commitTransaction();
            }
            catch (Exception e)
            {
                realm.cancelTransaction();
                Toast.makeText(getApplicationContext(),"survey attempt database failure",Toast.LENGTH_LONG).show();
            }

        }
        realm.beginTransaction();
        try {
            SurveyData surveyData = realm.where(SurveyData.class).equalTo("survey_id",survey_id).findFirst();
            surveyData.setAtempts(surveyData.getAtempts()+1);
            realm.commitTransaction();
        }
        catch (Exception e)
        {
            realm.cancelTransaction();
            Toast.makeText(getApplicationContext(),"survey attempt count database failure",Toast.LENGTH_LONG).show();
        }
        realm.close();
        this.finish();
    }
}
