package com.flipgrid.assignment.flipgridsignup.app.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flipgrid.assignment.flipgridsignup.R;
import com.flipgrid.assignment.flipgridsignup.app.global.DataKey;

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

        String greetingsMessage = getGreetingsMessage();

        greetings.setText(greetingsMessage);
        successMessage.setText(R.string.success_message);

        if (!getWebsite().isEmpty()) {
            String website = String.format("<a href=\"https://%s\">%s</a>", getWebsite(), getWebsite());
            displayWebsite.setText(Html.fromHtml(website));
            displayWebsite.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            displayWebsite.setVisibility(View.GONE);
        }

        if (!getFirstName().isEmpty()) {
            displayFirstName.setText(getFirstName());
        } else {
            displayFirstName.setVisibility(View.GONE);
        }

        displayEmail.setText(getEmail());
    }

    private String getGreetingsMessage() {
        return !getFirstName().isEmpty() ?
                String.format(getContext().getString(R.string.greetings_with_name), getFirstName()) :
                getContext().getString(R.string.greetings);
    }

    private String getFirstName() {
        return getArguments() != null ? getArguments().getString(DataKey.FIRST_NAME.name()) : "";
    }

    private String getEmail() {
        return getArguments() != null ? getArguments().getString(DataKey.EMAIL.name()) : "";
    }

    private String getWebsite() {
        return getArguments() != null ? getArguments().getString(DataKey.WEBSITE.name()) : "";
    }
}
