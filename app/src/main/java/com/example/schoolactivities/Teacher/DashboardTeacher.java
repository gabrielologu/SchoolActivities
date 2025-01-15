package com.example.schoolactivities.Teacher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolactivities.R;

public class DashboardTeacher extends AppCompatActivity {

    Button cursBtn, laboratorBtn, webPlatf;
    TextView welcomeTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dashboard_teacher);

        cursBtn = findViewById(R.id.cursuriButton);
        laboratorBtn = findViewById(R.id.laboratoareButton);
        webPlatf = findViewById(R.id.visitWebsite);

        webPlatf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.upit.ro/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        welcomeTxtName = findViewById(R.id.welcomeName);


    }
}
