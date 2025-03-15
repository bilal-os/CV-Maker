package com.example.cvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class PersonalInformation extends AppCompatActivity {

    TextInputEditText etFullName, etDateOfBirth, etEmail, etPhone, etAddress;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    Intent intent = new Intent();
                    intent.putExtra("FullName", etFullName.getText().toString());
                    intent.putExtra("DateOfBirth", etDateOfBirth.getText().toString());
                    intent.putExtra("Email", etEmail.getText().toString());
                    intent.putExtra("Phone", etPhone.getText().toString());
                    intent.putExtra("Address", etAddress.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }

    private void init()
    {
        etFullName = findViewById(R.id.etFullName);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private boolean validateFields() {
        boolean isValid = true;

        // Clear previous errors
        etFullName.setError(null);
        etDateOfBirth.setError(null);
        etEmail.setError(null);
        etPhone.setError(null);
        etAddress.setError(null);

        // Validate Full Name
        if (TextUtils.isEmpty(etFullName.getText())) {
            etFullName.setError("Full Name is required");
            isValid = false;
        }

        // Validate Date of Birth
        if (TextUtils.isEmpty(etDateOfBirth.getText())) {
            etDateOfBirth.setError("Date of Birth is required");
            isValid = false;
        }

        // Validate Email
        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches()) {
            etEmail.setError("Enter a valid email address");
            isValid = false;
        }

        // Validate Phone Number
        if (TextUtils.isEmpty(etPhone.getText())) {
            etPhone.setError("Phone Number is required");
            isValid = false;
        } else if (!etPhone.getText().toString().matches("\\d{10}")) {
            etPhone.setError("Enter a valid 10-digit phone number");
            isValid = false;
        }

        // Validate Address
        if (TextUtils.isEmpty(etAddress.getText())) {
            etAddress.setError("Address is required");
            isValid = false;
        }

        return isValid;
    }

}