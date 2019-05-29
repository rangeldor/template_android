package com.codeepisodes.myapplication.DB.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.codeepisodes.myapplication.DB.DAO.DAO;
import com.codeepisodes.myapplication.DTO.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductTable extends DAO {

    private static final String TAG = "ProductTable";

    public ProductTable(Context context) {
        super ( context );
    }

    public static String TABLE_NAME = "product";
    public static String COLUMN_NAME_ID = "id";
    public static String COLUMN_NAME_NAME = "name";
    public static String COLUMN_NAME_PRICE = "price";

    public static String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                    COLUMN_NAME_PRICE + " INTEGER DEFAULT 0 );";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public boolean create(Product product) {
        SQLiteDatabase db = this.getWritableDatabase ( );

        ContentValues dados = new ContentValues ( );
        dados.put ( COLUMN_NAME_NAME , product.getName ( ) );
        dados.put ( COLUMN_NAME_PRICE , product.getPrice ( ) );

        long result = db.insert ( TABLE_NAME , null , dados );
        if ( result > 0 ) {
            Log.d ( TAG , "Sucesso ao inserir! " );
            return true;
        } else {
            Log.d ( TAG , "Erro ao inserir! " );
            return false;
        }
    }

    public List<Product> read(Integer id) {

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
        SQLiteDatabase db = getReadableDatabase ( );
        Cursor c = db.rawQuery ( sql , null );

        List<Product> productList = new ArrayList<> ( );
        try {
            while (c.moveToNext ( )) {
                if ( c.getCount ( ) > 0 ) {
                    Product product = new Product ( );

                    if ( c.getString ( c.getColumnIndex ( COLUMN_NAME_ID ) ) != null )
                        product.setId ( Integer.valueOf ( c.getString ( c.getColumnIndex ( COLUMN_NAME_ID ) ) ) );
                    product.setName ( c.getString ( c.getColumnIndex ( COLUMN_NAME_NAME ) ) );
                    if ( c.getString ( c.getColumnIndex ( COLUMN_NAME_PRICE ) ) != null )
                        product.setPrice ( Integer.valueOf ( c.getString ( c.getColumnIndex ( COLUMN_NAME_PRICE ) ) ) );

                    productList.add ( product );
                }
            }
        } finally {
            c.close ( );
        }

        return productList;
    }


    public List<Product> readAll() {

        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase ( );
        Cursor c = db.rawQuery ( sql , null );

        List<Product> productList = new ArrayList<> ( );
        try {
            while (c.moveToNext ( )) {
                if ( c.getCount ( ) > 0 ) {
                    Product product = new Product ( );

                    if ( c.getString ( c.getColumnIndex ( COLUMN_NAME_ID ) ) != null )
                        product.setId ( Integer.valueOf ( c.getString ( c.getColumnIndex ( COLUMN_NAME_ID ) ) ) );
                    product.setName ( c.getString ( c.getColumnIndex ( COLUMN_NAME_NAME ) ) );
                    if ( c.getString ( c.getColumnIndex ( COLUMN_NAME_PRICE ) ) != null )
                        product.setPrice ( Integer.valueOf ( c.getString ( c.getColumnIndex ( COLUMN_NAME_PRICE ) ) ) );

                    productList.add ( product );
                }
            }
        } finally {
            c.close ( );
        }

        return productList;
    }

    public boolean update(Product product) {
        SQLiteDatabase db = this.getWritableDatabase ( );

        ContentValues dados = new ContentValues ( );
        dados.put ( COLUMN_NAME_NAME , product.getName ( ) );
        dados.put ( COLUMN_NAME_PRICE , product.getPrice ( ) );

        long result = db.update ( TABLE_NAME , dados , "id=?" , new String[]{String.valueOf ( product.getId ( ) )} );
        if ( result > 0 ) {
            Log.d ( TAG , "Sucesso ao atualizar! " );
            return true;
        } else {
            Log.d ( TAG , "Erro ao atualizar! " );
            return false;
        }
    }

    public boolean delete(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase ( );

        long result = db.delete ( TABLE_NAME , "id=? " , new String[]{String.valueOf ( id )} );
        if ( result > 0 ) {
            Log.d ( TAG , "Sucesso ao deletar! " );
            return true;
        } else {
            Log.d ( TAG , "Erro ao deletar! " );
            return false;
        }
    }
}
