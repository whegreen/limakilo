package com.limakilogram.www.bawang.util.api;

import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by walesadanto on 2/7/15.
 */
public class APICallManager {

    private static APICallManager instance;
    private RestAdapter restAdapter;
    private String endPoint = "http://billme.elasticbeanstalk.com";
//    public static Boolean usingMock = true;

    private String authentification;

    /**
     * Returns singleton class instance
     */
    public static APICallManager getInstance() {
        if (instance == null) {
            synchronized (APICallManager.class) {
                if (instance == null) {
                    instance = new APICallManager();
                }
            }
        }
        return instance;
    }

    public APICallManager() {
//        OkHttpClient client = new OkHttpClient();
//        client.setReadTimeout(30, TimeUnit.SECONDS);
//        client.setConnectTimeout(30, TimeUnit.SECONDS);
//        restAdapter = new RestAdapter.Builder().setEndpoint(endPoint)
//                .setLogLevel(RestAdapter.LogLevel.FULL).setClient(new OkClient(client)).build();

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public String getAuthentification() {
        return authentification;
    }

    public void setAuthentification(String authentification) {
        this.authentification = authentification;
    }


}
