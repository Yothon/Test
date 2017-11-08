package tang.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/**
 * Created by 唐
 * on 2017/11/7.
 */

public class DbManager {
    //1112
    private static MySqlHelper mySqlHelper;

    public static MySqlHelper getSqlHelper(Context context) {
        if (null == mySqlHelper) {
            mySqlHelper = new MySqlHelper(context);
        }
        return mySqlHelper;
    }

    public static void execSQL(SQLiteDatabase db, String sql) {
        if (db != null && !TextUtils.isEmpty(sql)) {
            db.execSQL(sql);
        } else {
            throw new RuntimeException("db is null or spl is empty");
        }
    }

}
