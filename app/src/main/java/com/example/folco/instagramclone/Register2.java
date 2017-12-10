package com.example.folco.instagramclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.folco.instagramclone.models.User;

import static com.example.folco.instagramclone.Login.registerEmail;
import static com.example.folco.instagramclone.Login.registerName;
import static com.example.folco.instagramclone.Login.registerPassword;
import static com.example.folco.instagramclone.Login.registerPhone;
import static com.example.folco.instagramclone.Login.registerUsername;
import static com.example.folco.instagramclone.models.User.listUser;

public class Register2 extends AppCompatActivity {

    public Boolean usernameBelumTerpakai() {
        String registerUsername = ((EditText) findViewById(R.id.register2Username)).getText().toString();


            for (User user : listUser) {
                if (user.getUsername().equals(registerUsername)) return false;
            }


        return true;
    }

    public Boolean validasi() {
        String registerUsername = ((EditText) findViewById(R.id.register2Username)).getText().toString().trim();

        if (TextUtils.isEmpty(registerUsername)) {
            ((EditText) findViewById(R.id.register2Username)).setError("Harap diisi..!!!");
            return false;
        }
        else {
            if (!usernameBelumTerpakai()) {
                ((EditText) findViewById(R.id.register2Username)).setError("Username sudah terpakai..!!!");
                return false;
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Button register2BtnRegister = (Button) findViewById(R.id.register2BtnRegister);

        register2BtnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login.registerUsername = ((EditText) findViewById(R.id.register2Username)).getText().toString();
                if (validasi()) {
                    try {
                        listUser.add(new User(registerName, registerUsername, registerPhone, registerEmail, registerPassword));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Register2.this);
                    alertDialogBuilder.setMessage("Account created..!!!");

                    alertDialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent intent = new Intent(Register2.this, Login.class);
                            startActivity(intent);
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });
    }
}
