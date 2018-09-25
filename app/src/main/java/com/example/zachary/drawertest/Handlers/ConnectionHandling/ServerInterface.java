package com.example.zachary.drawertest.Handlers.ConnectionHandling;

import android.graphics.Bitmap;

import com.example.zachary.drawertest.Handlers.NavigationHandling.App;

import java.util.ArrayList;

/**
 * Created by Zachary on 18-Jul-16.
 */
public class ServerInterface {

    public static ArrayList getAdPreviewList(String search) {
        // Todo -
        ArrayList<DataAd> adDataArrayList = new ArrayList<>();
        adDataArrayList = RawData.generateAdListData(search);
        return adDataArrayList;
    }

    public static DataAd getAdPreviewSingle(String AdID) {
        // Todo -
        DataAd previewsingle = new DataAd();
        previewsingle = RawData.generateAdPreview(AdID);
        return previewsingle;
    }

    public static ArrayList getAdFavouriteList(ArrayList<String> favourites) {
        // Todo -
        ArrayList<DataAd> adFavouritesArrayList = new ArrayList<>();
        adFavouritesArrayList = RawData.generateAdFavouritesData(favourites);
        return adFavouritesArrayList;
    }

    public static ArrayList getUserAdList(String userid) {
        // Todo -
        ArrayList<DataAd> userAdArrayList = new ArrayList<>();
        userAdArrayList = RawData.generateAdListData(null);
        return userAdArrayList;
    }

    public static DataUser getUserProfile(String userid) {
        // Todo -
        DataUser dataUser = new DataUser();
        dataUser = RawData.generateUserProfile(userid);
        return dataUser;
    }

    /** To be added in a later version. */
    /*
    public static ArrayList getUserFavouriteList(ArrayList<String> favourites) {
        // Todo -
        ArrayList<DataUser> holder = new ArrayList<>();
        holder = RawData.generateFavouriteUsersList(favourites);
        return holder;
    }
    */

    public static ArrayList getUserRateList(String adid, String USRID) {
        // Todo -
        ArrayList<DataUser> dataUserList = new ArrayList<>();
        dataUserList = RawData.generateUserProfileList();
        return dataUserList;
    }

    public static DataAd getAdFull(String adid) {
        // Todo -
        DataAd dataAd = new DataAd();
        dataAd = RawData.generateFullAd(adid);
        return dataAd;
    }

    public static ArrayList getChatPreviewList(String USRID) {
        // Todo -
        ArrayList<DataChatPreview> chatPreviewArrayList = new ArrayList<>();
        chatPreviewArrayList = RawData.generateChatPreviews();
        return chatPreviewArrayList;
    }

    public static ArrayList getChatFullMessageList(String adid, String USRID, String userid) {
        // Todo -
        ArrayList<DataChatMessage> chatFullMessageArrayList = new ArrayList<>();
        chatFullMessageArrayList = RawData.createChatMessages();
        return chatFullMessageArrayList;
    }

    public static void sendUserNew (DataUser datauser) {
        // Todo -
    }

    public static String sendPictureUpdate (String USRID, Bitmap PPL, Bitmap PPS) {
        String holder = null;
        // Todo -

        return holder;
    }

    public static void sendUserRating (String userid, boolean voteup) {
        // Todo -
    }

    public static String sendAdPost(DataAd dataad) {
        String holder = null;
        // Todo -

        return holder;
    }

    /** To be added in a later version. */
    /*
    public static String sendAdUpdate(DataAd dataad) {
        String holder = null;
        // Todo -

        return holder;
    }
    */

    public static String sendAdDelete(String USRID, String adid) {
        String holder = null;
        // Todo -

        return holder;
    }

    public static String sendAdDeleteAll(String USRID) {
        String holder = null;
        // Todo -

        return holder;
    }

    public static void sendChatMessage(DataChatMessage chatmessage) {
        // Todo -

    }

    public static String sendChatDelete(){
        String holder = null;
        // Todo -

        return holder;
    }

    public static String sendFeedback(String feedback) {
        String holder = null;
        // Todo -

        return holder;
    }

    public static boolean loginLoginAttempt(String USR, String PASS) {
        boolean holder = false;
        // Boolean value determines whether call for User Object and progression
        // to the Main Drawer screen if activated.
        // Todo -

        holder = true;
        return holder;
    }

    public static DataUser loginLogin(String USR, String PASS) {
        DataUser dataUser = new DataUser();
        // Boolean value determines Toast Text in PopUpForgotPass
        // Todo -

        return dataUser;
    }

    public static boolean loginRetrieveEmail(String email) {
        boolean success = false;
        // Boolean value determines Toast Text in PopUpForgotPass
        // Todo -

        return success;
    }

    public static void loginLogOut() {
        // Todo -
        App.clearLoginALL();
    }

    public static boolean loginCheckUsername(String username) {
        boolean success = false;
        // Boolean value halts registration process in Register if false.
        // Todo -

        success = true;
        return success;
    }

}
