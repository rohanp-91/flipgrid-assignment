package com.flipgrid.assignment.flipgridsignup.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flipgrid.assignment.flipgridsignup.R;
import com.flipgrid.assignment.flipgridsignup.app.DataKey;

public class SigninFragment extends Fragment {

    private TextView greetings,
            successMessage,
            displayWebsite,
            displayFirstName,
            displayEmail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        initializeView(view);
        return view;
    }

    private void initializeView(View view) {
        greetings = view.findViewById(R.id.greetings);
        successMessage = view.findViewById(R.id.success_message);
        displayWebsite = view.findViewById(R.id.display_website);
        displayFirstName = view.findViewById(R.id.display_firstname);
        displayEmail = view.findViewById(R.id.display_email);

        String greetingsMessage = !getFirstName().isEmpty() ?
                String.format("Hello, %s!", getFirstName()) :
                String.format("Hello!");

        greetings.setText(greetingsMessage);
        successMessage.setText("Your super-awesome portfolio has been successfully submitted! The details below will be public within your community!");
        displayWebsite.setText(getWebsite());
        displayFirstName.setText(getFirstName());
        displayEmail.setText(getEmail());
    }

    private String getFirstName() {
        return getArguments().getString(DataKey.FIRST_NAME.name());
    }

    private String getEmail() {
        return getArguments().getString(DataKey.EMAIL.name());
    }

    private String getWebsite() {
        return getArguments().getString(DataKey.WEBSITE.name());
    }
}
