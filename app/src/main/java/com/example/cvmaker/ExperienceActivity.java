package com.example.cvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ExperienceActivity extends AppCompatActivity {

    private Button btnAddExperience, btnConfirmExperience;
    private ListView lvExperienceList;
    private ArrayList<Experience> experienceList;
    private ArrayAdapter<Experience> adapter;

    ActivityResultLauncher<Intent> getExperienceActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        experienceList = (ArrayList<Experience>) getIntent().getSerializableExtra("experiences");
        if (experienceList == null) {
            experienceList = new ArrayList<>();
        }

        // Set up the adapter for the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, experienceList);
        lvExperienceList.setAdapter(adapter);

        // Handle "Add New Experience" button click
        btnAddExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExperienceActivity.this, addExperienceActivity.class);
                getExperienceActivity.launch(intent);
            }
        });

        // Handle "Confirm" button click
        btnConfirmExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("experiences",experienceList);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
    private void init() {
        btnAddExperience = findViewById(R.id.btn_add_experience);
        btnConfirmExperience = findViewById(R.id.btn_confirm_experience);
        lvExperienceList = findViewById(R.id.lv_experience_list);
        experienceList = new ArrayList<>();

        // Handle the result from AddExperienceActivity
        getExperienceActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == RESULT_CANCELED || result.getData() == null) {
                        Toast.makeText(ExperienceActivity.this, "No Experience Added", Toast.LENGTH_SHORT).show();
                    } else if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Experience experience = new Experience(
                                data.getStringExtra("CompanyName"),
                                data.getStringExtra("Role"),
                                data.getStringExtra("StartDate"),
                                data.getStringExtra("EndDate"),
                                data.getStringExtra("Responsibilities")
                        );

                        experienceList.add(experience);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}