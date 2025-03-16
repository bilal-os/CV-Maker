package com.example.cvmaker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> getProfilePictureLauncher;
    ActivityResultLauncher<Intent> getPersonalInformationLauncher;
    ActivityResultLauncher<Intent> getSummaryLauncher;

    ActivityResultLauncher<Intent> getEducationLauncher;

    ActivityResultLauncher<Intent> getExperienceLauncher;

    ActivityResultLauncher<Intent> getCertificationLauncher;
    CVInformation cvInformation;
    Button btnProfilePicture, btnPersonalInfo, btnSummary, btnEducation, btnExperience, btnCertification, btnPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setOnClickListeners();
    }

    private void setOnClickListeners()
    {
        btnProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ProfilePicture.class);
                getProfilePictureLauncher.launch(intent);
            }
        });

        btnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PersonalInformation.class);
                getPersonalInformationLauncher.launch(intent);
            }
        });

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SummaryActivity.class);
                getSummaryLauncher.launch(intent);
            }
        });

        btnEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EducationActivity.class);
                intent.putExtra("educations", cvInformation.getEducations());
                getEducationLauncher.launch(intent);
            }
        });

        btnExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ExperienceActivity.class);
                intent.putExtra("experiences",cvInformation.getExperiences());
                getExperienceLauncher.launch(intent);
            }
        });

        btnCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,CertificationsActivity.class);
                intent.putExtra("certifications",cvInformation.getCertifications());
                getCertificationLauncher.launch(intent);
            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PreviewCvActivity.class);
                intent.putExtra("cv_information",cvInformation);
                startActivity(intent);
            }
        });



    }

    private void init()
    {
        cvInformation = new CVInformation();
        btnProfilePicture=findViewById(R.id.btnProfilePicture);
        btnPersonalInfo=findViewById(R.id.btnPersonalDetails);
        btnSummary = findViewById(R.id.btnSummary);
        btnEducation = findViewById(R.id.btnEducation);
        btnExperience=findViewById(R.id.btnExperience);
        btnCertification=findViewById(R.id.btnCertifications);
        btnPreview=findViewById(R.id.btnPreview);

        getProfilePictureLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->
                {
                    if(result.getResultCode() == RESULT_CANCELED || result.getData()==null)
                    {
                        Toast.makeText(HomeActivity.this,"No Profile Image Selected",Toast.LENGTH_LONG).show();
                    }

                    else if(result.getResultCode()==RESULT_OK)
                    {
                        Intent profilePictureIntent = result.getData();
                        cvInformation.setUriProfilePicture(Uri.parse( profilePictureIntent.getStringExtra("ImageURI")));
                        Toast.makeText(HomeActivity.this,"Profile Image selected!",Toast.LENGTH_LONG).show();
                    }
                }
        );

        getPersonalInformationLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->
                {
                    if(result.getResultCode() == RESULT_CANCELED || result.getData()==null)
                    {
                        Toast.makeText(HomeActivity.this,"No Personal Data Inserted",Toast.LENGTH_LONG).show();
                    }

                    else if(result.getResultCode()==RESULT_OK)
                    {
                        Intent personalInformationIntent=result.getData();
                        cvInformation.setFullName(personalInformationIntent.getStringExtra("FullName"));
                        cvInformation.setDateOfBirth(personalInformationIntent.getStringExtra("DateOfBirth"));
                        cvInformation.setEmail(personalInformationIntent.getStringExtra("Email"));
                        cvInformation.setPhone(personalInformationIntent.getStringExtra("Phone"));
                        cvInformation.setAddress(personalInformationIntent.getStringExtra("Address"));
                        Toast.makeText(HomeActivity.this,"Personal Information Collected",Toast.LENGTH_LONG).show();
                    }
                });

        getSummaryLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->
                {
                    if(result.getResultCode() == RESULT_CANCELED || result.getData()==null)
                    {
                        Toast.makeText(HomeActivity.this,"No Summary Inserted",Toast.LENGTH_LONG).show();
                    }

                    else if(result.getResultCode()==RESULT_OK)
                    {
                        Intent summaryIntent=result.getData();
                        cvInformation.setSummary(summaryIntent.getStringExtra("Summary"));
                        Toast.makeText(HomeActivity.this,"Summary Collected",Toast.LENGTH_LONG).show();
                    }
                });

        getEducationLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->
                {
                    if(result.getResultCode() == RESULT_CANCELED || result.getData()==null)
                    {
                        Toast.makeText(HomeActivity.this,"No Education Inserted",Toast.LENGTH_LONG).show();
                    }

                    else if(result.getResultCode()==RESULT_OK) {
                        // Retrieve the updated ArrayList<Education> from the result
                        ArrayList<Education> updatedEducations =
                                (ArrayList<Education>) result.getData().getSerializableExtra("educations");
                        if (updatedEducations != null) {
                            cvInformation.setEducations(updatedEducations);
                            Toast.makeText(HomeActivity.this, "Education Collected", Toast.LENGTH_LONG).show();
                        }
                    }

                });

        getExperienceLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->
                {
                    if(result.getResultCode() == RESULT_CANCELED || result.getData()==null)
                    {
                        Toast.makeText(HomeActivity.this,"No Experience Inserted",Toast.LENGTH_LONG).show();
                    }

                    else if(result.getResultCode()==RESULT_OK) {

                        ArrayList<Experience> updatedExperiences = (ArrayList<Experience>) result.getData().getSerializableExtra("experiences");
                        if(updatedExperiences!=null)
                        {
                            cvInformation.setExperiences(updatedExperiences);
                            Toast.makeText(HomeActivity.this,"Experience Collected",Toast.LENGTH_LONG).show();
                    }
                    }
                });

        getCertificationLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) ->
                {
                    if(result.getResultCode() == RESULT_CANCELED || result.getData()==null)
                    {
                        Toast.makeText(HomeActivity.this,"No Experience Inserted",Toast.LENGTH_LONG).show();
                    }

                    else if(result.getResultCode()==RESULT_OK) {

                    ArrayList<Certification> certifications = (ArrayList<Certification>) result.getData().getSerializableExtra("certifications");

                    if(certifications!=null)
                    {
                        cvInformation.setCertifications(certifications);
                        Toast.makeText(HomeActivity.this,"Certifications Collected",Toast.LENGTH_LONG).show();
                    }

                    }
                });

    }
}