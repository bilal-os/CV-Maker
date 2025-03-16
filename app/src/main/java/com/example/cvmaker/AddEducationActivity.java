package com.example.cvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddEducationActivity extends AppCompatActivity {

    private EditText etInstitution, etDegree, etFieldOfStudy, etStartDate, etEndDate;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btnSave.setOnClickListener(v -> {
            if (validateFields()) {
                String institution = etInstitution.getText().toString().trim();
                String degree = etDegree.getText().toString().trim();
                String fieldOfStudy = etFieldOfStudy.getText().toString().trim();
                String startDate = etStartDate.getText().toString().trim();
                String endDate = etEndDate.getText().toString().trim();

                Intent intent = new Intent();
                intent.putExtra("Institution", institution);
                intent.putExtra("Degree", degree);
                intent.putExtra("FieldOfStudy", fieldOfStudy);
                intent.putExtra("StartDate", startDate);
                intent.putExtra("EndDate", endDate);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // Handle Cancel button click
        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

    }

    private void init()
    {
        etInstitution = findViewById(R.id.et_institution);
        etDegree = findViewById(R.id.et_degree);
        etFieldOfStudy = findViewById(R.id.et_field_of_study);
        etStartDate = findViewById(R.id.et_start_date);
        etEndDate = findViewById(R.id.et_end_date);
        btnSave = findViewById(R.id.btn_save_education);
        btnCancel = findViewById(R.id.btn_cancel_education);
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (TextUtils.isEmpty(etInstitution.getText().toString().trim())) {
            etInstitution.setError("Institution name is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etDegree.getText().toString().trim())) {
            etDegree.setError("Degree is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etFieldOfStudy.getText().toString().trim())) {
            etFieldOfStudy.setError("Field of study is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etStartDate.getText().toString().trim())) {
            etStartDate.setError("Start date is required");
            isValid = false;
        } else if (!etStartDate.getText().toString().trim().matches("\\d{4}-\\d{2}")) {
            etStartDate.setError("Start date must be in YYYY-MM format");
            isValid = false;
        }

        if (TextUtils.isEmpty(etEndDate.getText().toString().trim())) {
            etEndDate.setError("End date is required");
            isValid = false;
        } else if (!etEndDate.getText().toString().trim().matches("\\d{4}-\\d{2}")) {
            etEndDate.setError("End date must be in YYYY-MM format");
            isValid = false;
        }

        return isValid;
    }

}