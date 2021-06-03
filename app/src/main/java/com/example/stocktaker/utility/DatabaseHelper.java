package com.example.stocktaker.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "StockManager .db";
    public static final String TABLE_NAME = "TOTAL_STOCK";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "CATEGORY";
    public static final String COL_5 = "QUANTITY";

    EditText name;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
        public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, CATEGORY TEXT, QUANTITY INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData(String name, String description, String category, String quantity){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,name);
            contentValues.put(COL_3,description);
            contentValues.put(COL_4,category);
            contentValues.put(COL_5,quantity);
            long result = db.insert(TABLE_NAME,null,contentValues);

            if(result == -1)
                return false;
            else
                return true;
        }

        public Cursor getAllData(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from "+ TABLE_NAME,null);
            return res;
        }
        public Cursor getAllDataInstock(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where QUANTITY != 0",null);
            return res;
        }

        public Cursor getAllDataOutOfStock(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where QUANTITY == 0",null);
            return res;
        }

        public Cursor searchAllDataName(String n){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where NAME ='" + n +"'COLLATE NOCASE",null);
            return res;
        }

        public Cursor searchAllDataID(String n){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where ID ='" + n +"'COLLATE NOCASE",null);
            return res;
        }
        public Cursor searchAllDataDescription(String n){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where DESCRIPTION ='" + n +"'COLLATE NOCASE",null);
            return res;
        }

        public Cursor searchAllDataQuantity(String n){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where QUANTITY ='" + n +"'COLLATE NOCASE",null);
            return res;
        }

        public Cursor searchAllDataCategory(String n){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where CATEGORY ='" + n +"'COLLATE NOCASE",null);
            return res;
        }

        public boolean updateDB (String id, String name, String description, String category, String quantity){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,id);
            contentValues.put(COL_2,name);
            contentValues.put(COL_3,description);
            contentValues.put(COL_4,category);
            contentValues.put(COL_5,quantity);
            db.update(TABLE_NAME, contentValues, "ID = ?",new String[] {id} );
            return true;
        }

        public Integer deleteData (String id){
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, "id =  ?", new String[] {id});
        }

        public Cursor dropTable (){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery(" DROP TABLE " + TABLE_NAME ,null);
            return res;
        }

        public Cursor printPDF(){
            SQLiteDatabase db = this.getWritableDatabase();
            String[] columns= {"ID", "Name", "Description", "Category", "Quantity"};
            Cursor res = db.query(TABLE_NAME, columns, null,null,null,null, null );
            return res;
        }

    }
