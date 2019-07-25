package com.codeepisodes.myapplication.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.codeepisodes.myapplication.BuildConfig;
import com.codeepisodes.myapplication.DB.Models.ProductTable;
import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.Fragment.ProductFragment;
import com.codeepisodes.myapplication.R;
import com.codeepisodes.myapplication.Utils.ImageUtils;

import java.io.File;
import java.util.Objects;

import static android.support.v4.content.FileProvider.getUriForFile;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";
    private static final int CAMERA_REQUEST_CODE = 567;
    private ImageView register_image;
    private TextInputEditText edt_name, edt_price, edt_description;
    private String name, price, description;
    private ProductTable productTable;
    private Product product_insert, product_update;
    private String imagePath;
    private FloatingActionButton fab_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_product );

        product_insert = new Product ();

        edt_name = findViewById ( R.id.edt_name );
        edt_price = findViewById ( R.id.edt_price );
        edt_description = findViewById ( R.id.edt_descricao );
        register_image = findViewById ( R.id.register_image );
        fab_camera = findViewById ( R.id.fab_camera );

        product_update = (Product) getIntent ( ).getSerializableExtra ( ProductFragment.SELECTED_PRODUCT );
        if ( product_update != null ) {
            edt_name.setText ( product_update.getName ( ) );
            edt_price.setText ( String.valueOf ( product_update.getPrice ( ) ) );
            edt_description.setText ( product_update.getDescription ( ) );
            ImageUtils.setImage ( register_image , product_update.getImagePath ( ) );
        }

        // No Java 8 => "view ->" substitui "new View.OnClickListener ( )"
        fab_camera.setOnClickListener ( view -> callCamera ( ) );
    }

    private void callCamera() {
        Intent intent = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );
        imagePath = getExternalFilesDir ( null ) + "/" + System.currentTimeMillis ( ) + ".jpg";

        if ( product_update != null ) {
            product_update.setImagePath ( imagePath );
        }else{
            product_insert.setImagePath ( imagePath );
        }

        File photo = new File ( imagePath );
        intent.putExtra ( MediaStore.EXTRA_OUTPUT , Uri.fromFile ( photo ) );
        startActivityForResult ( intent , CAMERA_REQUEST_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {

        if ( requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK ) {
            if ( product_update != null ) {
                ImageUtils.setImage ( register_image , product_update.getImagePath ( ) );
            } else {
                ImageUtils.setImage ( register_image , product_insert.getImagePath () );
            }
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

            if ( product_update != null ) { // Atualiza

                product_update.setName ( name );
                product_update.setDescription ( description );
                product_update.setPrice ( Integer.parseInt ( price.isEmpty () ? "0" : price ) );

                if ( productTable.update ( product_update ) ) {
                    finish ( );
                    Toast.makeText ( ProductActivity.this , getString( R.string.toast_product_success_update) , Toast.LENGTH_SHORT ).show ( );
                } else {
                    Toast.makeText ( ProductActivity.this , getString( R.string.toast_product_error_update) , Toast.LENGTH_SHORT ).show ( );
                }

            } else { // Cria

                product_insert.setName ( name );
                product_insert.setDescription ( description );
                product_insert.setPrice ( Integer.parseInt ( price.isEmpty () ? "0" : price )  );

                if ( productTable.create ( product_insert ) ) {
                    finish ( );
                    Toast.makeText ( ProductActivity.this , getString( R.string.toast_product_success_insert) , Toast.LENGTH_SHORT ).show ( );
                } else {
                    Toast.makeText ( ProductActivity.this , getString( R.string.toast_product_error_insert) , Toast.LENGTH_SHORT ).show ( );
                }

            }
        }

        return super.onOptionsItemSelected ( item );
    }
}
