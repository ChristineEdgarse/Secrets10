package com.example.zachary.drawertest.Main.Settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataUser;
import com.example.zachary.drawertest.Handlers.ConnectionHandling.ServerInterface;
import com.example.zachary.drawertest.Handlers.ImageHandler;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.Main.Drawer_Main;
import com.example.zachary.drawertest.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Zachary on 20-Jul-16.
 */
public class ChangeProfilePicture extends AppCompatActivity {

    Context context;
    CircleImageView ivProfilePicture, ivProfilePictureSmall;
    Bitmap ProfilePictureLarge, ProfilePictureSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changeprofilepicture);
        context = this;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_changeprofilepicture);

        ivProfilePicture = (CircleImageView) findViewById(R.id.profilepicture);
        ivProfilePictureSmall = (CircleImageView) findViewById(R.id.profilepicturethumb);
        ProfilePictureLarge = ImageHandler.ReassembleBitmapfromString(App.getPPL(), ImageHandler.image_profile_main_dp());
        ProfilePictureSmall = ImageHandler.ReassembleBitmapfromString(App.getPPS(), ImageHandler.image_profile_thumb_dp());
        ivProfilePicture.setImageBitmap(ProfilePictureLarge);
        ivProfilePictureSmall.setImageBitmap(ProfilePictureSmall);
    }

    public void AddPhoto(View view) {
        Intent intent = new Intent();
        intent.setType(ImageHandler.IMAGE_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        int intentid = ImageHandler.PROFILEPICTURE;
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), intentid);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // What ImageButton was clicked
            switch (requestCode) {
                case ImageHandler.PROFILEPICTURE:
                    ProfilePictureLarge = HandleImage(ivProfilePicture, ImageHandler.image_profile_main_px(), data);
                    if (ProfilePictureLarge == null) {setNullImages(); return;}
                    ProfilePictureSmall = HandleImage(ivProfilePictureSmall, ImageHandler.image_profile_thumb_px(), data);
                    break;
            }
        }
    }

    private Bitmap HandleImage(CircleImageView iv, int size, Intent data) {
        Bitmap bitmap = ImageHandler.ImagefromStoragetoBitmap(data, size, context);
        ImageHandler.SetImage(bitmap, iv);
        return bitmap;
    }

    private void setNullImages () {
        Drawable ProfileImage = getResources().getDrawable(R.drawable.logopurple);
        Bitmap B = ((BitmapDrawable) ProfileImage).getBitmap();
        try {
            ProfilePictureLarge = ImageHandler.ResizeBitmap(B, ImageHandler.image_profile_main_px());
            ProfilePictureSmall = ImageHandler.ResizeBitmap(B, ImageHandler.image_profile_thumb_px());
        } catch (IOException e) { e.printStackTrace(); }
    }


    public void changeProfilePicture(View view) {
        if (ProfilePictureLarge == null)  {setNullImages();}
        App.setPPL(ImageHandler.DeassembleBitmaptoString(ProfilePictureLarge));
        App.setPPS(ImageHandler.DeassembleBitmaptoString(ProfilePictureSmall));
        ServerInterface.sendPictureUpdate(App.getUSRID(), ProfilePictureLarge, ProfilePictureSmall);

        Intent changedrawer = new Intent(this, Drawer_Main.class);
        changedrawer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(changedrawer);
    }
}