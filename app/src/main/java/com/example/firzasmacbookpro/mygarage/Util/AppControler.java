package com.example.firzasmacbookpro.mygarage.Util;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppControler extends Application {
    private static final String TAG = AppControler.class.getSimpleName();
    private static AppControler instance;
    RequestQueue mRequestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized AppControler getInstance()
    {
        return instance;
    }

    private RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> reg, String tag)
    {
        reg.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(reg);
    }

    public <T> void addToRequestQueue (Request<T> reg)
    {
        reg.setTag(TAG);
        getRequestQueue().add(reg);
    }

    public void cancelAllRequest(Object reg)
    {
        if (mRequestQueue != null)
        {
            mRequestQueue.cancelAll(reg);
        }
    }
}
