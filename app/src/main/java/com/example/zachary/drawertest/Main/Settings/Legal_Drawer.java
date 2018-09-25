package com.example.zachary.drawertest.Main.Settings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zachary.drawertest.R;

/**
 * Created by Zachary on 10-Jul-16.
 */
public class Legal_Drawer extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legal_legal1);
        findViewById(R.id.legalconfirm1).setOnClickListener(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_legal);
}

    // Advances through legal text
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.legalconfirm1:
                setContentView(R.layout.legal_legal2);
                findViewById(R.id.legalconfirm2).setOnClickListener(this);
                break;
            case R.id.legalconfirm2:
                setContentView(R.layout.legal_legal3);
                findViewById(R.id.legalconfirm3).setOnClickListener(this);
                break;
            case R.id.legalconfirm3:
                setContentView(R.layout.legal_legal4);
                findViewById(R.id.legalconfirm4).setOnClickListener(this);
                break;
            case R.id.legalconfirm4:
                onBackPressed();
                break;
        }
    }

    public void cancel (View view) {
        onBackPressed();
    }

}
