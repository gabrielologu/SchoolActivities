package com.example.schoolactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton, loginButtonPage;
    EditText firstName, lastName, email, phoneNumber, password;
    DatabaseHelper DB;
    String email2, pass2, firstname2, lastname2, phonenr2;
    RadioGroup chooseUser;
    RadioButton Student, Teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_main);

        // Initialize views
        registerButton = findViewById(R.id.registerButton);
        loginButtonPage = findViewById(R.id.toLoginPageButton);
        firstName = findViewById(R.id.firstNameInput);
        lastName = findViewById(R.id.lastNameInput);
        email = findViewById(R.id.emailInput);
        phoneNumber = findViewById(R.id.phoneNumberInput);
        password = findViewById(R.id.passwordInput);
        chooseUser = findViewById(R.id.chooseUserType);
        Student = findViewById(R.id.imStudent);
        Teacher = findViewById(R.id.imTeacher);

        // Initialize the DatabaseHelper
        DB = new DatabaseHelper(this);

        // Set up click listeners
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email2 = email.getText().toString().trim();
                pass2 = password.getText().toString().trim();
                firstname2 = firstName.getText().toString().trim();
                lastname2 = lastName.getText().toString().trim();
                phonenr2 = phoneNumber.getText().toString().trim();


                if (email2.isEmpty() || pass2.isEmpty() || firstname2.isEmpty() || lastname2.isEmpty() || phonenr2.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please complete all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.checkEmail(email2);
                    Boolean checkStudent = DB.checkEStudent(email2);

                    if (checkuser || checkStudent) {
                        Toast.makeText(RegisterActivity.this, "Email already exists. Please log in.", Toast.LENGTH_SHORT).show();
                    }
                    else if (Teacher.isChecked()) {
                        Boolean insert = DB.insertData(email2, pass2, firstname2, lastname2, phonenr2);
                        if (insert) {
                            Toast.makeText(RegisterActivity.this, "Registered successfully. Please log in.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    if (Student.isChecked()) {
                            Boolean insertStudent = DB.insertDataStudent(email2, pass2, firstname2, lastname2, phonenr2);
                       if (insertStudent) {
                                Toast.makeText(RegisterActivity.this, "Registered successfully. Please log in.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                       }
                       else {
                                Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                       }
                    }
                }
            }
        });

        loginButtonPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}