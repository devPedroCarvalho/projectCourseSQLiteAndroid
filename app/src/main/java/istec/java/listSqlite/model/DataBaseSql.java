package istec.java.listSqlite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseSql extends SQLiteOpenHelper {

    private static final String DB_NAME = "Users.Database";
    private static final String DB_TABLE = "Users_table";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String UC = "UC";

    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT " + ")";


    public DataBaseSql(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE + "'");
        onCreate(db);
    }
    public boolean AddData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(NAME, name);
        long result = db.insert(DB_TABLE,null, cValues);
        return result != -1;
    }


    public Cursor ShowDataSql(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ DB_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        return  cursor;
    }
}
