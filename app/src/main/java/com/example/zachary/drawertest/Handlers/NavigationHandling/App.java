package com.example.zachary.drawertest.Handlers.NavigationHandling;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.zachary.drawertest.Handlers.ConnectionHandling.DataUser;
import com.example.zachary.drawertest.Handlers.ImageHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zachary on 18-Jul-16.
 */
public class App extends Application {

    private static Context mContext;
    private static SharedPreferences storage;
    private static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        storage = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = storage.edit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    public static void setSTD(String data) {toStorage("STD", data);}
    public static void setPSE(String data) {toStorage("PSE", data);}
    /////////////////////////////////////////////////////////////////////////////////////////

    public static Context getContext(){
        return mContext;
    }

    public static String fromStorage(String caller) {
        return storage.getString(caller, "");
    }

    public static void toStorage(String caller, String data) {
        editor.putString(caller, data);
        editor.apply();
    }

    public static String getSavedUsername() {return fromStorage("SavedUsername");}
    public static String getSavedPassword() {return fromStorage("SavedPassword");}
    public static String getUSR() {return fromStorage("USR");}
    public static String getPASS() {return fromStorage("PASS");}
    public static String getUSRID() {return fromStorage("USRID");}
    public static String getPPS() {return fromStorage("PPS");}
    public static String getPPL() {return fromStorage("PPL");}
    public static String getEmail() {return fromStorage("Email");}

    public static boolean getFirstLoad() {
        return storage.getBoolean("FirstLoad", true);
    }

    public static int getMaxAds() {
        return storage.getInt("MaxAds", 50);
    }

    public static ArrayList getFavouriteAds() {
        ArrayList<String> holder = new ArrayList<>();
        Set<String> StringSet = storage.getStringSet("FavouriteAds", null);
        if (StringSet != null) {holder.addAll(StringSet);}
        return holder;
    }

    public static ArrayList getFavouriteUsers() {
        ArrayList<String> holder = new ArrayList<>();
        Set<String> StringSet = storage.getStringSet("FavouritesUsers", null);
        if (StringSet != null) {holder.addAll(StringSet);}
        return holder;
    }

    public static void setSavedUsername(String data) {toStorage("SavedUsername", data);}
    public static void setSavedPassword(String data) {toStorage("SavedPassword", data);}
    public static void setUSR(String data) {toStorage("USR", data);}
    public static void setPASS(String data) {toStorage("PASS", data);}
    public static void setUSRID(String data) {toStorage("USRID", data);}
    public static void setPPL(String data) {toStorage("PPL", data);}
    public static void setPPS(String data) {toStorage("PPS", data);}
    public static void setEmail(String data) {toStorage("Email", data);}

    public static void setLoginALL(DataUser datauser) {
        setUSR(datauser.getuserUsername());
        setPASS(datauser.getuserPassword());
        setUSRID(datauser.getuserUserID());
        setPPL(ImageHandler.DeassembleBitmaptoString(datauser.getuserUserMain()));
        setPPS(ImageHandler.DeassembleBitmaptoString(datauser.getuserUserThumb()));
        setEmail(datauser.getuserEmail());
    }

    public static void setFavouriteAds(ArrayList favourite) {
        Set<String> StringSet = new HashSet<>(favourite);
        StringSet.addAll(favourite);
        editor.remove("FavouriteAds");
        editor.apply();
        editor.putStringSet("FavouriteAds", StringSet);
        editor.apply();
    }

    public static void setFavouriteUsers(ArrayList favourite) {
        Set<String> StringSet = new HashSet<>(favourite);
        StringSet.addAll(favourite);
        editor.remove("FavouritesUsers");
        editor.apply();
        editor.putStringSet("FavouritesUsers", StringSet);
        editor.apply();
    }


    public static void setFirstLoad() {
        editor.putBoolean("FirstLoad", false);
        editor.apply();
    }

    public static void clearSavedUsername() {setSavedUsername(null);}
    public static void clearSavedPassword() {setSavedPassword(null);}
    public static void clearUSR() {setUSR(null);}
    public static void clearPASS() {setPASS(null);}
    public static void clearUSRID() {setUSRID(null);}
    public static void clearPPL() {setPPL(null);}
    public static void clearPPS() {setPPS(null);}
    public static void clearEmail() {setEmail(null);}


    public static void clearLoginALL() {
        clearSavedUsername();
        clearSavedPassword();
        clearUSR();
        clearPASS();
        clearUSRID();
        clearPPL();
        clearPPS();
        clearEmail();
    }
}
