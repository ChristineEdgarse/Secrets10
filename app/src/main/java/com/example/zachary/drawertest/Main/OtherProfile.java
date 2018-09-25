package com.example.zachary.drawertest.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataAd;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataUser;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterAdList;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OtherProfile extends AppCompatActivity {

    ArrayList <DataAd> AdDataArrayList = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                String UserID = getIntent().getStringExtra("UserID");
                int userid = Integer.parseInt(UserID);
                if (userid == 9) {redirect(); return;}
        setContentView(R.layout.drawer_profile);
        context = this;
        /** Future Update */
        // Todo - Add header bar with favourite/star for users & handle favourites

        CircleImageView profileimage = (CircleImageView) findViewById(R.id.profilepicture);
        TextView tvUserRating = (TextView) findViewById(R.id.tvUserRating);
        TextView tvItemsSold = (TextView) findViewById(R.id.tvItemsSold);
        TextView tvStudying = (TextView) findViewById(R.id.tvStudying);
        TextView tvPSE = (TextView) findViewById(R.id.tvPSE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();

        DataUser dataUser = ServerInterface.getUserProfile(UserID);

        getSupportActionBar().setTitle(dataUser.getuserUsername());
        tvUserRating.setText(dataUser.getuserRating() + "%");
        tvItemsSold.setText(dataUser.getuserItemsSold());
        tvPSE.setText(dataUser.getuserPSE());
        tvStudying.setText(dataUser.getuserStudying());
        profileimage.setImageBitmap(dataUser.getuserUserMain());

        AdDataArrayList = ServerInterface.getUserAdList(UserID);
        refreshAdapter();
    }

    @Override
    public void onResume () {
        super.onResume();
        refreshAdapter();
    }

    public void refreshAdapter() {
        RecyclerView adpreviewlist = (RecyclerView) findViewById(R.id.profileAdPreviews);
        adpreviewlist.setPadding(0,0,0,0);
        CustomAdapterAdList mAdapter = new CustomAdapterAdList(context, AdDataArrayList, true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }

    public void redirect() {
        Intent intent = new Intent(this, Drawer_Main.class);
        intent.putExtra("Redirected", true);
        startActivity(intent);
        finish();
    }

}

