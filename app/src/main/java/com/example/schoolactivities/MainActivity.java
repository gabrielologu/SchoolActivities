package com.example.schoolactivities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolactivities.Student.DashboardStudent;
import com.example.schoolactivities.Teacher.DashboardTeacher;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin, btnRegisterPage;
    DatabaseHelper databaseHelper;
    String email2,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);
        btnLogin = findViewById(R.id.loginButton);
        btnRegisterPage = findViewById(R.id.toRegisterPageButton);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();


        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email2 = email.getText().toString();
                pass = password.getText().toString();
                if(email2.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please complete all the fields.", Toast.LENGTH_SHORT).show();
                }
                if(databaseHelper.checkEmail(email2) && databaseHelper.checkPassword(pass)){
                    Intent intent = new Intent(getApplicationContext(), DashboardTeacher.class);
                    startActivity(intent);
                }
                else if(databaseHelper.checkEStudent(email2) && databaseHelper.checkPStudent(pass)){
                    Intent intent = new Intent(getApplicationContext(), DashboardStudent.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Incorrect credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}