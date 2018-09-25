package com.example.zachary.drawertest.Main.Fragments.DrawerScreens;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataAd;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterAdFavourites;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

public class DrawerFavourites extends Fragment implements CustomAdapterAdFavourites.UnfavouritedPass{

    ArrayList <DataAd> AdDataArrayList = new ArrayList<>();
    View view;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drawer_adlist, container, false);
        context = getActivity();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.hide();

        createLists();
        return view;
    }

    @Override
    public void onResume () {
        super.onResume();
        createLists();
        refreshAdapter();
    }

    public void createLists() {
        ArrayList<String> Favourites = App.getFavouriteAds();
        AdDataArrayList.clear();
        AdDataArrayList = ServerInterface.getAdFavouriteList(Favourites);
        refreshAdapter();
    }

    @Override
    public void onDataPass(String adid) {
        updateAdapter(adid);
    }

    public void updateAdapter(String adid) {
        ArrayList<String> AdIDList = new ArrayList();
        for (int x = 0; x < AdDataArrayList.size(); x++) {
            AdIDList.add(AdDataArrayList.get(x).getadAdID());
        }
        int i = AdIDList.indexOf(adid);
        AdDataArrayList.remove(i);
        refreshAdapter();
    }

    public void refreshAdapter() {
        RecyclerView adpreviewlist = (RecyclerView) view.findViewById(R.id.lvAdPreviews);
        adpreviewlist.setPadding(0,0,0,0);
        CustomAdapterAdFavourites mAdapter = new CustomAdapterAdFavourites(context, AdDataArrayList, true, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }

}

