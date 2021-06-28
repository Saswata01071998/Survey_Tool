package com.example.surveytool;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.SurveyData;

import java.util.List;

import io.realm.RealmResults;

public class AdminSurveyAdapter extends RecyclerView.Adapter<AdminSurveyAdapter.AdminSurveyHolder> {

    Context ctx;
    RealmResults<SurveyData> survey_list;
    AppCompatActivity appcompat;

    public AdminSurveyAdapter(Context ctx, RealmResults<SurveyData> survey_list,AppCompatActivity appcompat) {
        this.ctx=ctx;
        this.survey_list=survey_list;
        this.appcompat=appcompat;

    }

    @NonNull
    @Override
    public AdminSurveyAdapter.AdminSurveyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater2 = LayoutInflater.from(ctx);
        View view2 = inflater2.inflate(R.layout.admin_cardview , null);
        return new AdminSurveyHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSurveyAdapter.AdminSurveyHolder holder, int position) {

        SurveyData surveyData=survey_list.get(position);
        holder.textView.setText(surveyData.getSurvey_name());
        holder.count.setText(surveyData.getSurvey_purpose().toString());
        holder.id.setText(surveyData.getSurvey_id());

    }

    @Override
    public int getItemCount() {
        return survey_list.size();
    }

    class AdminSurveyHolder extends RecyclerView.ViewHolder {

        TextView textView,count,id;
        Button del;
        public AdminSurveyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.survey_name2);
            count = itemView.findViewById(R.id.survey_count2);
            id=itemView.findViewById(R.id.survey_id2);
            del=itemView.findViewById(R.id.delete);

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DeleteSurvey deleteSurvey = new DeleteSurvey(id.getText().toString(),ctx);
                    deleteSurvey.deleteSurvey();
                    deleteSurvey.recreateApp(appcompat);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = id.getText().toString();
                    Intent intent = new Intent(ctx.getApplicationContext(),AdminSurveyResults.class);
                    intent.putExtra("id",s);
                    ctx.startActivity(intent);
                }
            });

        }
    }
}
