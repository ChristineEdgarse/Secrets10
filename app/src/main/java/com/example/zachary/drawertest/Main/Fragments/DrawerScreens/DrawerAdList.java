package com.example.zachary.drawertest.Main.Fragments.DrawerScreens;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataAd;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterAdList;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

public class DrawerAdList extends Fragment {

    ArrayList <DataAd> AdDataArrayList = new ArrayList<>();

    String Search;
    Context context, mcontext;
    View view;
    int MaxAds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.drawer_adlist, container, false);
        Bundle args = getArguments();
        Search = args.getString("Search" ,"ALL");
        context = getActivity();

        AdDataArrayList = ServerInterface.getAdPreviewList(Search);
        refreshAdapter();
        return view;
    }

    @Override
    public void onResume () {
        super.onResume();
        refreshAdapter();
    }

    public void refreshAdapter() {
        RecyclerView adpreviewlist = (RecyclerView) view.findViewById(R.id.lvAdPreviews);
        CustomAdapterAdList mAdapter = new CustomAdapterAdList(context, AdDataArrayList, true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }

}

