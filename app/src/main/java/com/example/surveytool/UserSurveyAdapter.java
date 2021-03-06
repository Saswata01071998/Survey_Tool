package com.example.surveytool;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveytool.datamodels.SurveyData;

import java.util.List;

import io.realm.RealmResults;

public class UserSurveyAdapter extends RecyclerView.Adapter<UserSurveyAdapter.UserSurveyHolder> {

        Context ctx;
        RealmResults<SurveyData> survey_list;

public UserSurveyAdapter(Context ctx, RealmResults<SurveyData> survey_list) {
        this.ctx=ctx;
        this.survey_list=survey_list;

        }

@NonNull
@Override
public UserSurveyAdapter.UserSurveyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater2 = LayoutInflater.from(ctx);
        View view2 = inflater2.inflate(R.layout.card_view , null);
        return new UserSurveyHolder(view2);
        }

@Override
public void onBindViewHolder(@NonNull UserSurveyAdapter.UserSurveyHolder holder, int position) {

        SurveyData surveyData=survey_list.get(position);
        holder.textView.setText(surveyData.getSurvey_name());
        holder.count.setText(surveyData.getSurvey_purpose());
        holder.id.setText(surveyData.getSurvey_id());
        }

@Override
public int getItemCount() {
        return survey_list.size();
        }

class UserSurveyHolder extends RecyclerView.ViewHolder {

    TextView textView,count,id;
    public UserSurveyHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.survey_name);
        count = itemView.findViewById(R.id.survey_count);
        id=itemView.findViewById(R.id.survey_id);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = id.getText().toString();
                Intent intent = new Intent(ctx.getApplicationContext(),SurveyAttemptActivity.class);
                intent.putExtra("id",s);
                ctx.startActivity(intent);
            }
        });

    }
}
}
