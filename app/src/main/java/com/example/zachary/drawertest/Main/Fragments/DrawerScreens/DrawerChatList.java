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

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataChatPreview;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterChatList;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

public class DrawerChatList extends Fragment {

    ArrayList <DataChatPreview> dataChatPreviewList = new ArrayList<>();
    Context context;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drawer_chat, container, false);
        context = getActivity();
        String USRID = App.getUSRID();

        dataChatPreviewList = ServerInterface.getChatPreviewList(USRID);
        refreshAdapter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Todo - Handle removal of chat preview if deleted
        refreshAdapter();
    }

    public void refreshAdapter() {
        RecyclerView adpreviewlist = (RecyclerView) view.findViewById(R.id.lvChatList);
        CustomAdapterChatList mAdapter = new CustomAdapterChatList(context, dataChatPreviewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }
}

