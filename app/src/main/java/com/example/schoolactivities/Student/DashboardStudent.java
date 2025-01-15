package com.example.schoolactivities.Student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolactivities.R;


public class DashboardStudent extends AppCompatActivity {

    Button courseBtn, labsBtn, websiteBtn;
    TextView welcomeTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dashboard_student);

        courseBtn = findViewById(R.id.coursesButton);
        labsBtn = findViewById(R.id.labsButton);
        websiteBtn = findViewById(R.id.visitWebsite);

        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.upit.ro/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        welcomeTxtName = findViewById(R.id.welcomeName);
        welcomeTxtName.setText("Welcome");

    }
}
