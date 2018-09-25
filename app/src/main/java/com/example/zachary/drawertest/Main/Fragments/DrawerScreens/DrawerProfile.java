package com.example.zachary.drawertest.Main.Fragments.DrawerScreens;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataAd;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataUser;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterAdEdit;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterAdList;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DrawerProfile extends Fragment {

    ArrayList <DataAd> AdDataArrayList = new ArrayList<>();
    boolean editable;
    Context context;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.drawer_profile, container, false);
        context = getActivity();
        editable = false;
        LocalBroadcastManager.getInstance(context).registerReceiver(
                EditSwitchReciever, new IntentFilter("EditSwitch"));

        CircleImageView profileimage = (CircleImageView) view.findViewById(R.id.profilepicture);
        TextView tvUserRating = (TextView) view.findViewById(R.id.tvUserRating);
        TextView tvItemsSold = (TextView) view.findViewById(R.id.tvItemsSold);
        TextView tvStudying = (TextView) view.findViewById(R.id.tvStudying);
        TextView tvPSE = (TextView) view.findViewById(R.id.tvPSE);

        DataUser dataUser = ServerInterface.getUserProfile(App.getUSRID());

        tvUserRating.setText(dataUser.getuserRating() + "%");
        tvItemsSold.setText(dataUser.getuserItemsSold());
        tvPSE.setText(dataUser.getuserPSE());
        tvStudying.setText(dataUser.getuserStudying());
        profileimage.setImageBitmap(dataUser.getuserUserMain());

        AdDataArrayList = ServerInterface.getUserAdList(App.getUSRID());
        refreshAdapterMain();

        return view;
    }

    private BroadcastReceiver EditSwitchReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switchAdapter();
        }
    };

    @Override
    public void onResume () {
        super.onResume();
        refreshAdapterMain();
        editable = false;
    }

    public void switchAdapter() {
        editable = !editable;
        if (!editable) {refreshAdapterMain();}
        if (editable) {refreshAdapterEdit();}
    }

    public void refreshAdapterMain() {
        RecyclerView adpreviewlist = (RecyclerView) view.findViewById(R.id.profileAdPreviews);
        CustomAdapterAdList mAdapter = new CustomAdapterAdList(context, AdDataArrayList, true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }

    public void refreshAdapterEdit() {
        FragmentManager fm = getFragmentManager();
        RecyclerView adpreviewlist = (RecyclerView) view.findViewById(R.id.profileAdPreviews);
        CustomAdapterAdEdit mAdapter = new CustomAdapterAdEdit(context, AdDataArrayList, fm);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }

}

