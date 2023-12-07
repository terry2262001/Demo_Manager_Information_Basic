package com.example.quang.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quang.model.ThongTin;

import java.util.ArrayList;

public class DBHelper {
    String dbName ="newdb";
    String tblThongTin = "THONGTIN";


    private static final String TABLE_NAME = "THONGTIN";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "hovaten";
    private static final String COLUMN_tendinhdanh = "tendinhdanh";
    private static final String COLUMN_sinhnhat = "sinhnhat";
    private static final String COLUMN_mxh = "mxh";
    private static final String COLUMN_gmail = "gmail";
    private static final String COLUMN_avt = "avt";


    Context mContext;

    public DBHelper(Context mContext) {
        this.mContext = mContext;
    }

    public SQLiteDatabase openDB(){
        return mContext.openOrCreateDatabase(dbName,Context.MODE_PRIVATE,null);
    }
    public void closeDB(SQLiteDatabase db ){db.close();    }
    public  void createThongTin(){
        SQLiteDatabase db = openDB();
        String sql  =
                "create table if not exists " + TABLE_NAME + " (" +
                        COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME+ " TEXT not null, "+
                        COLUMN_tendinhdanh+" TEXT not null, "+
                        COLUMN_sinhnhat+" TEXT not null, "+
                        COLUMN_mxh+" TEXT not null, "+
                        COLUMN_gmail+" TEXT not null, "+
                        COLUMN_avt + " TEXT);";
        db.execSQL(sql);
        closeDB(db);
    }
        public ArrayList<ThongTin> getAllThongTin(){

        ArrayList<ThongTin> mtp = new ArrayList<>();

        SQLiteDatabase db = openDB();
        // tao cau truy vam
        String sql = "SELECT * FROM "+ tblThongTin;
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String dinhDanh = cursor.getString(2);
            String ngaySinh = cursor.getString(3);
            String thongTinThem = cursor.getString(4);
            String mail = cursor.getString(5);
            byte[]  avt = cursor.getBlob(6);
            mtp.add(new ThongTin(id,ten,dinhDanh,ngaySinh,thongTinThem,mail,avt));
        }
        closeDB(db);
        return mtp;
    }

    public long insertThongTin(ThongTin thongTin){
        SQLiteDatabase db = openDB();//1 Mở database
        ContentValues contentValues = new ContentValues();//tạo dữ liệu để add vào database
        contentValues.put(COLUMN_NAME,thongTin.getHoVaTen());
        contentValues.put(COLUMN_tendinhdanh,thongTin.getTenDinhDanh());
        contentValues.put(COLUMN_sinhnhat,thongTin.getSinhNhat());
        contentValues.put(COLUMN_mxh,thongTin.getThongTinThem());
        contentValues.put(COLUMN_gmail,thongTin.getGmail());
        contentValues.put(COLUMN_avt,thongTin.getAvt());

        long tmp  = db.insert(tblThongTin, null,contentValues);
        db.close();//đóng databse
        return tmp;
    }

    public  int deleteThongTin(int id){
        SQLiteDatabase db = openDB();
        String idDelete = String.valueOf(id);
        int tmp = db.delete(tblThongTin,"id=?",new String[]{idDelete});
        closeDB(db);
        return  tmp;

    }

    public long updateThongTin(ThongTin newThongTin){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,newThongTin.getHoVaTen());
        contentValues.put(COLUMN_tendinhdanh,newThongTin.getTenDinhDanh());
        contentValues.put(COLUMN_sinhnhat,newThongTin.getSinhNhat());
        contentValues.put(COLUMN_mxh,newThongTin.getThongTinThem());
        contentValues.put(COLUMN_gmail,newThongTin.getGmail());
        contentValues.put(COLUMN_avt,newThongTin.getAvt());
        int tmp = db.update(tblThongTin,contentValues, "id=?", new String[]{String.valueOf(newThongTin.getId())});
        closeDB(db);
        return  tmp;

    }

}
