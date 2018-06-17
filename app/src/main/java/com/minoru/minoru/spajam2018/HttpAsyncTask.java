package com.minoru.minoru.spajam2018;

import android.os.AsyncTask;

public class HttpAsyncTask extends AsyncTask<String,Void,Void>{
    @Override
    protected Void doInBackground(String... params) {
        String ipaddress=params[0];
        int port = Integer.parseInt(params[1]);
        String msg = params[2];

        try {

        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
