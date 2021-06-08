package com.example.surveytool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.surveytool.datamodels.QuestionData;
import com.example.surveytool.datamodels.ResponseData;
import com.example.surveytool.datamodels.SurveyData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

public class SurveyCreate extends AppCompatActivity {



    EditText question,sname,spurpose,option1,option2,option3,option4;
    List<String> option_list = new ArrayList<String>();
    List<QuestionBusinessModel> questionData = new ArrayList<QuestionBusinessModel>();
    String adminId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_survey_layout);

        Intent intent = getIntent();
        adminId=intent.getStringExtra("id");

        option1=(EditText) findViewById(R.id.option1);
        option2=(EditText) findViewById(R.id.option2);
        option3=(EditText) findViewById(R.id.option3);
        option4=(EditText) findViewById(R.id.option4);
        question=(EditText)findViewById(R.id.question);
        sname=(EditText)findViewById(R.id.survey_name);
        spurpose=(EditText)findViewById(R.id.survey_purpose);
    }



    public void addQuestion(View view) {

        if (question.getText().toString().equals("") || option1.getText().toString().equals("") || option2.getText().toString().equals("") || option3.getText().toString().equals("")|| option4.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill all fields properly",Toast.LENGTH_LONG).show();
        }
        else {
            option_list.add(option1.getText().toString());
            option_list.add(option2.getText().toString());
            option_list.add(option3.getText().toString());
            option_list.add(option4.getText().toString());
            questionData.add(new QuestionBusinessModel(question.getText().toString(), option_list));
            option_list.clear();
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
            question.setText("");
        }
    }

    public void submitData(View view) {

        int f=1;

        addQuestion(view);

        String id = UUID.randomUUID().toString();

        Realm realm =Realm.getDefaultInstance();
        realm.beginTransaction();
        try
        {
            SurveyData surveyData = realm.createObject(SurveyData.class);
            surveyData.setSurvey_id(id);
            surveyData.setSurvey_name(sname.getText().toString());
            surveyData.setSurvey_purpose(spurpose.getText().toString());
            surveyData.setAdmin_id(adminId);
            surveyData.setAtempts(0);
            realm.commitTransaction();
        }
        catch (Exception e)
        {
            realm.cancelTransaction();
            Toast.makeText(getApplicationContext(),"Database Input Failure survey basic",Toast.LENGTH_LONG).show();
            f=0;
        }
        realm.beginTransaction();
        try {

            for(int i=0;i<questionData.size();i++) {

                String qid = UUID.randomUUID().toString();
                QuestionData questionobj = realm.createObject(QuestionData.class);
                questionobj.setServey_id(id);
                questionobj.setQuestion_body(questionData.get(i).getQuestion());
                questionobj.setQuestion_id(qid);
                questionData.get(i).setId(qid);

            }
            realm.commitTransaction();

        }
        catch (Exception e){
            realm.cancelTransaction();
            Toast.makeText(getApplicationContext(),"Database Input Failure Question",Toast.LENGTH_LONG).show();
            f=0;
        }



            for (int i = 0; i < questionData.size(); i++) {

                realm.beginTransaction();
                try {

                for (int j = 0; j < 4; j++) {
                ResponseData obj = realm.createObject(ResponseData.class, UUID.randomUUID().toString());
                obj.setCount(0);
                obj.setQuestion_id(questionData.get(i).getId());
                obj.setResponse(questionData.get(i).getOption().get(j));

            }
                    realm.commitTransaction();
        }
                catch (Exception e)
                {
                    realm.cancelTransaction();
                    Toast.makeText(getApplicationContext(),"Database Input Failure Response "+i,Toast.LENGTH_LONG).show();
                    f=0;
                }


        }
            if(f==1)
                this.finish();



    }
}
