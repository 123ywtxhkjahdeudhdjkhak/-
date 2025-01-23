package com.frb.yyong;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            try(Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null)){
                if(cursor!=null){
                    if(cursor.moveToFirst()){
                        do{
                            int i =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                            if(i!=-1)
                                Log.i("frbsji", "onRequestPermissionsResult: "+cursor.getString(i));
                        }while (cursor.moveToNext());
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
       findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
               startActivity(intent);
           }
       });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                try(Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null)){
                     if(cursor!=null){
                         if(cursor.moveToFirst()){
                             do{
                                 int i =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                                 if(i!=-1) Log.i("frbsji", "onRequestPermissionsResult: "+cursor.getString(i));
                             }while (cursor.moveToNext());
                         }
                     }else {
                         Toast.makeText(this, "空", Toast.LENGTH_SHORT).show();
                     }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(this, "权限未授予", Toast.LENGTH_SHORT).show();
            }
        }
    }
}