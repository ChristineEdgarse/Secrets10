package com.example.zachary.drawertest.Main.Fragments.PopUps;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.ImageHandler;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.Main.Friends;
import com.example.zachary.drawertest.R;

public class PopUpItemSold extends Fragment implements View.OnClickListener {

    String AdID, UserID;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_itemsold, container, false);
        context = getActivity();
        view.findViewById(R.id.shadow).setOnClickListener(this);
        view.findViewById(R.id.confirm).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        Bundle args = getArguments();
        TextView Name = (TextView) view.findViewById(R.id.tvItemName);
        TextView Price = (TextView) view.findViewById(R.id.tvUsername);
        ImageView Image = (ImageView) view.findViewById(R.id.ivAdImage);
        Name.setText(args.getString("ItemName"));
        Price.setText("$" + args.getString("ItemPrice"));
        Image.setImageBitmap(ImageHandler.ReassembleBitmapfromString(args.getString("ItemImage"), ImageHandler.image_ad_thumb_dp()));
        AdID = args.getString("AdID");
        UserID = args.getString("UserID");
        return view;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.confirm) {
            ServerInterface.sendAdDelete(App.getUSRID(), AdID);
            Intent intent = new Intent(context, Friends.class);
            intent.putExtra("AdID", AdID);
            intent.putExtra("RateUser", true);
            startActivity(intent);
        }
        getActivity().getFragmentManager().popBackStack();
    }
}

