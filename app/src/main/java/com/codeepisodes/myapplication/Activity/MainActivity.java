package com.codeepisodes.myapplication.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.codeepisodes.myapplication.DB.Models.ProductTable;
import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.Fragment.FeedFragment;
import com.codeepisodes.myapplication.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Declarações ANDROID
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;

    // Declarações JAVA
    private ProductTable productTable;
    private List<Product> productList;
    private Product product;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        castComponents ( );
        createToolbar();
        createNavigateDrawer ( );

        FeedFragment feedFragment = new FeedFragment ( );
        createFragmentTransaction ( feedFragment );


//        productTable = new ProductTable ( getApplicationContext () );
//
//        // Lista todos os produtos
//        productList = productTable.readAll ();
//
//        Log.d ( TAG , "Lista de Produtos: " + productList.toString () );
//
//        // Lista o produto conforme o id
//        productList = productTable.read (productList.get ( 0 ).getId ());
//
//        Log.d ( TAG , "Produto: " + productList.toString () );
//
//        // Atualiza o produto com o objeto passado
//        product = new Product ();
//        product.setId ( 1 );
//        product.setName ( "Armario" );
//        product.setPrice ( 2500 );
//        if(productTable.update (product)){
//            Log.d ( TAG , "Produto atualizado com sucesso " );
//        }else{
//            Log.d ( TAG , "Erro ao atualizar o produto " + productList.toString () );
//        }
//
//        // Lista todos os produtos
//        productList = productTable.readAll ();
//
//        Log.d ( TAG , "Lista de Produtos 2: " + productList.toString () );
//
//        // Deleta o produto conforme o id
//        if(productTable.delete (2)){
//            Log.d ( TAG , "Produto deletado com sucesso " );
//        }else{
//            Log.d ( TAG , "Erro ao deletar o produto " + productList.toString () );
//        }
//
//        // Lista todos os produtos
//        productList = productTable.readAll ();
//
//        Log.d ( TAG , "Lista de Produtos 3: " + productList.toString () );
//
//
//
//        fab.setOnClickListener ( new View.OnClickListener ( ) {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent ( MainActivity.this, ProductActivity.class );
//                startActivity ( intent );
//            }
//        } );


    }

    /**********************************************************************************************/
    /*********************************** @OVERRIDE METHODS  ***************************************/
    /**********************************************************************************************/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById ( R.id.drawer_layout );
        if ( drawer.isDrawerOpen ( GravityCompat.START ) ) {
            drawer.closeDrawer ( GravityCompat.START );
        } else {
            super.onBackPressed ( );
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId ( );

        if ( id == R.id.nav_feed ) {

        } else if ( id == R.id.nav_gallery ) {

        } else if ( id == R.id.nav_slideshow ) {

        } else if ( id == R.id.nav_tools ) {

        } else if ( id == R.id.nav_share ) {

        } else if ( id == R.id.nav_send ) {

        }

        DrawerLayout drawer = findViewById ( R.id.drawer_layout );
        drawer.closeDrawer ( GravityCompat.START );
        return true;
    }

    /**********************************************************************************************/
    /*********************************** PRIVATE METHODS    ***************************************/
    /**********************************************************************************************/

    private void castComponents() {

    }

    private void createToolbar() {
        toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );
    }

    private void createNavigateDrawer() {
        DrawerLayout drawer = findViewById ( R.id.drawer_layout );
        NavigationView navigationView = findViewById ( R.id.nav_view );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle ( this , drawer , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ( );
        navigationView.setNavigationItemSelectedListener ( this );
    }

    private void createFragmentTransaction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ( ).beginTransaction ( );
        fragmentTransaction.replace ( R.id.frameContainer , fragment );
        fragmentTransaction.commit ( );
    }
}
