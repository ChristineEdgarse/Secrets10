package com.example.zachary.drawertest.Handlers.ConnectionHandling;

import android.graphics.Bitmap;

/**
 * Created by Zachary on 18-Jul-16.
 */
public class DataAd {

    String adName, adPrice, userUsername, userRating, adAdID, userUserID, adCondition, adDescription, adTags;
    Bitmap adImageThumb, userUserThumb, adImageBitmapA, adImageBitmapB, adImageBitmapC, adImageBitmapD, adImageBitmapE;

    public DataAd() {   }

    public DataAd(String adName, String adPrice, String userUsername, String userRating,
                  String adAdID, String userUserID, Bitmap adImageThumb,
                  Bitmap userUserThumb, String adCondition, String adDescription, String adTags,
                  Bitmap adImageBitmapA, Bitmap adImageBitmapB, Bitmap adImageBitmapC, Bitmap adImageBitmapD, Bitmap adImageBitmapE) {

        this.adName = adName;
        this.adPrice = adPrice;
        this.userUsername = userUsername;
        this.userRating = userRating;
        this.adAdID = adAdID;
        this.userUserID = userUserID;
        this.adImageThumb = adImageThumb;
        this.userUserThumb = userUserThumb;
        this.adCondition = adCondition;
        this.adDescription = adDescription;
        this.adTags = adTags;
        this.adImageBitmapA = adImageBitmapA;
        this.adImageBitmapB = adImageBitmapB;
        this.adImageBitmapC = adImageBitmapC;
        this.adImageBitmapD = adImageBitmapD;
        this.adImageBitmapE = adImageBitmapE;
    }

    public DataAd(String adName, String adPrice,
                  String userUsername, String userRating,
                  String adAdID, String userUserID,
                  Bitmap adImageThumb, Bitmap userUserThumb) {

        this.adName = adName;
        this.adPrice = adPrice;
        this.userUsername = userUsername;
        this.userRating = userRating;
        this.adAdID = adAdID;
        this.userUserID = userUserID;
        this.adImageThumb = adImageThumb;
        this.userUserThumb = userUserThumb;
    }

    public String getadName() { return adName; }
    public String getadPrice() { return adPrice; }
    public String getuserUsername() { return userUsername; }
    public String getuserRating() { return userRating; }
    public String getadAdID() { return adAdID; }
    public String getuserUserID() { return userUserID; }
    public Bitmap getadImageThumb() { return adImageThumb; }
    public Bitmap getuserUserThumb() { return userUserThumb; }
    public String getadCondition() { return adCondition; }
    public String getadDescription() { return adDescription; }
    public String getadTags() { return adTags; }
    public Bitmap getadImageBitmapA() { return adImageBitmapA; }
    public Bitmap getadImageBitmapB() { return adImageBitmapB; }
    public Bitmap getadImageBitmapC() { return adImageBitmapC; }
    public Bitmap getadImageBitmapD() { return adImageBitmapD; }
    public Bitmap getadImageBitmapE() { return adImageBitmapE; }



    public void setadName(String adName) { this.adName = adName; }
    public void setadPrice(String adPrice) { this.adPrice = adPrice; }
    public void setuserUsername(String userUsername) { this.userUsername = userUsername; }
    public void setuserRating(String userRating) { this.userRating = userRating; }
    public void setadAdID(String adAdID) { this.adAdID = adAdID; }
    public void setuserUserID(String userUserID) { this.userUserID = userUserID; }
    public void setadImageThumb(Bitmap adImageThumb) { this.adImageThumb = adImageThumb; }
    public void setuserUserThumb(Bitmap userUserThumb) { this.userUserThumb = userUserThumb; }
    public void setadCondition(String adCondition) { this.adCondition = adCondition; }
    public void setadDescription(String adDescription) { this.adDescription = adDescription; }
    public void setadTags(String adTags) { this.adTags = adTags; }
    public void setadImageA(Bitmap adImageBitmapA) { this.adImageBitmapA = adImageBitmapA; }
    public void setadImageB(Bitmap adImageBitmapB) { this.adImageBitmapB = adImageBitmapB; }
    public void setadImageC(Bitmap adImageBitmapC) { this.adImageBitmapC = adImageBitmapC; }
    public void setadImageD(Bitmap adImageBitmapD) { this.adImageBitmapD = adImageBitmapD; }
    public void setadImageE(Bitmap adImageBitmapE) { this.adImageBitmapE = adImageBitmapE; }



    public void setAdPreview(String adName, String adPrice, String userUsername, String userRating, String adAdID,
                             String userUserID, Bitmap adImageThumb, Bitmap userUserThumb) {
        setadName(adName);
        setadPrice(adPrice);
        setuserUsername(userUsername);
        setuserRating(userRating);
        setadAdID(adAdID);
        setuserUserID(userUserID);
        setadImageThumb(adImageThumb);
        setuserUserThumb(userUserThumb);
    }


    public void setAdCreate(String adName, String adPrice,
                            String userUsername, String userUserID,
                            Bitmap adImageThumb, Bitmap userUserThumb,
                            String adCondition, String adDescription, String adTags,
                            Bitmap adImageA, Bitmap adImageB, Bitmap adImageC, Bitmap adImageD, Bitmap adImageE) {
        setadName(adName);
        setadPrice(adPrice);
        setuserUsername(userUsername);
        setuserUserID(userUserID);
        setadImageThumb(adImageThumb);
        setuserUserThumb(userUserThumb);
        setadCondition(adCondition);
        setadDescription(adDescription);
        setadTags(adTags);
        setadImageA(adImageA);
        setadImageB(adImageB);
        setadImageC(adImageC);
        setadImageD(adImageD);
        setadImageE(adImageE);
    }

    /*
    /////////////////////////////

    public void getAdPreview() {
        getadName();
        getadPrice();
        getuserUsername();
        getuserRating();
        getadAdID();
        getuserUserID();
        getadImageThumb();
        getuserUserThumb();
    }

    public void getAdFull() {
        getadCondition();
        getadDescription();
        getadImageA();
        getadImageB();
        getadImageC();
        getadImageD();
        getadImageE();
    }
    */


    /***
     public void setAdALL() {
     setadName(adName);
     setadPrice(adPrice);
     setuserUsername(userUsername);
     setuserRating(userRating);
     setadAdID(adAdID);
     setuserUserID(userUserID);
     setadImageThumb(adImageThumb);
     setadImageStringThumb(adImageStringThumb);
     setuserUserThumb(userUserThumb);
     setuserImageStringThumb(userImageStringThumb);
     setadCondition(adCondition);
     setadDescription(adDescription);
     setadImageA(adImageBitmapA);
     setadImageB(adImageBitmapB);
     setadImageC(adImageBitmapC);
     setadImageD(adImageBitmapD);
     setadImageE(adImageBitmapE);
     setadImageStringA(adImageStringA);
     setadImageStringB(adImageStringB);
     setadImageStringC(adImageStringC);
     setadImageStringD(adImageStringD);
     setadImageStringE(adImageStringE);
     }
     ***/
}
