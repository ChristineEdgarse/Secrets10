package com.example.zachary.drawertest.Main;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataUser;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterUserList;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

/**
 * Created by Zachary on 21-Jul-16.
 */
public class Friends  extends AppCompatActivity {

    ArrayList<DataUser> UserDataArrayList = new ArrayList<>();
    Context context;
    String AdID, USRID;
    boolean rateuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        context = this;

        rateuser = getIntent().getBooleanExtra("RateUser", false);
        if (rateuser) {
            AdID = getIntent().getStringExtra("AdID");
            USRID = App.getUSRID();
            UserDataArrayList = ServerInterface.getUserRateList(AdID, USRID);
            getSupportActionBar().setTitle("Choose user to rate");
        }
        /** For future update */
        /*
        else {
            getSupportActionBar().setTitle("Followed Users");
            ArrayList<String> FavouriteUsers = App.getFavouriteUsers();
            UserDataArrayList = ServerInterface.getUserFavouriteList(FavouriteUsers);
        }
        */
        FragmentManager fragmentManager = getFragmentManager();
        GridView catlist = (GridView) findViewById(R.id.categorygrid);
        catlist.setAdapter(new CustomAdapterUserList(UserDataArrayList, context, rateuser, fragmentManager));
    }
}
