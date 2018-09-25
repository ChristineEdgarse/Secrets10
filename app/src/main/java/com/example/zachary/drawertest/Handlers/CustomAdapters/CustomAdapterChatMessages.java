package com.example.zachary.drawertest.Handlers.CustomAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataChatMessage;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

public class CustomAdapterChatMessages extends RecyclerView.Adapter<CustomAdapterChatMessages.Holder>{

    ArrayList<DataChatMessage> dataChatMessageList;
    Context context;
    View rowView;
    ViewGroup viewgroup;

    public CustomAdapterChatMessages(Context callingclass, ArrayList<DataChatMessage> datachatmessages) {
        dataChatMessageList      = datachatmessages;
        context                  = callingclass;
    }

    @Override
    public int getItemCount()  {
        return dataChatMessageList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder extends RecyclerView.ViewHolder
    {
        TextView msg, time;

        public Holder(View rowView) {
            super(rowView);
            msg = (TextView) rowView.findViewById(R.id.tvMessageText);
            time = (TextView) rowView.findViewById(R.id.tvMessageTimeStamp);
        }
    }


    @Override
    public int getItemViewType(int position) {
        String USRID = App.getUSRID();
        String UserID = dataChatMessageList.get(position).getuserUserID();
        if (USRID == UserID) { return 0; }
        else{ return 1;}
    }



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewgroup = parent;
        switch (viewType) {
            case 0:
                rowView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.item_chat_self, viewgroup, false);
                break;
            case 1:
                rowView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.item_chat_other, viewgroup, false);
                break;
        }
        return new Holder(rowView);
    }

    @Override
    public void onBindViewHolder(Holder tempholder, int itemnum) {
        final int position = itemnum;
        final Holder holder = tempholder;
        DataChatMessage dataChatMessage = dataChatMessageList.get(position);

        holder.msg.setText(dataChatMessage.getchatChatMessage());
        holder.time.setText(dataChatMessage.getchatTimeStamp());
    }


}
