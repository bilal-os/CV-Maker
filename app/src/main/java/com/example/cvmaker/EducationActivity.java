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

public class EducationActivity extends AppCompatActivity {

    Button btnAddEducation, btnConfirm;
    ArrayAdapter<Education> adapter;
    private ArrayList<Education> educationList;
    private ListView lvEducationList;
    ActivityResultLauncher<Intent> getEducationActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        // Retrieve the passed ArrayList<Education>
        educationList = (ArrayList<Education>) getIntent().getSerializableExtra("educations");
        if (educationList == null) {
            educationList = new ArrayList<>();
        }

        // Set up the adapter for ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, educationList);
        lvEducationList.setAdapter(adapter);

        btnAddEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EducationActivity.this,AddEducationActivity.class);
                getEducationActivity.launch(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("educations", educationList);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }

    private void init()
    {
        btnAddEducation = findViewById(R.id.btn_add_education);
        lvEducationList = findViewById(android.R.id.list);
        btnConfirm=findViewById(R.id.btn_confirm_education);
        educationList = new ArrayList<>();

        getEducationActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->
                {
                    if(result.getResultCode() == RESULT_CANCELED || result.getData()==null)
                    {
                        Toast.makeText(EducationActivity.this,"No Education Added",Toast.LENGTH_LONG).show();
                    }

                    else if(result.getResultCode()==RESULT_OK) {

                        Intent getEducationIntent = result.getData();
                        Education education = new Education(
                                getEducationIntent.getStringExtra("Institution"),
                                getEducationIntent.getStringExtra("Degree"),
                                getEducationIntent.getStringExtra("FieldOfStudy"),
                                getEducationIntent.getStringExtra("StartDate"),
                                getEducationIntent.getStringExtra("EndDate")
                        );

                        educationList.add(education);
                        adapter.notifyDataSetChanged();
                    }
                });

    }


}