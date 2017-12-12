package com.example.folco.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.folco.instagramclone.models.User;

import static com.example.folco.instagramclone.models.User.listUser;

public class Login extends AppCompatActivity {

    public static String loginUsernameEmail;
    public static String loginPassword;
    public static String registerPhone;
    public static String registerEmail;
    public static String registerName;
    public static String registerPassword;
    public static String registerUsername;
    public static User userNow = new User();

    public ProgressDialog progressDialog;
    public int progress;

    public Boolean validate() {
        if (TextUtils.isEmpty(loginUsernameEmail)) {
            ((EditText) findViewById(R.id.loginUsernameEmail)).setError("Harap diisi..!!!");
            return false;
        }
        if (TextUtils.isEmpty(loginPassword)) {
            ((EditText) findViewById(R.id.loginPassword)).setError("Harap diisi..!!!");
            return false;
        }
        return true;
    }

        public Boolean userMatched() {
            for (User user : listUser) {
                if (user.getUsername().equals(loginUsernameEmail) || user.getEmail().equals(loginUsernameEmail)) {
                    if (user.getPassword().equals(loginPassword)) {
                        userNow = user;
                        return true;
                    }
                    else {
                        ((EditText) findViewById(R.id.loginPassword)).setError("Wrong password..!!!");
                        return false;
                    }
                }
            }
            ((EditText) findViewById(R.id.loginUsernameEmail)).setError("No user found..!!!");
            return false;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginBtnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Please wait....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(false);
                progressDialog.show();

                progress = 0;

                final Handler handler = new Handler();

                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progress < 2){
                            // Update the progress status
                            progress +=1;

                            // Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(1000);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    // Update the progress status
                                    progressDialog.setProgress(progress);
                                    // If task execution completed
                                    if(progress == 2){
                                        // Dismiss/hide the progress dialog
                                        progress++;
                                        progressDialog.dismiss();

                                        Login.loginUsernameEmail = ((EditText) findViewById(R.id.loginUsernameEmail)).getText().toString();
                                        Login.loginPassword = ((EditText) findViewById(R.id.loginPassword)).getText().toString();

                                        if (validate()) {
                                            if (userMatched()) {
                                                Intent intent = new Intent(Login.this, HomeActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }).start(); // Start the operation
            }
        });

        Toolbar loginToolbar = (Toolbar) findViewById(R.id.loginToolbar);

        loginToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }
}
