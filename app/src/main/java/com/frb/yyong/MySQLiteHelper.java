package com.frb.yyong;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String s="create table Book (" +
            "id integer primary key autoincrement, 名字 text not null )";
    private Context context;
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL(s);
        Toast.makeText(context, "表创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);
        Toast.makeText(context, "数据库更新成功", Toast.LENGTH_SHORT).show();
    }
}
