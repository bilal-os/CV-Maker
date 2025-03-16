package com.example.cvmaker;

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

public class addCertificationActivity extends AppCompatActivity {

    private EditText etCertificationName;
    private EditText etIssuingOrganization;
    private EditText etIssueDate;
    private EditText etExpiryDate;
    private Button btnSave;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_certification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etCertificationName = findViewById(R.id.et_certification_name);
        etIssuingOrganization = findViewById(R.id.et_issuing_organization);
        etIssueDate = findViewById(R.id.et_issue_date);
        etExpiryDate = findViewById(R.id.et_expiry_date);
        btnSave = findViewById(R.id.btn_save_certification);
        btnCancel = findViewById(R.id.btn_cancel_certification);

        btnSave.setOnClickListener(v -> saveCertification());

        btnCancel.setOnClickListener(v -> cancelCertification());

    }

    private void saveCertification() {

        String name = etCertificationName.getText().toString().trim();
        String organization = etIssuingOrganization.getText().toString().trim();
        String issueDate = etIssueDate.getText().toString().trim();
        String expiryDate = etExpiryDate.getText().toString().trim();

        if (name.isEmpty() || organization.isEmpty() || issueDate.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("Name", name);
        intent.putExtra("Organization", organization);
        intent.putExtra("IssueDate", issueDate);
        intent.putExtra("ExpiryDate", expiryDate);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void cancelCertification() {
       setResult(RESULT_CANCELED);
        finish(); // Close activity
    }

}