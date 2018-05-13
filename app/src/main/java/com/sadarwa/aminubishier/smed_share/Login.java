package com.sadarwa.aminubishier.smed_share;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Login extends AppCompatActivity {
    private EditText user_email, user_password,student_name;
    private Button login_button;
    private DatabaseOpenHelper myDb;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_email = (EditText)findViewById(R.id.name_txtb);
        user_password = (EditText)findViewById(R.id.password_txtb);
        login_button = (Button) findViewById(R.id.login_btn);
        student_name = (EditText)findViewById(R.id.student_name_txtb);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the object of the DatabaseOpenHelper
                myDb = new DatabaseOpenHelper(Login.this);

                //get the regCode typed by user
                String regCode = user_email.getText().toString();

                //extract and compare the pattern of the school email
                String code = regCode.substring(regCode.indexOf("@") + 1);

                //extract company's first name
                String company[] = student_name.getText().toString().split(" ");
                String CompanyFirstName = company[0];

                //checking whether the email matches the pattern
                if (code.equals("X0c-2ao-194-#?j")) {

                    //the insertData returns true if successful and false if otherwise
                    boolean isInserted = myDb.insertData(code, user_password.getText().toString(), CompanyFirstName);

                    //if it returns true i.e successful
                    if (isInserted){
                        Toast.makeText(Login.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                        finish();
                }
                //if it returns false i.e not successful
                    else
                        Toast.makeText(Login.this, "Registration not Successful", Toast.LENGTH_LONG).show();

                    }

                    //if email doesn't match the pattern
                 else {
                    Toast.makeText(Login.this, "Invalid Reg. code", Toast.LENGTH_LONG).show();

                }
            }
        });



    }
}
