package com.codeepisodes.myapplication.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "user.preferences";
    private final String KEY_EMAIL_LOGIN = "email";
    private final String KEY_THEME = "theme";

    public UserPreferences(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(FILE_NAME, 0);
        editor = preferences.edit();
    }

    public void saveEmailLogin(String emailLogin){
        editor.putString(KEY_EMAIL_LOGIN, emailLogin);
        editor.commit();
    }

    public void saveTheme(String theme){
        editor.putString(KEY_THEME, theme);
        editor.commit();
    }

    public String restoreEmailLogin(){
        return preferences.getString(KEY_EMAIL_LOGIN, "");
    }

    public String restoreTheme(){
        return preferences.getString(KEY_THEME, "");
    }
}
