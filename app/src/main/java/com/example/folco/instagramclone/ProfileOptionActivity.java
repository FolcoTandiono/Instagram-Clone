package com.example.folco.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_option);

        ImageView back = (ImageView) findViewById(R.id.profileOptionBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ((TextView) findViewById(R.id.profileOptionEditProfile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileOptionActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        ((TextView) findViewById(R.id.profileOptionChangePassword)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileOptionActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        ((TextView) findViewById(R.id.profileOptionLogOut)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileOptionActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
