package com.example.planpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {


    Button login_btn, rgstr_btn;

    EditText rgstr_username, rgstr_passwd;

    DBUser mydb;

    @Override
    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.register);

        rgstr_username = (EditText) findViewById(R.id.rgstr_username);
        rgstr_passwd = (EditText) findViewById(R.id.rgstr_password);

        login_btn = (Button) findViewById(R.id.rgstr_login_btn);
        rgstr_btn = (Button) findViewById(R.id.rgstr_signup_btn);

        mydb = new DBUser(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Register.this, LogIn.class);
                startActivity(in);
            }
        });

        rgstr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = rgstr_username.getText().toString();
                String password = rgstr_passwd.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(Register.this, "Do not leave empty input boxes!", Toast.LENGTH_SHORT).show();
                }else{

                    Boolean userCheck = mydb.userExist(username);

                    if(userCheck){
                        Toast.makeText(Register.this, "Username Already Exist!", Toast.LENGTH_SHORT).show();
                    }else{
                        Boolean insert = mydb.userRegister(username, password);
                        if (insert){
                            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
}
