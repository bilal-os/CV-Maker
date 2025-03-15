package com.example.cvmaker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilePicture extends AppCompatActivity {

    ImageView ivProfile;
    ImageButton ibProfile;
    Button submit, cancel;

    ActivityResultLauncher<Intent> getImageLauncher;

    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_picture);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                getImageLauncher.launch(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null)
                {
                    Intent intent = new Intent();
                    intent.putExtra("ImageURI",String.valueOf(imageUri));
                    setResult(RESULT_OK,intent);
                    finish();
                }

                else {
                    Toast.makeText(ProfilePicture.this,"First Select an Image", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
    private void init()
    {
        ivProfile=findViewById(R.id.ivProfilePicture);
        ibProfile=findViewById(R.id.ibUploadImage);
        submit=findViewById(R.id.btnSubmitProfile);
        cancel=findViewById(R.id.btnCancelProfile);

        getImageLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) ->
                {
                    if(result.getResultCode()==RESULT_OK && result.getData()!=null)
                    {
                        imageUri = result.getData().getData();
                        ivProfile.setImageURI(imageUri);
                    }
                    else
                    {
                        Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
                    }
                }
                );


    }

}