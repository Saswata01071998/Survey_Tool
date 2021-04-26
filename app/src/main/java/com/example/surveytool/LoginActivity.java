package com.example.surveytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surveytool.datamodels.LoginData;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText password;
    TextView username;
    String u,p;
    LoginData loginData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        username=(TextView) findViewById(R.id.inputEmail);
        password = (TextInputEditText) findViewById(R.id.password);



    }

    public void login(View view) {

        u=username.getText().toString();
        p=password.getText().toString();

        Realm realm = Realm.getDefaultInstance();

        try {
                loginData = realm.where(LoginData.class).equalTo("username",u).findFirst();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Database Access Failure",Toast.LENGTH_LONG).show();
        }

        if(loginData!=null && loginData.getUsername().contentEquals(u) && loginData.getPassword().contentEquals(p))
        {
            username.setText("");
            password.setText("");
            Intent intent =new Intent(getApplicationContext(),AdminSurveyActivity.class);
            startActivity(intent);
        }
        else
        {
            username.setText("");
            password.setText("");
            Toast.makeText(getApplicationContext(), "Wrong Information" ,Toast.LENGTH_SHORT).show();
        }
        realm.close();
    }

    public void register_admin(View view) {

        Intent intent= new Intent(getApplicationContext(),AdminRegisterActivity.class);
        startActivity(intent);

    }
}