package tang.test;

import android.content.ContentValues;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NetworkChangedEvent, View.OnClickListener {

    private TextView tvNetwork;
    private NetworkReceiver networkReceiver;
    private MySqlHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = DbManager.getSqlHelper(this);
        tvNetwork = findViewById(R.id.tv_network);
        Button btnCreate = findViewById(R.id.btn_create);
        Button btnInsert = findViewById(R.id.btn_insert);
        btnCreate.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        initNetWorkReceiver();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                SQLiteDatabase writableDatabase = helper.getWritableDatabase();
                writableDatabase.close();
                break;
            case R.id.btn_insert:
                writableDatabase = helper.getWritableDatabase();
                //sql
                String insertSql = "insert into person values('唐鸿程',25)";
                DbManager.execSQL(writableDatabase, insertSql);
                String insertSql2 = "insert into person values('万有鑫',25)";
                DbManager.execSQL(writableDatabase, insertSql2);
                String insertSql3 = "insert into class values('唐鸿程',100,90,80)";
                DbManager.execSQL(writableDatabase, insertSql3);
                String insertSql4 = "insert into class values('万有鑫',80,100,90)";
                DbManager.execSQL(writableDatabase, insertSql4);
                //api
                ContentValues values = new ContentValues();
                values.put("name", "董超然");
                values.put("age", "2x");
                writableDatabase.insert("person", null, values);
                writableDatabase.close();
                break;
            default:
                break;
        }
    }

    private void initNetWorkReceiver() {
        networkReceiver = new NetworkReceiver(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    @Override
    public void networkChange(int state) {
        String alertContent;
        switch (state) {
            case NetUtil.NETWORK_MOBILE:
                alertContent = "mobile";
                break;
            case NetUtil.NETWORK_WIFI:
                alertContent = "wifi";
                break;
            case NetUtil.NETWORK_NONE:
                alertContent = "none";
                break;
            default:
                alertContent = "";
                break;
        }
        tvNetwork.setText(alertContent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }
}
