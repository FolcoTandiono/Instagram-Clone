package com.example.folco.instagramclone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.folco.instagramclone.Login.userNow;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ((ImageView) findViewById(R.id.changePassowrdBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ((ImageView) findViewById(R.id.changePasswordTrue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = ((TextView) findViewById(R.id.changePasswordOldPassword)).getText().toString();
                String newPassword = ((TextView) findViewById(R.id.changePasswordNewPassword)).getText().toString();
                String confirmNewPassword = ((TextView) findViewById(R.id.changePasswordConfirmNewPassword)).getText().toString();

                if (oldPassword.equals(userNow.getPassword())) {
                    if (newPassword.equals(confirmNewPassword)) {
                        userNow.setPassword(newPassword);

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ChangePasswordActivity.this);
                        builder1.setMessage("Password changed..!!!");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                    else {
                        ((TextView) findViewById(R.id.changePasswordConfirmNewPassword)).setError("New password not mached..!!!");
                    }
                }
                else {
                    ((TextView) findViewById(R.id.changePasswordOldPassword)).setError("Wrong old password..!!!");
                }
            }
        });
    }
}
