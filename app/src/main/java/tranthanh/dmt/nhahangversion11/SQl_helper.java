package tranthanh.dmt.nhahangversion11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Time;

public class SQl_helper extends SQLiteOpenHelper {
    private static final String DBName="mydb12";
    private static final int VERSION = 1;
    private static final String Table_Name = "lichsu";
    private static final String ID = "_id";
    private static  final String NAME ="name";
    private static final String YEAROB = "luotxem";
    private SQLiteDatabase myDB;

    public SQl_helper(@Nullable Context context) {
        super(context, DBName, null, VERSION);
        this.myDB = myDB;
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getYEAROB() {
        return YEAROB;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTable ="CREATE TABLE "+Table_Name+" (" +
                ""+
                ID+" INTEGER , " +
                ""+NAME+" TEXT NOT NULL," +
                ""+YEAROB+" TEXT NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  void openDB(){
        myDB=getWritableDatabase();
    }
    public void closeDB(){
        if(myDB!=null && myDB.isOpen()){
            myDB.close();
        }
    }
    public long Insert(int id,String name,String yearob){
        ContentValues values=new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);
        values.put(YEAROB,yearob);
        return myDB.insert(Table_Name,null,values);
    }
    public long Update(int id,String name,String yearob){
        ContentValues values=new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);
        values.put(YEAROB,yearob);
        String where=ID+ " = " + id;
        return myDB.update(Table_Name,values,where,null);
    }
    public long Delete(int id){
        String Where = ID +" = "+id;
        return myDB.delete(Table_Name,Where,null);
    }

    public Cursor getAllRecord(){

        String query ;
        //do ra list view theo thu tu tang dan nam sinh//
        query = "select name,COUNT(name) as sl from lichsu group by name order by COUNT(name) desc";
        return myDB.rawQuery(query,null);
    }
    public Cursor getAllRecord1(){

        String query = "Select "+ID+" From "+Table_Name;
        return myDB.rawQuery(query,null);
    }

}