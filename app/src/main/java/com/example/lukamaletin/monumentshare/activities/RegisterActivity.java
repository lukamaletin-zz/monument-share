package com.example.lukamaletin.monumentshare.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.database.dao.wrappers.UserDAOWrapper;
import com.example.lukamaletin.monumentshare.models.User;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    @Bean
    UserDAOWrapper userDAOWrapper;

    @ViewById
    EditText username;

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @ViewById
    EditText passwordConfirmation;

    @Click
    @EditorAction(R.id.passwordConfirmation)
    void register() {
        final User user = new User();

        if (username.getText().toString().equals("")) {
            Toast.makeText(this, R.string.username_required, Toast.LENGTH_SHORT).show();
            return;
        } else {
            user.setUsername(username.getText().toString());
        }

        if (email.getText().toString().equals("")) {
            Toast.makeText(this, R.string.email_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!userDAOWrapper.emailAvailable(email.getText().toString())) {
            Toast.makeText(this, R.string.email_unavailable, Toast.LENGTH_SHORT).show();
            return;
        } else {
            user.setEmail(email.getText().toString());
        }

        if (password.getText().toString().equals("")) {
            Toast.makeText(this, R.string.password_required, Toast.LENGTH_SHORT).show();
            return;
        } else {
            user.setPassword(password.getText().toString());
        }

        if (!password.getText().toString().equals(passwordConfirmation.getText().toString())) {
            Toast.makeText(this, R.string.password_unmatched, Toast.LENGTH_SHORT).show();
            return;
        }

        userDAOWrapper.registerUser(user);
        Toast.makeText(this, R.string.registration_success, Toast.LENGTH_SHORT).show();
        NavigationActivity_.intent(this).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
