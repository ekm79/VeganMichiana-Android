package com.lambdaschool.veganmichianaguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lambdaschool.veganmichianaguide.apiaccess.VeganMichianaDao;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        username = findViewById(R.id.username);
                        password = findViewById(R.id.password);
                        String user = username.getText().toString();
                        String pass = password.getText().toString();
                        VeganMichianaDao.logIn(user, pass);
//                        if (VeganMichianaDao.logIn(user, pass) != null) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//                        }
                    }
                }).start();

            }

        });
    }
}
