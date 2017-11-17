package com.example.anik.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anik on 29-Sep-17.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx) {
        this.ctx=ctx;
        prefs=ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=prefs.edit();

    }

    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedInMode",loggedIn);
        editor.commit();
    }
    public boolean loggedIn(){
        return prefs.getBoolean("loggedInMode",false);
    }
}
