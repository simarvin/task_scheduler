package com.example.planpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LogIn extends Activity {

    EditText log_username, log_password;
    Button login_btn, register_btn;
    DBUser mydb;

    public static final String Extra_Username = "username";

    @Override
    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.log_in);

        log_username = (EditText) findViewById(R.id.login_username);
        log_password = (EditText) findViewById(R.id.login_password);

        login_btn = (Button) findViewById(R.id.login_login_btn);
        register_btn = (Button)  findViewById(R.id.login_signup_btn);

        mydb = new DBUser(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = log_username.getText().toString();
                String password = log_password.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(LogIn.this, "Please Complete the Input", Toast.LENGTH_SHORT).show();
                }else{

                    Boolean checkUser = mydb.userLogin(username, password);

                    if(checkUser){

                        ArrayList<ModalUser> data = mydb.fetchUser();

                        for(int x=0; x< data.size(); x++) {
                            Log.d("username", " " + data.get(x).username);

                                if(data.get(x).username.equals(username)) {
                                        username = data.get(x).username;
                                        Intent in = new Intent(LogIn.this, Home.class);
                                        in.putExtra(Extra_Username, username);
                                        startActivity(in);
                                        Toast.makeText(LogIn.this, "You have Successfully Loged-in!", Toast.LENGTH_SHORT).show();
                                    }
                        }
                    }else{
                        Toast.makeText(LogIn.this, "Account Do not Exist", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LogIn.this, Register.class);
                startActivity(in);
            }
        });


    }
}
