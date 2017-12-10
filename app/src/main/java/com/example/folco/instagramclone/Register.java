package com.example.folco.instagramclone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.folco.instagramclone.models.User;

import static com.example.folco.instagramclone.models.User.listUser;

public class Register extends AppCompatActivity implements EmailFragment.OnFragmentInteractionListener, PhoneFragment.OnFragmentInteractionListener {

    public EditText registerPhone;
    public EditText registerEmail;

    public EditText getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(EditText registerPhone) {
        this.registerPhone = registerPhone;
    }

    public EditText getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(EditText registerEmail) {
        this.registerEmail = registerEmail;
    }

    public Boolean emailBelumTerpakai() {
        String email = getRegisterEmail().getText().toString();


            for (User user : listUser) {
                if (user.getEmail().equals(email)) {
                    return false;
                }
            }

        return true;
    }

    public Boolean validasi() {
        if (TextUtils.isEmpty(getRegisterPhone().getText().toString().trim())) {
            getRegisterPhone().setError("Harap diisi..!!!");

            return false;
        }
        else if (TextUtils.isEmpty(getRegisterEmail().getText().toString().trim())) {
            getRegisterEmail().setError("Harap diisi..!!!");

            return false;
        }
        else {
            if (!emailBelumTerpakai()) {
                getRegisterEmail().setError("Email sudah terpakai..!!!");

                return false;
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.registerTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("PHONE"));
        tabLayout.addTab(tabLayout.newTab().setText("EMAIL"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.registerViewPager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Toolbar registerToolbar = (Toolbar) findViewById(R.id.registerToolbar);

        registerToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        Button registerBtnRegister = (Button) findViewById(R.id.registerBtnRegister);

        registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getSharedPreferences("Register", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("registerEmail", getRegisterEmail().getText().toString());
//                editor.putString("registerPhone", getRegisterPhone().getText().toString());
//                editor.apply();
                if (validasi()) {
                    Login.registerEmail = getRegisterEmail().getText().toString().trim();
                    Login.registerPhone = getRegisterPhone().getText().toString().trim();

                    Intent i = new Intent(Register.this, Register1.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
