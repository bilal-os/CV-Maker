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

public class HomeActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> getProfilePictureLauncher;
    Uri uriProfilePicture;

    Button btnProfilePicture;

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

        btnProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(HomeActivity.this,ProfilePicture.class);
            getProfilePictureLauncher.launch(intent);
            }
        });

    }

    private void init()
    {

        btnProfilePicture=findViewById(R.id.btnProfilePicture);

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
                        uriProfilePicture = Uri.parse( profilePictureIntent.getStringExtra("ImageURI"));
                        Toast.makeText(HomeActivity.this,"Profile Image selected!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}