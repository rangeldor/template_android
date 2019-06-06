package com.codeepisodes.myapplication.Activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codeepisodes.myapplication.DB.Models.ProductTable;
import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.Fragment.ProductFragment;
import com.codeepisodes.myapplication.R;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";
    private TextInputEditText edt_name, edt_price, edt_description;
    private String name, price, description;
    private ProductTable productTable;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_product );

        edt_name = findViewById ( R.id.edt_name );
        edt_price = findViewById ( R.id.edt_price );
        edt_description = findViewById ( R.id.edt_descricao );

        product = (Product) getIntent ( ).getSerializableExtra ( ProductFragment.SELECTED_PRODUCT );
        if ( product != null ) {
            edt_name.setText ( product.getName ( ) );
            edt_price.setText ( String.valueOf ( product.getPrice ( ) ) );
            edt_description.setText ( product.getDescription ( ) );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ( ).inflate ( R.menu.menu_product , menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ( );

        if ( id == R.id.item_save ) {

            productTable = new ProductTable ( getApplicationContext ( ) );

            name = edt_name.getText ( ).toString ( );
            price = edt_price.getText ( ).toString ( );
            description = edt_description.getText ( ).toString ( );

            if ( product != null ) { // Atualiza

                product.setName ( name );
                product.setDescription ( description );
                product.setPrice ( Integer.parseInt ( price ) );

                if ( productTable.update ( product ) ) {
                    finish ( );
                    Toast.makeText ( ProductActivity.this , "Sucesso ao atualizar o produto " , Toast.LENGTH_SHORT ).show ( );
                } else {
                    Toast.makeText ( ProductActivity.this , "Erro ao atualizar o produto" , Toast.LENGTH_SHORT ).show ( );
                }

            } else { // Cria

                product = new Product ( );
                product.setName ( name );
                product.setDescription ( description );
                product.setPrice ( Integer.parseInt ( price ) );

                if ( productTable.create ( product ) ) {
                    finish ( );
                    Toast.makeText ( ProductActivity.this , "Sucesso ao inserir o produto " , Toast.LENGTH_SHORT ).show ( );
                } else {
                    Toast.makeText ( ProductActivity.this , "Erro ao inserir o produto" , Toast.LENGTH_SHORT ).show ( );
                }

            }
        }

        return super.onOptionsItemSelected ( item );
    }
}
