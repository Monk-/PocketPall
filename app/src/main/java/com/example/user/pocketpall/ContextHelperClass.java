package com.example.user.pocketpall;

import android.app.Application;
import android.content.Context;


public class ContextHelperClass extends Application
{

    private static Context context;

    public void onCreate(){
        super.onCreate();
        ContextHelperClass.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ContextHelperClass.context;
    }
}
