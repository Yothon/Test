package tang.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 唐
 * on 2017/11/7.
 */

public class MySqlHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "sqlT.db";

    public MySqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqlHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablePerson = "create table person(personId Integer primary key autoincrement ,name char,age int)";
        db.execSQL(tablePerson);
        String tableClass = "create table class(name char,english int,math int,chinese int)";
        db.execSQL(tableClass);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {

        }
    }
}
