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
import com.codeepisodes.myapplication.Fragment.Menu1Fragment;
import com.codeepisodes.myapplication.Fragment.ProductFragment;
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

        // Instancia os componentes do android
        initComponents ( );
        // Cria a Toolbar(Barra superior)
        createToolbar();
        // Cria o NavigateDraweer(Menu lateral)
        createNavigateDrawer ( );

        FeedFragment feedFragment = new FeedFragment ( );
        // Cria a transação do fragmento
        createFragmentTransaction ( feedFragment );
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
            FeedFragment feedFragment = new FeedFragment ( );
            createFragmentTransaction ( feedFragment );
        } else if ( id == R.id.nav_product ) {
            ProductFragment productFragment = new ProductFragment ();
            createFragmentTransaction ( productFragment );
        } else if ( id == R.id.nav_menu1 ) {
            Menu1Fragment menu1Fragment = new Menu1Fragment ();
            createFragmentTransaction ( menu1Fragment );
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

    private void initComponents() {

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
