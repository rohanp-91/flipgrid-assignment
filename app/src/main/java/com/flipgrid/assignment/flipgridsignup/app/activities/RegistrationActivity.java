package com.flipgrid.assignment.flipgridsignup.app.activities;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.flipgrid.assignment.flipgridsignup.BuildConfig;
import com.flipgrid.assignment.flipgridsignup.R;
import com.flipgrid.assignment.flipgridsignup.app.global.AppContext;
import com.flipgrid.assignment.flipgridsignup.app.global.DataKey;
import com.flipgrid.assignment.flipgridsignup.app.network.IResponseCallback;
import com.flipgrid.assignment.flipgridsignup.app.global.PreferenceWrapper;
import com.flipgrid.assignment.flipgridsignup.app.network.RegistrationRepository;
import com.flipgrid.assignment.flipgridsignup.app.fragments.RegistrationFragment;
import com.flipgrid.assignment.flipgridsignup.app.fragments.SigninFragment;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity implements RegistrationFragment.OnSubmitButtonClickListener {

    private static final String TAG_REGISTRATION_FRAGMENT = "registrationFragment";
    private static final String TAG_SIGNIN_FRAGMENT = "signinFragment";

    private RegistrationRepository registrationRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationRepository = new RegistrationRepository();

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

        RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag(TAG_REGISTRATION_FRAGMENT);
        registrationFragment.showProgressBar();

        AppContext.getInstance(this).getPreferenceWrapper().writeBoolean(DataKey.IS_REGISTERED.name(), true);

        registrationRepository.sendLoginRequest(new Object(), new IResponseCallback() {
            @Override
            public void onSuccess(Object request, Object response) {
                registrationFragment.hideProgressBar();
                showSigninView(registrationArguments, true);
            }

            @Override
            public void onFailure(Object request, Object response) {
                AppContext.getInstance(getApplicationContext()).getLogger().LogError(new HashMap<String, String>() {{
                    put("Error", response.toString());
                }});
            }
        });
    }

    private void showSigninView(Bundle arguments, boolean fromRegistrationView) {
        SigninFragment signinFragment = new SigninFragment();
        signinFragment.setArguments(arguments);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.slide_out
                    ).replace(fromRegistrationView ?
                            fragmentManager.findFragmentByTag(TAG_REGISTRATION_FRAGMENT).getId() :
                            R.id.activity_registration_content, signinFragment, TAG_SIGNIN_FRAGMENT)
                    .commit();
        }

    }
}