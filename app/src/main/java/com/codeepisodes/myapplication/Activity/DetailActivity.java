package com.codeepisodes.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.Fragment.ProductFragment;
import com.codeepisodes.myapplication.R;

public class DetailActivity extends AppCompatActivity {

    private Product product;
    private Toolbar toolbar;
    private TextView txt_title, txt_description, txt_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );
        toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão de voltar
        getSupportActionBar ().setHomeButtonEnabled ( true ); //Ativar o botão de voltar
       // getSupportActionBar().setTitle("Detalhes");   //Titulo para ser exibido na sua Action Bar em frente à seta

        product = (Product) getIntent ().getSerializableExtra ( ProductFragment.SELECTED_PRODUCT );

        txt_title = findViewById ( R.id.txt_title_detail );
        txt_description = findViewById ( R.id.txt_subtitle_detail );
        txt_price = findViewById ( R.id.txt_price_detail );

        txt_title.setText (product.getName ());
        txt_description.setText (product.getDescription ());
        txt_price.setText ( String.format ( "%s %s" , getString ( R.string.format_price ) , String.valueOf ( product.getPrice ( ) ) ) );

        FloatingActionButton fab = findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Snackbar.make ( view , "Produto no carrinho: " + product.getName () , Snackbar.LENGTH_LONG )
                        .setAction ( "Action" , null ).show ( );
            }
        } );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Id do botão de voltar(setinha)
        if ( item.getItemId ( ) == android.R.id.home ) {
            finish ( ); // Finaliza a Activity atual e assim volta para a tela anterior
        }
        return true;
    }
}
