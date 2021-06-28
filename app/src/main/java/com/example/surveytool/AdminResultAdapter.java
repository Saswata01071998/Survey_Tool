package com.example.surveytool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.QuestionData;
import com.example.surveytool.datamodels.ResponseData;

import io.realm.Realm;
import io.realm.RealmResults;

public class AdminResultAdapter extends RecyclerView.Adapter<AdminResultAdapter.AdminResultViewHolder> {

    Context ctx;
    RealmResults<QuestionData> realmResults;
    RealmResults<ResponseData> responseDataRealmResults;
    Integer total_attempts;

    public AdminResultAdapter(Context ctx, RealmResults<QuestionData> realmResults, Integer total_attempts) {
        this.ctx = ctx;
        this.realmResults = realmResults;
        this.total_attempts = total_attempts;
        if(this.total_attempts==0)
            total_attempts=1;
    }

    @NonNull
    @Override
    public AdminResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater2 = LayoutInflater.from(ctx);
        View view2 = inflater2.inflate(R.layout.survey_result_cardview , null);
        return new AdminResultAdapter.AdminResultViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminResultViewHolder holder, int position) {

        int count,chk=0;
        QuestionData obj= realmResults.get(position);
        holder.q.setText(obj.getQuestion_body());

        Realm realm = Realm.getDefaultInstance();
        responseDataRealmResults = realm.where(ResponseData.class).equalTo("question_id",obj.getQuestion_id()).findAll();
        holder.op1.setText(responseDataRealmResults.get(0).getResponse());
        count=responseDataRealmResults.get(0).getCount();
        if(total_attempts!=0){
        chk=(100*count/total_attempts);
        holder.p1.setProgress(chk);}


        holder.op2.setText(responseDataRealmResults.get(1).getResponse());
        count=responseDataRealmResults.get(1).getCount();
        if(total_attempts!=0){
        chk=(100*count/total_attempts);
        holder.p2.setProgress(chk);}

        holder.op3.setText(responseDataRealmResults.get(2).getResponse());
        count=responseDataRealmResults.get(2).getCount();
        if(total_attempts!=0){
        chk=(100*count/total_attempts);
        holder.p3.setProgress(chk);}

        holder.op4.setText(responseDataRealmResults.get(3).getResponse());
        count=responseDataRealmResults.get(3).getCount();
        if(total_attempts!=0){
        chk=(100*count/total_attempts);
        holder.p4.setProgress(chk);}
    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

    public class AdminResultViewHolder extends RecyclerView.ViewHolder{

        TextView q,op1,op2,op3,op4;
        ProgressBar p1,p2,p3,p4;
        public AdminResultViewHolder(@NonNull View itemView) {
            super(itemView);
            q= itemView.findViewById(R.id.question_res);
            op1= itemView.findViewById(R.id.option1_result);
            op2= itemView.findViewById(R.id.option2_result);
            op3= itemView.findViewById(R.id.option3_result);
            op4= itemView.findViewById(R.id.option4_result);
            p1= itemView.findViewById(R.id.pbar1);
            p2= itemView.findViewById(R.id.pbar2);
            p3= itemView.findViewById(R.id.pbar3);
            p4= itemView.findViewById(R.id.pbar4);


        }
    }
}
