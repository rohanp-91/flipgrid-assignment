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

        greetings.setText("Hello");
        successMessage.setText("Your super-awesome portfolio has been successfully submitted! The details below will be public within your community!");
        displayWebsite.setText("rohanpathak.microsoft.com");
        displayFirstName.setText("Rohan");
        displayEmail.setText("ropathak@microsoft.com");
    }
}
