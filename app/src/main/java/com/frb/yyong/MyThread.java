package com.frb.yyong;

import android.os.AsyncTask;

public class MyThread extends AsyncTask<Void,Integer,Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
