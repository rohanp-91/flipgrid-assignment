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

import com.flipgrid.assignment.flipgridsignup.BuildConfig;
import com.flipgrid.assignment.flipgridsignup.R;
import com.flipgrid.assignment.flipgridsignup.app.AppContext;
import com.flipgrid.assignment.flipgridsignup.app.DataKey;
import com.flipgrid.assignment.flipgridsignup.app.PreferenceWrapper;
import com.flipgrid.assignment.flipgridsignup.app.fragments.RegistrationFragment;
import com.flipgrid.assignment.flipgridsignup.app.fragments.SigninFragment;

public class RegistrationActivity extends AppCompatActivity implements RegistrationFragment.OnSubmitButtonClickListener {

    private static final String TAG_REGISTRATION_FRAGMENT = "registrationFragment";
    private static final String TAG_SIGNIN_FRAGMENT = "signinFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        PreferenceWrapper preferenceWrapper = AppContext.getInstance(this).getPreferenceWrapper();
        if (BuildConfig.DEVELOPER_MODE || !preferenceWrapper.readBoolean(DataKey.IS_REGISTERED.name(), false)) {
            showRegistrationView();
        } else {
            Bundle savedData = new Bundle();
            savedData.putString(DataKey.FIRST_NAME.name(), preferenceWrapper.readString(DataKey.FIRST_NAME.name()));
            savedData.putString(DataKey.EMAIL.name(), preferenceWrapper.readString(DataKey.EMAIL.name()));
            savedData.putString(DataKey.WEBSITE.name(), preferenceWrapper.readString(DataKey.WEBSITE.name()));
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
        registrationArguments.putString(DataKey.FIRST_NAME.name(), firstName);
        registrationArguments.putString(DataKey.EMAIL.name(), email);
        registrationArguments.putString(DataKey.WEBSITE.name(), website);

        AppContext.getInstance(this).getPreferenceWrapper().writeBoolean(DataKey.IS_REGISTERED.name(), true);

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