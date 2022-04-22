package com.flipgrid.assignment.flipgridsignup.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.flipgrid.assignment.flipgridsignup.R;
import com.flipgrid.assignment.flipgridsignup.app.fragments.RegistrationFragment;
import com.flipgrid.assignment.flipgridsignup.app.fragments.SigninFragment;

public class RegistrationActivity extends AppCompatActivity implements RegistrationFragment.OnSubmitButtonClickListener {

    private static final String TAG_REGISTRATION_FRAGMENT = "registrationFragment";
    private static final String TAG_SIGNIN_FRAGMENT = "signinFragment";

    EditText firstName, email, password, website;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        SharedPreferences prefs = this.getSharedPreferences("0", Context.MODE_PRIVATE);
        if (!prefs.getBoolean("isRegistered", false)) {
            showRegistrationView();
        } else {
            Bundle savedData = new Bundle();
            savedData.putString("firstName", prefs.getString("firstName", ""));
            savedData.putString("email", prefs.getString("email", ""));
            savedData.putString("website", prefs.getString("website", ""));
            showSigninView(savedData, false);
        }
    }

    private void showRegistrationView() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        RegistrationFragment registrationFragment = (RegistrationFragment) fragmentManager.findFragmentByTag(TAG_REGISTRATION_FRAGMENT);
        if (registrationFragment != null && registrationFragment.isAdded()) {
            registrationFragment.setOnSubmitButtonClickListener(this);
            return;
        }

        if (registrationFragment == null) {
            registrationFragment = new RegistrationFragment();
        }
        registrationFragment.setOnSubmitButtonClickListener(this);
        fragmentManager.beginTransaction()
                .replace(R.id.activity_registration_content, registrationFragment, TAG_REGISTRATION_FRAGMENT)
                .commitNow();
    }

    @Override
    public void onSubmitButtonClicked(final String firstName, final String email, final String website) {
        Bundle registrationArguments = new Bundle();
        registrationArguments.putString("firstName", firstName);
        registrationArguments.putString("email", email);
        registrationArguments.putString("website", website);

        SharedPreferences prefs = this.getSharedPreferences("0", Context.MODE_PRIVATE);
        prefs.edit().putBoolean("isRegistered", true).apply();

        showSigninView(registrationArguments, true);
    }

    private void showSigninView(Bundle arguments, boolean fromRegistrationView) {
        SigninFragment signinFragment = new SigninFragment();
        signinFragment.setArguments(arguments);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(fromRegistrationView ?
                            fragmentManager.findFragmentByTag(TAG_REGISTRATION_FRAGMENT).getId() :
                            R.id.activity_registration_content, signinFragment, TAG_SIGNIN_FRAGMENT)
                    .commit();
        }

    }
}