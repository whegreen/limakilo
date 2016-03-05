package id.limakilo.www.bawang.util.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by walesadanto on 27/7/15.
 */
public class PreferencesManager {

    public static final String AUTH_TOKEN = "auth_token";
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String ADDRESS = "address";
    public static final String HANDPHONE = "handphone";
    public static final String EMAIL = "email";
    public static final String CITY = "city";
    public static final String AVATAR_URL = "avatar_url";
    public static final String COVER_URL = "cover_url";
    public static final String LAST_LOGIN_TS = "last_login_ts";

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    // e-cash
    public static final String ECASH_TOKEN = "token";
    public static final String ECASH_MSISDN = "msisdn";
    public static final String ECASH_CREDENTIALS = "credentials";

    private static PreferencesManager instance;

    /** Returns singleton class instance */
    public static PreferencesManager getInstance() {
        if (instance == null) {
            synchronized (PreferencesManager.class) {
                if (instance == null) {
                    instance = new PreferencesManager();
                }
            }
        }
        return instance;
    }

    public static boolean saveAsString(Context context, String key, String value) {
        if (context == null || key == null || key.isEmpty()) {
            return false;
        }
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean removeString(Context context, String key) {
        if (context == null || key == null || key.isEmpty()) {
            return false;
        }

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        if (!prefs.contains(key)) {
            return false;
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        return editor.commit();
    }

    public static String getAsString(Context context, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, null);
    }

    public static void saveAuthToken(Context context, String authToken) {
        PreferencesManager.saveAsString(context, AUTH_TOKEN, authToken);
    }

    public static String getAuthToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(AUTH_TOKEN, null);
    }

}
