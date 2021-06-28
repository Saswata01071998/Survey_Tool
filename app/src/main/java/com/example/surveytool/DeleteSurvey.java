package com.example.surveytool;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surveytool.datamodels.QuestionData;
import com.example.surveytool.datamodels.ResponseData;
import com.example.surveytool.datamodels.SurveyData;

import io.realm.Realm;
import io.realm.RealmResults;

public class DeleteSurvey {
    String id;
    RealmResults<QuestionData> questionData;
    Context ctx;

    public DeleteSurvey(String id, Context ctx) {
        this.id = id;
        this.ctx = ctx;
    }

    public void deleteSurvey()
    {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        try
        {
            realm.where(SurveyData.class).equalTo("survey_id",id).findAll().deleteAllFromRealm();
            realm.commitTransaction();
        }
        catch (Exception e)
        {
            realm.cancelTransaction();
            Toast.makeText(ctx.getApplicationContext(),"Database Delete 1 Failure  ",Toast.LENGTH_LONG).show();
        }

        questionData=realm.where(QuestionData.class).equalTo("servey_id",id).findAll();



        for(int i=0;i<questionData.size();i++)
        {
            realm.beginTransaction();

            try{
                realm.where(ResponseData.class).equalTo("question_id",questionData.get(i).getQuestion_id()).findAll().deleteAllFromRealm();
                realm.commitTransaction();

            }catch (Exception e){
                realm.cancelTransaction();
                Toast.makeText(ctx.getApplicationContext(),"Database Delete 3 Failure  "+i,Toast.LENGTH_LONG).show();

            }

        }
        realm.beginTransaction();
        try
        {

            realm.where(QuestionData.class).equalTo("servey_id",id).findAll().deleteAllFromRealm();
            realm.commitTransaction();
        }
        catch (Exception e)
        {
            realm.cancelTransaction();
            Toast.makeText(ctx.getApplicationContext(),"Database Delete 2 Failure  ",Toast.LENGTH_LONG).show();
        }
        realm.close();

    }
    public void recreateApp(AppCompatActivity appCompatActivity)
    {
        appCompatActivity.recreate();
    }
    public void finishApp(AppCompatActivity appCompatActivity)
    {
        appCompatActivity.finish();
    }
}
