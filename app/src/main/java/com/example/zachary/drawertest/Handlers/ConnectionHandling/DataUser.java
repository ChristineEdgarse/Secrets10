package com.example.zachary.drawertest.Handlers.ConnectionHandling;

import android.graphics.Bitmap;

/**
 * Created by Zachary on 18-Jul-16.
 */
public class DataUser {

    String userUsername, userPassword, userEmail, userRating, userItemsSold, userPSE, userStudying,
            userUserID, userGender, userBirthDate;
    Bitmap userUserMain, userUserThumb;

    public DataUser() {   }

    public DataUser(String userUsername, String userPassword, String userEmail, String userRating,
                    String userItemsSold, String userPSE,
                    String userStudying, String userUserID, String userGender, String userBirthDate,
                    Bitmap userUserMain, Bitmap userUserThumb) {

        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRating = userRating;
        this.userItemsSold = userItemsSold;
        this.userPSE = userPSE;
        this.userStudying = userStudying;
        this.userUserID = userUserID;
        this.userGender = userGender;
        this.userBirthDate = userBirthDate;
        this.userUserMain = userUserMain;
        this.userUserThumb = userUserThumb;
    }

    public String getuserUsername() { return userUsername; }
    public String getuserPassword() { return userPassword; }
    public String getuserEmail() { return userEmail; }
    public String getuserRating() { return userRating; }
    public String getuserItemsSold() { return userItemsSold; }
    public String getuserPSE() { return userPSE; }
    public String getuserStudying() { return userStudying; }
    public String getuserUserID() { return userUserID; }
    public String getuserGender() { return userGender; }
    public String getuserBirthDate() { return userBirthDate; }
    public Bitmap getuserUserMain() { return userUserMain; }
    public Bitmap getuserUserThumb() { return userUserThumb; }


    public void setuserUsername(String userUsername) { this.userUsername = userUsername; }
    public void setuserPassword(String userPassword) { this.userPassword = userPassword; }
    public void setuserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setuserRating(String userRating) { this.userRating = userRating; }
    public void setuserItemsSold(String userItemsSold) { this.userItemsSold = userItemsSold; }
    public void setuserPSE(String userPSE) { this.userPSE = userPSE; }
    public void setuserStudying(String userStudying) { this.userStudying = userStudying; }
    public void setuserUserID(String userUserID) { this.userUserID = userUserID; }
    public void setuserGender(String userGender) { this.userGender = userGender; }
    public void setuserBirthDate(String userBirthDate) { this.userBirthDate = userBirthDate; }
    public void setuserUserMain(Bitmap userUserMain) { this.userUserMain = userUserMain; }
    public void setuserUserThumb(Bitmap userUserThumb) { this.userUserThumb = userUserThumb; }


    public void setUserProfile(String userUsername, String userPassword, String userEmail,
//                               String userRating, String userItemsSold,
                               String userPSE, String userStudying, String userGender, String userBirthDate,
                               Bitmap userUserMain, Bitmap userUserThumb) {
        setuserUsername(userUsername);
        setuserPassword(userPassword);
        setuserEmail(userEmail);
//        setuserRating(userRating);
//        setuserItemsSold(userItemsSold);
        setuserPSE(userPSE);
        setuserStudying(userStudying);
        setuserGender(userGender);
        setuserBirthDate(userBirthDate);
        setuserUserMain(userUserMain);
        setuserUserThumb(userUserThumb);
    }

    /**
     public void setUserALL(String userUsername, String userPassword, String userEmail, String userRating,
     String userItemsSold, String userPSE,
     String userStudying, String userUserID, String userGender, String userBirthDate,
     Bitmap userUserMain, Bitmap userUserThumb, String userUserMainString, String userUserThumbString) {
     setuserUsername(userUsername);
     setuserPassword(userPassword);
     setuserEmail(userEmail);
     setuserRating(userRating);
     setuserItemsSold(userItemsSold);
     setuserPSE(userPSE);
     setuserStudying(userStudying);
     setuserUserID(userUserID);
     setuserGender(userGender);
     setuserBirthDate(userBirthDate);
     setuserUserMain(userUserMain);
     setuserUserThumb(userUserThumb);
     setuserUserMainString(userUserMainString);
     setuserUserThumbString(userUserThumbString);
     }
     */

}
