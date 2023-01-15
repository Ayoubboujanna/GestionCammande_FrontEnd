package com.example.clientstock;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                if(Objects.equals(username.getText().toString(), "admin")&&Objects.equals(password.getText().toString(),"admin"))
                {
                    Toast.makeText(LoginActivity.this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }
                if(Objects.equals(username.getText().toString(), "ayoub")&&Objects.equals(password.getText().toString(),"boujanna"))
                {
                    Toast.makeText(LoginActivity.this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                if(Objects.equals(username.getText().toString(), "mohamed")&&Objects.equals(password.getText().toString(),"ettaouanty"))
                {
                    Toast.makeText(LoginActivity.this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }else
                {
                    Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}