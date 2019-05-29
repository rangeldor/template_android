package com.codeepisodes.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.codeepisodes.myapplication.DB.Models.ProductTable;
import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ProductTable productTable;
    private List<Product> productList;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        productTable = new ProductTable ( getApplicationContext () );

        // Lista todos os produtos
        productList = productTable.readAll ();

        Log.d ( TAG , "Lista de Produtos: " + productList.toString () );

        // Lista o produto conforme o id
        productList = productTable.read (productList.get ( 0 ).getId ());

        Log.d ( TAG , "Produto: " + productList.toString () );

        // Atualiza o produto com o objeto passado
        product = new Product ();
        product.setId ( 1 );
        product.setName ( "Armario" );
        product.setPrice ( 2500 );
        if(productTable.update (product)){
            Log.d ( TAG , "Produto atualizado com sucesso " );
        }else{
            Log.d ( TAG , "Erro ao atualizar o produto " + productList.toString () );
        }

        // Lista todos os produtos
        productList = productTable.readAll ();

        Log.d ( TAG , "Lista de Produtos 2: " + productList.toString () );

        // Deleta o produto conforme o id
        if(productTable.delete (2)){
            Log.d ( TAG , "Produto deletado com sucesso " );
        }else{
            Log.d ( TAG , "Erro ao deletar o produto " + productList.toString () );
        }

        // Lista todos os produtos
        productList = productTable.readAll ();

        Log.d ( TAG , "Lista de Produtos 3: " + productList.toString () );



        FloatingActionButton fab = findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( MainActivity.this, ProductActivity.class );
                startActivity ( intent );
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ( ).inflate ( R.menu.menu_main , menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ( );

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings ) {
            return true;
        }

        return super.onOptionsItemSelected ( item );
    }
}
