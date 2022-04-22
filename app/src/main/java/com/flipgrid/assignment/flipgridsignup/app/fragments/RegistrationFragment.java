package com.flipgrid.assignment.flipgridsignup.app.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flipgrid.assignment.flipgridsignup.R;

public class RegistrationFragment extends Fragment {

    private OnSubmitButtonClickListener onSubmitButtonClickListener;
    EditText firstName, email, password, website;
    Button submitButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initializeView(view);

        if (this.getActivity().getSharedPreferences("0", Context.MODE_PRIVATE).getBoolean("hasSavedData", false)) {
            restoreSavedData();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences prefs = this.getActivity().getSharedPreferences("0", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("hasSavedData", true).apply();
        editor.putString("firstName", getFirstName()).apply();
        editor.putString("email", getEmail()).apply();
        editor.putString("website", getWebsite()).apply();
    }

    public void setOnSubmitButtonClickListener(OnSubmitButtonClickListener listener) {
        this.onSubmitButtonClickListener = listener;
    }

    private void initializeView(View view) {
        firstName = view.findViewById(R.id.first_name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        website = view.findViewById(R.id.website);

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
        SharedPreferences prefs = this.getActivity().getSharedPreferences("0", Context.MODE_PRIVATE);
        firstName.setText(prefs.getString("firstName", null));
        email.setText(prefs.getString("email", null));
        website.setText(prefs.getString("website", null));
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
