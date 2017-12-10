package com.example.folco.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register1 extends AppCompatActivity {

    public Boolean validasi() {
        String registerName = ((EditText) findViewById(R.id.register1Name)).getText().toString().trim();
        String registerPassword = ((EditText) findViewById(R.id.register1Password)).getText().toString().trim();

        if (TextUtils.isEmpty(registerName)) {
            ((EditText) findViewById(R.id.register1Name)).setError("Harap diisi..!!!");
            return false;
        }
        else if (TextUtils.isEmpty(registerPassword)) {
            ((EditText) findViewById(R.id.register1Password)).setError("Harap diisi..!!!");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        Button register1BtnRegister = (Button) findViewById(R.id.register1BtnRegister);

        register1BtnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getSharedPreferences("Register", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("registerName", ((EditText) findViewById(R.id.register1Name)).getText().toString());
//                editor.putString("registerPassword", ((EditText) findViewById(R.id.register1Password)).getText().toString());
//                editor.apply();
                if (validasi()) {
                    Login.registerName = ((EditText) findViewById(R.id.register1Name)).getText().toString();
                    Login.registerPassword = ((EditText) findViewById(R.id.register1Password)).getText().toString();

                    Intent intent = new Intent(Register1.this, Register2.class);
                    startActivity(intent);
                }
            }
        });
    }
}
