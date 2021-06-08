package com.example.surveytool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.QuestionData;
import com.example.surveytool.datamodels.ResponseData;

import io.realm.Realm;
import io.realm.RealmResults;

public class SurveyAttemptAdapter extends RecyclerView.Adapter<SurveyAttemptAdapter.SurveyAttemptViewHolder>{

    Context ctx;
    RealmResults<QuestionData> realmResults;
    RealmResults<ResponseData> responseDataRealmResults;

    public SurveyAttemptAdapter(Context ctx, RealmResults<QuestionData> realmResults) {
        this.ctx = ctx;
        this.realmResults = realmResults;
    }

    @NonNull
    @Override
    public SurveyAttemptAdapter.SurveyAttemptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater2 = LayoutInflater.from(ctx);
        View view2 = inflater2.inflate(R.layout.survey_attempt_cardview , null);
        return new SurveyAttemptAdapter.SurveyAttemptViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyAttemptViewHolder holder, int position) {
        QuestionData obj= realmResults.get(position);
        holder.question.setText(obj.getQuestion_body());
        holder.qid.setText(obj.getQuestion_id());

        Realm realm = Realm.getDefaultInstance();
        responseDataRealmResults = realm.where(ResponseData.class).equalTo("question_id",obj.getQuestion_id()).findAll();
        holder.one.setText(responseDataRealmResults.get(0).getResponse());
        holder.op1.setText(responseDataRealmResults.get(0).getResponse_id());
        holder.two.setText(responseDataRealmResults.get(1).getResponse());
        holder.op2.setText(responseDataRealmResults.get(1).getResponse_id());
        holder.three.setText(responseDataRealmResults.get(2).getResponse());
        holder.op2.setText(responseDataRealmResults.get(2).getResponse_id());
        holder.four.setText(responseDataRealmResults.get(3).getResponse());
        holder.op3.setText(responseDataRealmResults.get(3).getResponse_id());

    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

     class SurveyAttemptViewHolder extends RecyclerView.ViewHolder{

        TextView question,op1,op2,op3,op4,qid;
        RadioButton one,two,three,four;
        public SurveyAttemptViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
            one=itemView.findViewById(R.id.one);
            two=itemView.findViewById(R.id.two);
            three=itemView.findViewById(R.id.three);
            four=itemView.findViewById(R.id.four);
            op1=itemView.findViewById(R.id.op1);
            op2=itemView.findViewById(R.id.op2);
            op3=itemView.findViewById(R.id.op3);
            op4=itemView.findViewById(R.id.op4);
            qid=itemView.findViewById(R.id.qid);
        }
    }
}
