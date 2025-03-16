package com.example.cvmaker;

import android.content.Intent;
import android.os.Bundle;
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

public class CertificationsActivity extends AppCompatActivity {

    private ArrayList<Certification> certificationsArrayList;
    private ArrayAdapter<Certification> certificationsAdapter;

    private final ActivityResultLauncher<Intent> addCertificationLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String name = result.getData().getStringExtra("Name");
                    String organization = result.getData().getStringExtra("Organization");
                    String issueDate = result.getData().getStringExtra("IssueDate");
                    String expiryDate = result.getData().getStringExtra("ExpiryDate");

                    if (name != null && organization != null && issueDate != null) {
                        Certification certification = new Certification(
                                name,
                                organization,
                                issueDate,
                                expiryDate != null ? expiryDate : "N/A"
                        );

                        certificationsArrayList.add(certification);
                        certificationsAdapter.notifyDataSetChanged();
                        Toast.makeText(this, "Certification added successfully!", Toast.LENGTH_SHORT).show();
                    }
                } else if (result.getResultCode() == RESULT_CANCELED) {
                    Toast.makeText(this, "Certification addition canceled.", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_certifications);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the ListView and buttons
        ListView lvCertifications = findViewById(R.id.lv_certification_list);
        Button btnAddCertification = findViewById(R.id.btn_add_certification);
        Button btnConfirmCertification = findViewById(R.id.btn_confirm_certification);

        // Get CertificationsArrayList from the previous activity
        certificationsArrayList = (ArrayList<Certification>) getIntent().getSerializableExtra("certifications");
        if (certificationsArrayList == null) {
            certificationsArrayList = new ArrayList<>();
        }

        // Set up ArrayAdapter
        certificationsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, certificationsArrayList);
        lvCertifications.setAdapter(certificationsAdapter);

        // Add Certification Button logic
        btnAddCertification.setOnClickListener(v -> {
            Intent intent = new Intent(CertificationsActivity.this, addCertificationActivity.class);
            addCertificationLauncher.launch(intent);
        });

        // Confirm Button logic
        btnConfirmCertification.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("certifications", certificationsArrayList);
            setResult(RESULT_OK, resultIntent);
             finish(); // Close the activity
        });

    }
}
