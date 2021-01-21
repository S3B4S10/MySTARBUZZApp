package co.edu.unipiloto.edu.mystarbuzzapp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Usuarios.db";
    public static final String TABLE_NAME = "Register_user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FULLNAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "PASSWORD";
    public static final String COL_6 = "GENDER";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FULLNAME TEXT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, GENDER INTEGER) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
        }

     public void  initData(){
        SQLiteDatabase database = this.getWritableDatabase();
        onUpgrade(database,1,1);
    }

    public boolean insertData(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, user.getFullName());
        contentValues.put(COL_3, user.getUserName());
        contentValues.put(COL_4, user.getEmail());
        contentValues.put(COL_5, user.getPassword());
        contentValues.put(COL_6, user.getGender());

        long result = database.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

    public Cursor getData(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME +" where id="+
                id+"", null);
        return cursor;
    }

    public Cursor findDataByEmail(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME+ " where "+
                COL_4 + "= '"+email+"'", null);
        return cursor;
    }

}
