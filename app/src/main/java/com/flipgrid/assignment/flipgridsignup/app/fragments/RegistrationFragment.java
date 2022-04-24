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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flipgrid.assignment.flipgridsignup.R;
import com.flipgrid.assignment.flipgridsignup.app.global.AppContext;
import com.flipgrid.assignment.flipgridsignup.app.global.DataKey;
import com.flipgrid.assignment.flipgridsignup.app.global.PreferenceWrapper;
import com.flipgrid.assignment.flipgridsignup.app.utils.ErrorTypes;
import com.flipgrid.assignment.flipgridsignup.app.utils.Validators;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class RegistrationFragment extends Fragment {

    private final int MAX_ERRORS = 5;
    private OnSubmitButtonClickListener onSubmitButtonClickListener;
    private Validators validators;
    private TextInputLayout firstNameLayout, emailLayout, passwordLayout, websiteLayout;
    private EditText firstName, email, password, website;
    private Button submitButton;
    private ProgressBar progressBar;

    private List<ErrorTypes> emailErrors, passwordErrors;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initializeView(view);
        validators = new Validators();

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
        initFirstNameLayout(view);
        initEmailLayout(view);
        initPasswordLayout(view);
        initWebsiteLayout(view);
        initializeSubmitButton(view);
        initProgressBar(view);
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

    private void validateEmail() {
        emailErrors = validators.isEmailValid(getEmail());
        if (emailErrors.isEmpty()) {
            clearError(emailLayout);
        } else {
            setError(emailErrors, emailLayout);
        }
    }

    private void validatePassword() {
        passwordErrors = validators.isPasswordValid(getPassword());
        if (passwordErrors.isEmpty()) {
            clearError(passwordLayout);
        } else {
            setError(passwordErrors, passwordLayout);
        }
    }

    private void setError(List<ErrorTypes> errors, TextInputLayout textInputLayout) {
        textInputLayout.setErrorEnabled(true);
        TextView errorView = textInputLayout.findViewById(R.id.textinput_error);
        errorView.setMaxLines(MAX_ERRORS);

        StringBuilder sb = new StringBuilder();
        String separator = "";
        for (ErrorTypes error : errors) {
            sb.append(separator);
            separator = "\n";
            sb.append(getContext().getString(error.errorMessageResourceId));
        }
        textInputLayout.setError(sb.toString());
    }

    private void clearError(TextInputLayout textInputLayout) {
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }

    private void updateSubmitButtonState() {
        if (getEmail().length() <= 0 || (emailErrors != null && !emailErrors.isEmpty())) {
            submitButton.setEnabled(false);
            return;
        }
        if (getPassword().length() <=0 || (passwordErrors!= null && !passwordErrors.isEmpty())) {
            submitButton.setEnabled(false);
            return;
        }
        submitButton.setEnabled(true);
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

    private String getPassword() {
        return password.getText().toString().trim();
    }

    private void initFirstNameLayout(View view) {
        firstNameLayout = view.findViewById(R.id.first_name_input_layout);
        firstName = firstNameLayout.getEditText();
    }

    private void initEmailLayout(View view) {
        emailLayout = view.findViewById(R.id.email_input_layout);
        email = emailLayout.getEditText();
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                validateEmail();
                updateSubmitButtonState();
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateEmail();
                updateSubmitButtonState();
            }
        });
    }

    private void initPasswordLayout(View view) {
        passwordLayout = view.findViewById(R.id.password_input_layout);
        password = passwordLayout.getEditText();
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                validatePassword();
                updateSubmitButtonState();
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validatePassword();
                updateSubmitButtonState();
            }
        });
    }

    private void initWebsiteLayout(View view) {
        websiteLayout = view.findViewById(R.id.website_input_layout);
        website = websiteLayout.getEditText();
    }

    private void initProgressBar(View view) {
        progressBar = view.findViewById(R.id.progress_loader);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public interface OnSubmitButtonClickListener {
        void onSubmitButtonClicked(String firstName, String email, String website);
    }
}
