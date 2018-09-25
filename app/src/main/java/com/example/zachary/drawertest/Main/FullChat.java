package com.example.zachary.drawertest.Main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataChatMessage;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.Main.Fragments.PopUps.PopUpItemInfo;
import com.example.zachary.drawertest.Handlers.CustomAdapters.CustomAdapterChatMessages;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Zachary on 10-Jul-16.
 */
public class FullChat extends AppCompatActivity  {

    ArrayList <DataChatMessage> dataChatMessageList = new ArrayList<>();

    Context context;
    String Username, AdID, UserID, USRID, ChatID;
    boolean deleteconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullchat);
        context = this;
        USRID = App.getUSRID();
        AdID = getIntent().getStringExtra("AdID");
        UserID = getIntent().getStringExtra("UserID");
        Username = getIntent().getStringExtra("Username");
        deleteconfirm = false;

        dataChatMessageList = ServerInterface.getChatFullMessageList(AdID, USRID, UserID);
        getSupportActionBar().setTitle(Username);
        refreshAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            hideSoftKeyboard(this);
            infopopup();
        }
        if (id == R.id.action_delete) {
            if (!deleteconfirm) {
                deleteconfirm = true;
                Toast.makeText(FullChat.this, "Click again to delete chat.", Toast.LENGTH_SHORT).show();
            }
            else {
                ServerInterface.sendChatDelete();
                onBackPressed();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendmessage(View view) {
        EditText etMessage = (EditText) findViewById(R.id.etMessage);
        String message = etMessage.getText().toString().trim();
        etMessage.setText("");

        Calendar c = Calendar.getInstance();
        int intMonth = c.get(Calendar.MONTH) + 1;
        String Month = null;
        switch (intMonth) {
            case 1: Month = "Jan"; break;
            case 2: Month = "Feb"; break;
            case 3: Month = "Mar"; break;
            case 4: Month = "Apr"; break;
            case 5: Month = "May"; break;
            case 6: Month = "Jun"; break;
            case 7: Month = "Jul"; break;
            case 8: Month = "Aug"; break;
            case 9: Month = "Sep"; break;
            case 10: Month = "Oct"; break;
            case 11: Month = "Nov"; break;
            case 12: Month = "Dec"; break;
        }
        int Day = c.get(Calendar.DAY_OF_MONTH);
        int Hour = c.get(Calendar.HOUR);
        if (Hour == 0) {Hour = 12;}
        int intMinute = c.get(Calendar.MINUTE);
        String Minute = null;
        switch (intMinute) {
            case 1: Minute = "01"; break;
            case 2: Minute = "02"; break;
            case 3: Minute = "03"; break;
            case 4: Minute = "04"; break;
            case 5: Minute = "05"; break;
            case 6: Minute = "06"; break;
            case 7: Minute = "07"; break;
            case 8: Minute = "08"; break;
            case 9: Minute = "09"; break;
            default: Minute = String.valueOf(intMinute);
        }
        int intAMPM = c.get(Calendar.AM_PM);
        String AMPM = null;
        if (intAMPM == 0) {AMPM = "AM";} else {AMPM = "PM";}


        String timestamp = Month + "-" + Day + " " + Hour + ":" + Minute + " " + AMPM;;

        DataChatMessage dataChatMessage = new DataChatMessage(message, USRID, timestamp, ChatID);
        ServerInterface.sendChatMessage(dataChatMessage);
        dataChatMessageList.add(dataChatMessage);
        refreshAdapter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.headerbar_fullchat, menu);
        return true;
    }

    public void infopopup () {
        Fragment fragment = getFragmentManager().findFragmentByTag("InfoPopUp");
        if (fragment != null && fragment.isVisible()) { getFragmentManager().popBackStack(); }
        Fragment newFragment = new PopUpItemInfo();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle args = new Bundle();
        args.putString("AdID", AdID);
        args.putString("UserID", UserID);
        newFragment.setArguments(args);
        FragmentTransaction FT = fragmentManager.beginTransaction();
        FT.replace(R.id.fragmentholder, newFragment, "InfoPopUp");
        FT.addToBackStack("InfoPopUp");
        FT.commit();
    }

    @Override
    public void onBackPressed() {
        PopUpItemInfo myFragment = (PopUpItemInfo)getFragmentManager().findFragmentByTag("InfoPopUp");
        if (myFragment != null && myFragment.isVisible())
        { getFragmentManager().popBackStack(); }
        else
        { super.onBackPressed(); }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void refreshAdapter() {
        RecyclerView fullchat = (RecyclerView) findViewById(R.id.lvFullChat);
        CustomAdapterChatMessages mAdapter = new CustomAdapterChatMessages(context, dataChatMessageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setStackFromEnd(true);
        fullchat.setLayoutManager(layoutManager);
        fullchat.setItemAnimator(new DefaultItemAnimator());
        fullchat.setAdapter(mAdapter);
    }

}
