package com.example.zachary.drawertest.Main.Fragments.PopUps;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.R;

public class PopUpForgotPass extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_forgotpass, container, false);
        view.findViewById(R.id.shadow).setOnClickListener(this);
        view.findViewById(R.id.confirm).setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.confirm) {
            TextView tv = (TextView) view.findViewById(R.id.etForgot);
            boolean success = ServerInterface.loginRetrieveEmail(tv.getText().toString());
            if (success) {Toast.makeText(getActivity(), "Email Sent!", Toast.LENGTH_SHORT).show();}
            if (!success) {Toast.makeText(getActivity(), "Error! Please try again later.", Toast.LENGTH_LONG).show();}
        }
        getActivity().onBackPressed();
    }
}

