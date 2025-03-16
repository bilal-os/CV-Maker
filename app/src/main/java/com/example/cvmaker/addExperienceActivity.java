package com.example.cvmaker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class addExperienceActivity extends AppCompatActivity {

    private EditText etCompany, etRole, etResponsibilities, etStartDate, etEndDate;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_experience);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        etCompany = findViewById(R.id.et_company);
        etRole = findViewById(R.id.et_role);
        etResponsibilities = findViewById(R.id.et_responsibilities);
        etStartDate = findViewById(R.id.et_start_date_experience);
        etEndDate = findViewById(R.id.et_end_date_experience);
        btnSave = findViewById(R.id.btn_save_experience);
        btnCancel = findViewById(R.id.btn_cancel_experience);

        // Set up DatePickerDialogs for date fields
        setupDatePicker(etStartDate);
        setupDatePicker(etEndDate);

        // Handle Save button click
        btnSave.setOnClickListener(v -> saveExperience());

        // Handle Cancel button click
        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();});

    }
    private void setupDatePicker(EditText editText) {
        editText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                editText.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void saveExperience() {
        // Get input values
        String company = etCompany.getText().toString().trim();
        String role = etRole.getText().toString().trim();
        String responsibilities = etResponsibilities.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();

        // Validate inputs
        if (company.isEmpty() || role.isEmpty() || responsibilities.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send data back to the previous activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("CompanyName", company);
        resultIntent.putExtra("Role", role);
        resultIntent.putExtra("Responsibilities", responsibilities);
        resultIntent.putExtra("StartDate", startDate);
        resultIntent.putExtra("EndDate", endDate);
        setResult(RESULT_OK, resultIntent);

        // Close activity
        finish();
    }
}