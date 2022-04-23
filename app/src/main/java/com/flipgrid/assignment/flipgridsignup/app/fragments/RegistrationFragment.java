package com.flipgrid.assignment.flipgridsignup.app.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flipgrid.assignment.flipgridsignup.R;
import com.flipgrid.assignment.flipgridsignup.app.global.AppContext;
import com.flipgrid.assignment.flipgridsignup.app.global.DataKey;
import com.flipgrid.assignment.flipgridsignup.app.global.PreferenceWrapper;

public class RegistrationFragment extends Fragment {

    private OnSubmitButtonClickListener onSubmitButtonClickListener;
    EditText firstName, email, password, website;
    Button submitButton;
    ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initializeView(view);

        if (AppContext.getInstance(getActivity()).getPreferenceWrapper().readBoolean(DataKey.HAS_SAVED_DATA.name(), false)) {
            restoreSavedData();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        PreferenceWrapper preferenceWrapper = AppContext.getInstance(getActivity()).getPreferenceWrapper();
        preferenceWrapper.writeBoolean(DataKey.HAS_SAVED_DATA.name(), true);
        preferenceWrapper.writeString(DataKey.FIRST_NAME.name(), getFirstName());
        preferenceWrapper.writeString(DataKey.EMAIL.name(), getEmail());
        preferenceWrapper.writeString(DataKey.WEBSITE.name(), getWebsite());
    }

    public void setOnSubmitButtonClickListener(OnSubmitButtonClickListener listener) {
        this.onSubmitButtonClickListener = listener;
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void initializeView(View view) {
        firstName = view.findViewById(R.id.first_name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        website = view.findViewById(R.id.website);
        progressBar = view.findViewById(R.id.progress_loader);

        progressBar.setMax(100);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.INVISIBLE);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        initializeSubmitButton(view);
    }

    private void initializeSubmitButton(View view) {
        submitButton = view.findViewById(R.id.submit);
        submitButton.setEnabled(false);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitButtonClickListener.onSubmitButtonClicked(getFirstName(), getEmail(), getWebsite());
            }
        });
    }

    private void restoreSavedData() {
        PreferenceWrapper preferenceWrapper = AppContext.getInstance(getActivity()).getPreferenceWrapper();
        firstName.setText(preferenceWrapper.readString(DataKey.FIRST_NAME.name()));
        email.setText(preferenceWrapper.readString(DataKey.EMAIL.name()));
        website.setText(preferenceWrapper.readString(DataKey.WEBSITE.name()));
    }

    private void checkRequiredFields() {
        if (email.getText().length() > 0 && password.getText().length() > 0) {
            submitButton.setEnabled(true);
        } else {
            submitButton.setEnabled(false);
        }
    }

    private String getFirstName() {
        return firstName.getText().toString().trim();
    }

    private String getEmail() {
        return email.getText().toString().trim();
    }

    private String getWebsite() {
        return website.getText().toString().trim();
    }

    public interface OnSubmitButtonClickListener {
        void onSubmitButtonClicked(String firstName, String email, String website);
    }
}
