package com.example.surveytool;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.surveytool.datamodels.LoginData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class AdminRegisterActivity extends AppCompatActivity {

    long check;
    EditText username;
    String name,password;
    TextInputEditText pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_register_layout);

        username=(EditText) findViewById(R.id.username_register);
        pass = (TextInputEditText) findViewById(R.id.password_create);

    }

    public void register_data_insert(View view) {

        Realm realm1=null;

        try {
            realm1=Realm.getDefaultInstance();
            name = String.valueOf(username.getText());
            password = String.valueOf(pass.getText());
        }
        catch (Exception e)
        {
            Log.e("Registration","idhar problem");
            this.finish();
        }

        try {
            check = realm1.where(LoginData.class).equalTo("username",name).count();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
        }
        if(check>=1)
        {
            Toast.makeText(getApplicationContext(),"Sorry Already Exists",Toast.LENGTH_LONG).show();
            username.setText("");
            pass.setText("");
        }
        else
        {
            realm1.beginTransaction();
            try {
                LoginData ld = realm1.createObject(LoginData.class, UUID.randomUUID().toString());
                ld.setUsername(name);
                ld.setPassword(password);

                realm1.commitTransaction();
            }
            catch (Exception e)
            {
                realm1.cancelTransaction();
                Toast.makeText(getApplicationContext(),"Database Input Failure",Toast.LENGTH_LONG).show();
            }
            finally {
                if(realm1!=null)
                    realm1.close();

            }
            this.finish();
        }

    }
}
