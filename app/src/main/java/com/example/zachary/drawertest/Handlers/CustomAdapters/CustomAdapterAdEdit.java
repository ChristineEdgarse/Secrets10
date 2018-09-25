package com.example.zachary.drawertest.Handlers.CustomAdapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataAd;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.Main.Fragments.PopUps.PopUpItemSold;
import com.example.zachary.drawertest.Handlers.ImageHandler;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

public class CustomAdapterAdEdit extends RecyclerView.Adapter<CustomAdapterAdEdit.Holder>{

    ArrayList<DataAd> dataAdList;
    FragmentManager fragmentManager;
    Context context;
    String USRID;

    public CustomAdapterAdEdit(Context callingclass, ArrayList<DataAd> dataadlist, FragmentManager mfragmentManager) {
        dataAdList       = dataadlist;
        context          = callingclass;
        fragmentManager  = mfragmentManager;
        USRID = App.getUSRID();
    }

    @Override
    public int getItemCount()  {
        return dataAdList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder extends RecyclerView.ViewHolder
    {
        TextView name, price;
        ImageView img, icon;
        Button sold;

        public Holder(View rowView) {
            super(rowView);
            img = (ImageView) rowView.findViewById(R.id.ivAdImage);
            icon = (ImageView) rowView.findViewById(R.id.AdIconBottom);
            name = (TextView) rowView.findViewById(R.id.tvItemName);
            price = (TextView) rowView.findViewById(R.id.tvPrice);
            sold = (Button) rowView.findViewById(R.id.buttonSold);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adedit, parent, false);
        return new Holder(rowView);
    }

    @Override
    public void onBindViewHolder(Holder tempholder, int itemnum) {
        final String AdID, UserID, AdName, Username;
        final Holder holder = tempholder;
        final int position = itemnum;

        final DataAd dataAd = dataAdList.get(position);
        AdID = dataAd.getadAdID();
        UserID = dataAd.getuserUserID();
        AdName = dataAd.getadName();
        Username = dataAd.getuserUsername();

        /** Get reference to views
         finds and sets information based on list position  */

        holder.img.setImageBitmap(dataAd.getadImageThumb());
        holder.name.setText(AdName);
        holder.price.setText("$" + dataAd.getadPrice());

        holder.icon.setOnClickListener(new View.OnClickListener() {
            boolean delete = false;
            @Override
            public void onClick(View v) {
                if (delete) {
                    ServerInterface.sendAdDelete(USRID, AdID);
                } else {
                    delete = true;
                    Toast.makeText(context, "Tap again to delete ad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("ItemName", AdName);
                args.putString("ItemPrice", dataAd.getadPrice());
                args.putString("ItemImage", ImageHandler.DeassembleBitmaptoString(dataAd.getadImageThumb()));
                args.putString("AdID", AdID);
                args.putString("UserID", UserID);


                Fragment newFragment = new PopUpItemSold();
                newFragment.setArguments(args);
                FragmentTransaction FT = fragmentManager.beginTransaction();
                FT.add(R.id.fragmentholder, newFragment);
                FT.addToBackStack("");
                FT.commit();

                /*
                try {
                    String AdIDString = AdID.get(position);
                    String UserIDString = UserID.get(position);
                    Log.e("onClick: ", AdIDString);
                    char X = AdIDString.charAt(35);
                    char x1 = AdIDString.charAt(37);
                    char x2 = AdIDString.charAt(38);
                    Log.e("onClick: ", String.valueOf(X + "-" + x1 + x2));

                    // Deletes all instances of the ad from server//////////////
                    Firebase FULLAD = server.child("AD/FULL");
                    Firebase ALL = server.child("AD/PREVIEW/ALL");
                    Firebase CATEGORIES = server.child("AD/PREVIEW/CATEGORIES/" + X);
                    Firebase ITEMTYPE = server.child("AD/PREVIEW/ITEMTYPE/" + X + "/" + x1 + x2);
                    Firebase USERADS = server.child("USR/ID/" + UserIDString + "/USERADS");
                    Firebase AdIDs = server.child("USR/ID/" + UserIDString + "/ADIDS");

                    Map<String,Object> DeleteAd = new HashMap<String,Object>();
                        DeleteAd.put(AdIDString, null);

                    FULLAD.updateChildren(DeleteAd);
                    ALL.updateChildren(DeleteAd);
                    CATEGORIES.updateChildren(DeleteAd);
                    ITEMTYPE.updateChildren(DeleteAd);
                    USERADS.updateChildren(DeleteAd);
                    AdIDs.updateChildren(DeleteAd);
                    ////////////////////////////////////////////////////////////

                    Toast.makeText(parent.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(parent.getContext(), "Item Not Found! Reload page and try again!", Toast.LENGTH_SHORT).show();
                }
                */
            }
        });
    }


}
