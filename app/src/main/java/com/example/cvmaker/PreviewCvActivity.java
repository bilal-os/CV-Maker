package com.example.cvmaker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PreviewCvActivity extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview_cv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the CVInformation object from Intent
        CVInformation cvInformation = (CVInformation) getIntent().getSerializableExtra("cv_information");

        // Bind views from the layout
        ImageView profilePicture = findViewById(R.id.iv_profile_picture);
        TextView fullName = findViewById(R.id.tv_full_name);
        TextView contactDetails = findViewById(R.id.tv_contact_details);
        TextView summary = findViewById(R.id.tv_summary);
        TextView education = findViewById(R.id.tv_educations);
        TextView experience = findViewById(R.id.tv_experiences);
        TextView certifications = findViewById(R.id.tv_certifications);
       backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(view -> finish());

        // Populate the views
        if (cvInformation != null) {
            if (cvInformation.getUriProfilePicture() != null) {
                profilePicture.setImageURI(cvInformation.getUriProfilePicture());
            }

            fullName.setText(cvInformation.getFullName());
            contactDetails.setText(String.format("Email: %s\nPhone: %s\nAddress: %s",
                    cvInformation.getEmail(), cvInformation.getPhone(), cvInformation.getAddress()));
            summary.setText(cvInformation.getSummary());

            // Populate Education
            StringBuilder educationText = new StringBuilder();
            ArrayList<Education> educations = cvInformation.getEducations();
            for (Education edu : educations) {
                educationText.append(edu.toString()).append("\n");
            }
            education.setText(educationText.toString());

            // Populate Experience
            StringBuilder experienceText = new StringBuilder();
            ArrayList<Experience> experiences = cvInformation.getExperiences();
            for (Experience exp : experiences) {
                experienceText.append(exp.toString()).append("\n");
            }
            experience.setText(experienceText.toString());

            // Populate Certifications
            StringBuilder certificationsText = new StringBuilder();
            ArrayList<Certification> certificationsList = cvInformation.getCertifications();
            for (Certification cert : certificationsList) {
                certificationsText.append(cert.toString()).append("\n");
            }
            certifications.setText(certificationsText.toString());
        }
    }
}
