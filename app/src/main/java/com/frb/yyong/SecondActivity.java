package com.frb.yyong;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static String string = "frb";
    public static final String RESULT = "frb";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();
        String s =null;
        if(intent.getIntExtra(string,-1)==-1) s=intent.getStringExtra(string);
        else s=String.valueOf(intent.getIntExtra(string,-1));
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        ((TextView)findViewById(R.id.title)).setText(s);
        ((EditText)findViewById(R.id.title1)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intent intent = new Intent();
                intent.putExtra(RESULT,s);
                setResult(1,intent);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this,"datavase.db",
                null,7);
        SQLiteDatabase sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("名字","frb");
        sqLiteDatabase.insert("Book",null,contentValues);
        contentValues.clear();
        contentValues.put("名字","frb1");
        sqLiteDatabase.insert("Book",null,contentValues);
        contentValues.put("名字","frb2");
        sqLiteDatabase.update("Book",contentValues,"名字 = ? ",new String[]{"frb"});
        sqLiteDatabase.delete("Book","名字 = ?",new String[]{"frb3"});
        Cursor cursor = sqLiteDatabase.query("Book",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                int i;
                if ((i = cursor.getColumnIndex("名字")) != -1)
                    Log.i("frb123123", "onCreate: "+cursor.getString(i));
            }while (cursor.moveToNext());
        }
    }
  public static Intent getCurrIntent(Context context , Object o){
        Intent intent = new Intent(context,SecondActivity.class);
        if(o instanceof Integer)
        intent.putExtra(string,(int)((Integer)o));
        else if (o instanceof String)
            intent.putExtra(string,(String)o);
        return intent;
  }
}
