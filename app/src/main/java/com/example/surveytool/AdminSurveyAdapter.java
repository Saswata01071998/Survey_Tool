package com.example.surveytool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.SurveyData;

import java.util.List;

public class AdminSurveyAdapter extends RecyclerView.Adapter<AdminSurveyAdapter.AdminSurveyHolder> {

    Context ctx;
    List<SurveyData> survey_list;

    public AdminSurveyAdapter(Context ctx, List<SurveyData> survey_list) {
        this.ctx=ctx;
        this.survey_list=survey_list;

    }

    @NonNull
    @Override
    public AdminSurveyAdapter.AdminSurveyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater2 = LayoutInflater.from(ctx);
        View view2 = inflater2.inflate(R.layout.card_view , null);
        return new AdminSurveyHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSurveyAdapter.AdminSurveyHolder holder, int position) {

        SurveyData surveyData=survey_list.get(position);
        holder.textView.setText(surveyData.getSurvey_name());
        holder.count.setText(surveyData.getSurvey_id().toString());

    }

    @Override
    public int getItemCount() {
        return survey_list.size();
    }

    class AdminSurveyHolder extends RecyclerView.ViewHolder {

        TextView textView,count;
        public AdminSurveyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.survey_name);
            count = itemView.findViewById(R.id.survey_count);

        }
    }
}
