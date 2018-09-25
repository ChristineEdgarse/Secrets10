package com.example.zachary.drawertest.Main.Start;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataUser;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.ImageHandler;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.Main.Drawer_Main;
import com.example.zachary.drawertest.Main.Fragments.PopUps.PopUpForgotPass;
import com.example.zachary.drawertest.R;

public class StartScreen extends Activity {

    String Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean firstload = App.getFirstLoad();
        Username = App.getSavedUsername();
        Password =  App.getSavedPassword();
        App.setFirstLoad();

        if (firstload) {
            setContentView(R.layout.startscreen_register);
        }
        else {
            setContentView(R.layout.startscreen_login);
            if ((Username != "" & Password != "") & (Username != null & Password != null)) {
                EditText etUsername = (EditText) findViewById(R.id.etUsername);
                EditText etPassword = (EditText) findViewById(R.id.etPassword);
                etUsername.setText(Username);
                etPassword.setText(Password);
            }
        }
    }

    public void login(View view) {
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        CheckBox remember = (CheckBox) findViewById(R.id.remember);
        if (remember.isChecked()) {
            Username = etUsername.getText().toString();
            Password = etPassword.getText().toString();
            App.setSavedPassword(Password);
            App.setSavedUsername(Username);
        } else {
            App.clearSavedPassword();
            App.clearSavedUsername();
        }

        boolean login = ServerInterface.loginLoginAttempt(Username, Password);
        if (login) {
//            DataUser dataUser = ServerInterface.loginLogin(Username, Password);
//            App.setLoginALL(dataUser);

            Intent intent = new Intent(this, Drawer_Main.class);
            startActivity(intent);
        }  else {
            Toast.makeText(StartScreen.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    public void signup(View view) {
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        String Email = etEmail.getText().toString();
        String Password = etPassword.getText().toString();
        if (master(Email, Password)) {
            Intent register = new Intent(this, Legal_Register.class);
            register.putExtra("Email", Email);
            register.putExtra("Password", Password);
            startActivity(register);
        }
    }

    public void forgot(View view) {
        Fragment newFragment = new PopUpForgotPass();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction FT = fragmentManager.beginTransaction();
        FT.add(R.id.fragmentholder, newFragment);
        FT.addToBackStack("");
        FT.commit();
    }

    public void loadlogin(View view) {
        setContentView(R.layout.startscreen_login);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername.setText(Username);
        etPassword.setText(Password);
    }

    public void loadregister(View view) {
        setContentView(R.layout.startscreen_register);
    }

    private boolean master(String email, String password) {
        if (!filled(email, password)) {
            return false; }
        if (!relevant(email)) {
            return false; }
        else
            return true;
    }

    // Ensures text fields is not empty
    private boolean filled(String email, String password) {
        if (email.equals("")) {
            Toast.makeText(this, getString(R.string.empty_email), Toast.LENGTH_SHORT).show();
            return false; }
        if (password.equals("")) {
            Toast.makeText(this, getString(R.string.empty_password), Toast.LENGTH_SHORT).show();
            return false; }
        else
            return true;
    }

    // Checks to make sure relevant email is entered
    private boolean relevant(String email){
        if(email.contains("@mun.ca")){
            return true; }
        if(email.contains("@mi.mun.ca")){
            return true; }
        if(email.contains("@ed.cna.nl.ca")){
            return true; }
        if(email.contains("@unisoftwareproductions.ca")){
            return true; }
        else {
            Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_LONG).show();
            return false; }
    }
}




