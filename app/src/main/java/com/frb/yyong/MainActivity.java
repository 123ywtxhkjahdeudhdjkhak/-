package com.frb.yyong;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AppCompatActivity";
    private MainReceiver mainReceiver = new MainReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"活动已创建",Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager= getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment1);
        fragmentManager.beginTransaction().replace(R.id.fragment1,fragment!=null?fragment:new MainFragment()).addToBackStack(null).commit();
        reReceiver();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(openFileOutput("w",MODE_APPEND));
            bufferedOutputStream.write("wxfx".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedReader bufferedReader=null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("w")));
            String s = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((s=bufferedReader.readLine())!=null){
                stringBuilder.append(s);
            }
            Toast.makeText(this,stringBuilder,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SharedPreferences.Editor editor =getSharedPreferences("w",MODE_PRIVATE).edit();
        editor.putBoolean("1",true);
        editor.putString("2","wf");
        editor.apply();
        if(getSharedPreferences("w",MODE_PRIVATE).getBoolean("1",false))
            Toast.makeText(this,getSharedPreferences("w",MODE_PRIVATE).
                getString("2",""),Toast.LENGTH_SHORT).show();
    }

    private void reReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mainReceiver,intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG+"frb111", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mainReceiver!=null)  unregisterReceiver(mainReceiver);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.title1){
            Toast.makeText(this,"菜单-添加按钮已点击",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"菜单-删除按钮已点击",Toast.LENGTH_SHORT).show();
            startActivityForResult(SecondActivity.getCurrIntent(this,"wwwwwwwwwwwwwwwwwwwwwwwwww"),1);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&data!=null){
            if(requestCode==1)  Toast.makeText(this,data.getCharSequenceExtra(SecondActivity.RESULT)!=null?data.getCharSequenceExtra(SecondActivity.RESULT):"2",Toast.LENGTH_SHORT).show();
            else Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}