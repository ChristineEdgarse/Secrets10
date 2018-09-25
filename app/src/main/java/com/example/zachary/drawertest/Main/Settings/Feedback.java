package com.example.zachary.drawertest.Main.Settings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.R;

/**
 * Created by Zachary on 20-Jul-16.
 */
public class Feedback extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_feedback);
    }

    public void sendFeedback (View view) {
        EditText et = (EditText) findViewById(R.id.etFeedback);
        String toast = ServerInterface.sendFeedback(et.getText().toString());
        Toast.makeText(Feedback.this, toast, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
