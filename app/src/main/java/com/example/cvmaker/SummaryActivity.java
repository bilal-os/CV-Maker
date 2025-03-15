package com.example.cvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class SummaryActivity extends AppCompatActivity {

    TextInputEditText etSummary;

    Button submit,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        etSummary = findViewById(R.id.et_summary);
        submit = findViewById(R.id.btnSubmitSummary);
        cancel = findViewById(R.id.btnCancelSummary);


        submit.setOnClickListener(v -> {
            String summaryText = etSummary.getText().toString().trim();
            if (summaryText.isEmpty()) {
                Toast.makeText(SummaryActivity.this, "Summary cannot be empty!", Toast.LENGTH_SHORT).show();
            } else {

                Intent intent = new Intent();
                intent.putExtra("Summary",etSummary.getText());
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        cancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    }
