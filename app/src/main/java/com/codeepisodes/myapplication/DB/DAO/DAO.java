package com.codeepisodes.myapplication.DB.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codeepisodes.myapplication.DB.Models.ProductTable;

public class DAO extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 3;
    public static String DATABASE_NAME = "vendas";

    public DAO(Context context) {
        super ( context , DATABASE_NAME , null , DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL ( ProductTable.SQL_CREATE_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        switch(oldVersion){
            case 2: // indo para versao 3
                db.execSQL ( ProductTable.SQL_ALTER_TABLE ); // não usar break para os cases, pois assim executará todas as versões
        }
    }
}
