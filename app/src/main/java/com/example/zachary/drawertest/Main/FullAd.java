package com.example.zachary.drawertest.Main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataAd;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.ImageHandler;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.Handlers.NavigationHandling.NavigationController;
import com.example.zachary.drawertest.R;

import java.util.ArrayList;

/**
 * Created by Zachary on 10-Jul-16.
 */
public class FullAd extends AppCompatActivity  {

    ArrayList <Bitmap> admainImages = new ArrayList<>();
    ImageView[] Imageholders = new ImageView[0];
    ArrayList<String> Favourites = new ArrayList<>();

    ImageView ivUserThumb, ivImageA, ivImageB, ivImageC, ivImageD, ivImageE;
    TextView tvUserRating, tvUsername, tvPrice, tvCondition, tvDescription;
    Toolbar toolbar = null;
    String AdName, Username, AdIDtext;
    DataAd dataAd;
    boolean favourited;
    int UserID, AdID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullad);

        AdIDtext = getIntent().getStringExtra("AdID");
        AdID = Integer.parseInt(getIntent().getStringExtra("AdID")) - 1;
        UserID = Integer.parseInt(getIntent().getStringExtra("UserID"));
        AdName = getIntent().getStringExtra("ItemName");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setTitle(AdName);

        ivUserThumb = (ImageView) findViewById(R.id.profilepicturethumb);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUserRating = (TextView) findViewById(R.id.tvUserRating);

        ivImageA = (ImageView) findViewById(R.id.ivImageA);
        ivImageB = (ImageView) findViewById(R.id.ivImageB);
        ivImageC = (ImageView) findViewById(R.id.ivImageC);
        ivImageD = (ImageView) findViewById(R.id.ivImageD);
        ivImageE = (ImageView) findViewById(R.id.ivImageE);
        Imageholders = new ImageView[]{ivImageA, ivImageB, ivImageC, ivImageD, ivImageE};

        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        tvDescription = (TextView) findViewById(R.id.tvDescription);

        dataAd = ServerInterface.getAdFull(AdIDtext);

        ivUserThumb.setImageBitmap(dataAd.getuserUserThumb());
        tvUsername.setText(dataAd.getuserUsername());
        tvUserRating.setText(dataAd.getuserRating() + "% Rating");

        admainImages.add(dataAd.getadImageBitmapA());
        admainImages.add(dataAd.getadImageBitmapB());
        admainImages.add(dataAd.getadImageBitmapC());
        admainImages.add(dataAd.getadImageBitmapD());
        admainImages.add(dataAd.getadImageBitmapE());
        setImages();

        tvPrice.setText("$" + dataAd.getadPrice());
        tvCondition.setText(dataAd.getadCondition());
        tvDescription.setText(dataAd.getadDescription());

        String Tags = dataAd.getadTags();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_favourite) {
            favourited = (Favourites.indexOf(AdIDtext) != -1);
            if (!favourited) {
                item.setIcon(R.drawable.ic_favorite_white_24dp);
                Favourites.add(AdIDtext);
            } else {
                item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                Favourites.remove(Favourites.indexOf(AdIDtext));
            }
            App.setFavouriteAds(Favourites);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setImages () {
        for (int z = 0; z < admainImages.size(); z++) {
            ImageView imagehandler = Imageholders[z];
            if (admainImages.get(z) != null) {
                imagehandler.setImageBitmap(admainImages.get(z));
                imagehandler.getLayoutParams().height = ImageHandler.image_ad_main_dp();
                imagehandler.getLayoutParams().width = ImageHandler.image_ad_main_dp();
            }
        }
    }

    public void ToOtherProfile (View view) {
        if (UserID == 9) {
            onBackPressed(); return;
        }
        NavigationController.toOtherProfile(this, String.valueOf(UserID));
    }

    public void ToFullChat (View view) {
        if (UserID == 9) {
            Toast.makeText(FullAd.this, "Cannot start chat with oneself!", Toast.LENGTH_SHORT).show();
        }
        else {
            NavigationController.toFullChat(this, String.valueOf(UserID), AdIDtext, Username);
        }
    }

    @Override
    public void onResume () {
        super.onResume();
        this.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.headerbar_fullad, menu);

        Favourites.clear();
        Favourites = App.getFavouriteAds();

        if (Favourites.indexOf(AdIDtext) != -1) {
            MenuItem heart = menu.findItem(R.id.action_favourite);
            heart.setIcon(R.drawable.ic_favorite_white_24dp);
        }
        return true;
    }

}
