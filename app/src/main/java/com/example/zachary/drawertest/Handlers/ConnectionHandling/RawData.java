package com.example.zachary.drawertest.Handlers.ConnectionHandling;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import com.example.zachary.drawertest.Handlers.ImageHandler;
import com.example.zachary.drawertest.Handlers.NavigationHandling.App;
import com.example.zachary.drawertest.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Zachary on 18-Jul-16.
 */
public class RawData {


    public static String[] itemNameList = App.getContext().getResources().getStringArray(R.array.Item_mock);
    public static String[] itemPriceList = App.getContext().getResources().getStringArray(R.array.Price_mock);
    public static int[] mainImages = App.getContext().getResources().getIntArray(R.array.Item_images);
    public static String[] chatmessagepreview = {"Grass/Poison", "Grass/Poison", "Grass/Poison", "Fire", "Fire", "Fire/Flying", "Water", "Water", "Water"};
    public static String[] itemDescription = {"Grass/Poison", "Grass/Poison", "Grass/Poison", "Fire", "Fire", "Fire/Flying", "Water", "Water", "Water"};
    public static String[] chatIDList = {"Erika", "Erika", "Erika", "Blaine", "Blaine", "Blaine", "Misty", "Misty", "Misty"};
    public static String[] UserArray = App.getContext().getResources().getStringArray(R.array.Usernames_mock);
    public static String[] RatingArray = App.getContext().getResources().getStringArray(R.array.Ratings_mock);
    public static String[] PSEarray = App.getContext().getResources().getStringArray(R.array.PSE_mock);
    public static String[] ItemSoldArray = App.getContext().getResources().getStringArray(R.array.ItemsSold_mock);
    public static String[] Studyingarray = App.getContext().getResources().getStringArray(R.array.spinner_register2);
    public static String[] ConditionArray = {"New", "Used", "Broken"};
    public static ArrayList<Bitmap> AdPhoto = createItemThumb();
    public static ArrayList<Bitmap> UserPhoto = createUserThumb();
    public static ArrayList<Bitmap> AdPhotoMain = createAdImage();

    public static ArrayList createItemThumb() {
        ArrayList<Bitmap> adThumbMock = new ArrayList<Bitmap>();

        TypedArray tArray = App.getContext().getResources().obtainTypedArray(R.array.Item_images);
        int count = tArray.length();
        Integer[] ImageArray = new Integer[count];
        for (int i = 0; i < ImageArray.length; i++) {
            ImageArray[i] = tArray.getResourceId(i, 0);
            Bitmap resizedthumb = null;
            Drawable ProfileImage = App.getContext().getResources().getDrawable(ImageArray[i]);
            Bitmap B = ((BitmapDrawable) ProfileImage).getBitmap();
            try {
                resizedthumb = ImageHandler.ResizeBitmap(B, ImageHandler.image_ad_thumb_dp());
            } catch (IOException e) {
                e.printStackTrace();
            }
            adThumbMock.add(resizedthumb);
        }
        tArray.recycle();
        return adThumbMock;
    }

    public static ArrayList createUserThumb() {
        ArrayList<Bitmap> usrThumbMock = new ArrayList<Bitmap>();

        TypedArray tArray = App.getContext().getResources().obtainTypedArray(R.array.PPL_images_mock);
        int count = tArray.length();
        Integer[] PPLArray = new Integer[count];
        for (int i = 0; i < PPLArray.length; i++) {
            PPLArray[i] = tArray.getResourceId(i, 0);
            Bitmap resizedthumb = null;
            Drawable ProfileImage = App.getContext().getResources().getDrawable(PPLArray[i]);
            Bitmap B = ((BitmapDrawable) ProfileImage).getBitmap();
            try {
                resizedthumb = ImageHandler.ResizeBitmap(B, ImageHandler.image_profile_thumb_dp());
            } catch (IOException e) {
                e.printStackTrace();
            }
            usrThumbMock.add(resizedthumb);
        }
        tArray.recycle();
        return usrThumbMock;
    }

    public static ArrayList createAdImage() {
        ArrayList<Bitmap> adThumbMock = new ArrayList<Bitmap>();

        TypedArray tArray = App.getContext().getResources().obtainTypedArray(R.array.Item_images);
        int count = tArray.length();
        Integer[] ImageArray = new Integer[count];
        for (int i = 0; i < ImageArray.length; i++) {
            ImageArray[i] = tArray.getResourceId(i, 0);
            Bitmap resizedthumb = null;
            Drawable ProfileImage = App.getContext().getResources().getDrawable(ImageArray[i]);
            Bitmap B = ((BitmapDrawable) ProfileImage).getBitmap();
            try {
                resizedthumb = ImageHandler.ResizeBitmap(B, ImageHandler.image_ad_main_dp());
            } catch (IOException e) {
                e.printStackTrace();
            }
            adThumbMock.add(resizedthumb);
        }
        tArray.recycle();
        return adThumbMock;
    }

    //----------------------------------------------------------------------------------------------

    public static DataUser generateUserProfile(String userid) {
        int UserID = Integer.parseInt(userid);
        DataUser dataUser = new DataUser();

        if (UserID == 9) {
            SharedPreferences info = PreferenceManager.getDefaultSharedPreferences(App.getContext());
            dataUser.setuserUsername(info.getString("SavedUsername", ""));
            dataUser.setuserRating(String.valueOf(100));
            dataUser.setuserPSE(info.getString("PSE", ""));
            dataUser.setuserItemsSold(String.valueOf(42));
            dataUser.setuserUserMain(ImageHandler.ReassembleBitmapfromString(info.getString("PPL", ""), ImageHandler.image_profile_main_px()));
            dataUser.setuserStudying(info.getString("STD", ""));
            dataUser.setuserUserID(userid);
        } else {
            Bitmap resizedfull = null;
            try {
                resizedfull = ImageHandler.ResizeBitmap(UserPhoto.get(UserID), ImageHandler.image_profile_main_px());
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataUser.setuserUsername(UserArray[UserID]);
            dataUser.setuserRating(RatingArray[UserID]);
            dataUser.setuserPSE(PSEarray[UserID]);
            dataUser.setuserItemsSold(ItemSoldArray[UserID]);
            dataUser.setuserUserMain(resizedfull);
            dataUser.setuserStudying(Studyingarray[((int) Math.floor(Math.random() * Studyingarray.length))]);
            dataUser.setuserUserID(userid);
        }
        return dataUser;
    }

    public static ArrayList generateUserProfileList() {
        ArrayList<DataUser> dataUserArrayList = new ArrayList<>();
        for (int i = 0; i < UserPhoto.size(); i++) {
            DataUser dataUser = new DataUser();
            dataUser.setuserUsername(UserArray[i]);
            dataUser.setuserRating(RatingArray[i]);
            dataUser.setuserPSE(PSEarray[i]);
            dataUser.setuserItemsSold(ItemSoldArray[i]);
            dataUser.setuserUserMain(UserPhoto.get(i));
            dataUser.setuserStudying(Studyingarray[((int) Math.floor(Math.random() * Studyingarray.length))]);
            dataUser.setuserUserID(String.valueOf(i));

            dataUserArrayList.add(dataUser);
        }
        return dataUserArrayList;
    }


    public static ArrayList generateChatPreviews() {
        ArrayList<DataChatPreview> holder = new ArrayList<>();
        for (int i = 0; i < mainImages.length; i++) {
            final int x = i;
            final int UserID = (int) Math.floor(Math.random() * UserPhoto.size());
            DataChatPreview chatPreview = new DataChatPreview();
            chatPreview.setChatPreview(UserArray[UserID], "Elapsam semel occasionem no ipse potest Jupiter reprehendere. Not even Jupiter can find a lost opportunity.",
                    itemPriceList[i], itemNameList[i], itemPriceList[i], String.valueOf(UserID), AdPhoto.get(i), UserPhoto.get(UserID));
            holder.add(chatPreview);
        }
        return holder;
    }

    public static DataAd generateAdPreview(String adid) {
        ArrayList<String> itemnametemp = new ArrayList<>();
        Collections.addAll(itemnametemp, itemPriceList);

        final int x = itemnametemp.indexOf(adid);
        final int UserID = (int) Math.floor(Math.random() * UserPhoto.size());
        DataAd dataAd = new DataAd(
                itemNameList[x], itemPriceList[x],
                UserArray[UserID], RatingArray[UserID],
                String.valueOf(x), String.valueOf(UserID),
                AdPhoto.get(x), UserPhoto.get(UserID)
        );
        return dataAd;
    }

    public static DataAd generateFullAd(String adid) {
        ArrayList<String> itemnametemp = new ArrayList<>();
        Collections.addAll(itemnametemp, itemPriceList);

        final int x = itemnametemp.indexOf(adid);
        final int UserID = (int) Math.floor(Math.random() * UserPhoto.size());
        DataAd dataAd = new DataAd(
                itemNameList[x], itemPriceList[x],
                UserArray[UserID], RatingArray[UserID],
                String.valueOf(x), String.valueOf(UserID),
                AdPhoto.get(x), UserPhoto.get(UserID)
        );
        dataAd.setadCondition(ConditionArray[(int) Math.floor(Math.random() * ConditionArray.length)]);
        dataAd.setadDescription("Elapsam semel occasionem no ipse potest Jupiter reprehendere. Not even Jupiter can find a lost opportunity.");
        dataAd.setadImageA(AdPhotoMain.get(x));

        //////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Bitmap> adThumbMock = new ArrayList<Bitmap>();
        Bitmap resizedthumb = null;
        TypedArray tArray = App.getContext().getResources().obtainTypedArray(R.array.Item_images_secondary);
        int count = tArray.length();
        Integer[] PPLArray = new Integer[count];
        for (int i = 0; i < PPLArray.length; i++) {
            PPLArray[i] = tArray.getResourceId(i, 0);
            Drawable ProfileImage = App.getContext().getResources().getDrawable(PPLArray[i]);
            Bitmap B = ((BitmapDrawable) ProfileImage).getBitmap();
            try {
                resizedthumb = ImageHandler.ResizeBitmap(B, ImageHandler.image_profile_main_dp());
            } catch (IOException e) {
                e.printStackTrace();
            }
            adThumbMock.add(resizedthumb);
        }
        //////////////////////////////////////////////////////////
        switch (x) {
            case 6:
                dataAd.setadImageB(adThumbMock.get(0));
                break;
            case 8:
                dataAd.setadImageB(adThumbMock.get(1));
                break;
            case 9:
                dataAd.setadImageB(adThumbMock.get(2));
                break;
            case 10:
                dataAd.setadImageB(adThumbMock.get(3));
                break;
            default:
                break;
        }
        return dataAd;
    }

    public static ArrayList generateAdFavouritesData(ArrayList<String> favourites) {
        ArrayList<DataAd> holder = new ArrayList<>();

        ArrayList<String> itemnametemp = new ArrayList<>();
        Collections.addAll(itemnametemp, itemPriceList);
        for (int i = 0; i < favourites.size(); i++) {
            final int x = itemnametemp.indexOf(favourites.get(i));
            final int UserID = (int) Math.floor(Math.random() * UserPhoto.size());
            DataAd dataAd = new DataAd(
                    itemNameList[x], itemPriceList[x],
                    UserArray[UserID], RatingArray[UserID],
                    itemPriceList[x], String.valueOf(UserID),
                    AdPhoto.get(x), UserPhoto.get(UserID)
            );
            holder.add(dataAd);
        }
        return holder;
    }


    public static ArrayList generateAdListData(String search) {
        ArrayList<DataAd> holder = new ArrayList<>();

        if (search == null) {search = "ALL";}
        if (search.equals("ALL")) {
            for (int i = 0; i < mainImages.length; i++) {
                final int x = i;
                final int UserID = (int) Math.floor(Math.random() * UserPhoto.size());
                DataAd dataAd = new DataAd(
                        itemNameList[x], itemPriceList[x],
                        UserArray[UserID], RatingArray[UserID],
                        itemPriceList[x], String.valueOf(UserID),
                        AdPhoto.get(x), UserPhoto.get(UserID)
                );
                holder.add(dataAd);
            }
        } else {
            for (int x = 0; x < mainImages.length; x++) {
                if (itemNameList[x].contains(search)) {
                    final int UserID = (int) Math.floor(Math.random() * UserPhoto.size());
                    DataAd dataAd = new DataAd(
                            itemNameList[x], itemPriceList[x],
                            UserArray[UserID], RatingArray[UserID],
                            itemPriceList[x], String.valueOf(UserID),
                            AdPhoto.get(x), UserPhoto.get(UserID)
                    );
                    holder.add(dataAd);
                }
            }
        }

        return holder;
    }

    public static ArrayList createChatMessages() {
        ArrayList<DataChatMessage> holder = new ArrayList<>();
        String USRID = App.getUSRID();

        String [] chatChatMessage = {"Hey, cool thing you got there. Are you going to be at MUN tomorrow?", "No, but I will be Wednesday", "Meet in the UC at 6pm Wednesday then?", "Sure! see you then"};
        String [] userUserID = {USRID,"0",USRID,"0"};
        String [] chatTimeStamp = {"Aug 7 6:20 PM","Aug 8 9:57 AM","Aug 8 10:22 AM","Aug 8 3:18 PM"};
        String chatChatID = "XYZ-987";

        for (int i = 0; i < chatChatMessage.length; i++) {
            DataChatMessage chatMessage = new DataChatMessage(
                    chatChatMessage[i], userUserID[i], chatTimeStamp[i], chatChatID);

            holder.add(chatMessage);
        }
        return holder;
    }

}
