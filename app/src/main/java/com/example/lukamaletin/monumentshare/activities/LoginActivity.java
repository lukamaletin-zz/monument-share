package com.example.lukamaletin.monumentshare.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.database.dao.wrappers.UserDAOWrapper;
import com.example.lukamaletin.monumentshare.utils.Preferences_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @Bean
    UserDAOWrapper userDAOWrapper;

    @Pref
    Preferences_ preferences;

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @Override
    protected void onResume() {
        if (preferences.id().exists()) {
            NavigationActivity_.intent(this).start();
        }
        super.onResume();
    }

    @Click
    @EditorAction(R.id.password)
    void login() {
        if (email.getText().toString().equals("")) {
            Toast.makeText(this, R.string.email_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.getText().toString().equals("")) {
            Toast.makeText(this, R.string.password_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!userDAOWrapper.authenticateUser(email.getText().toString(), password.getText().toString())) {
            Toast.makeText(this, R.string.wrong_credentials, Toast.LENGTH_SHORT).show();
            return;
        }

        NavigationActivity_.intent(this).start();
    }

    @Click
    void register() {
        RegisterActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_NO_HISTORY).start();
    }
}
