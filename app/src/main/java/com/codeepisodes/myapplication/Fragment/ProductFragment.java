package com.codeepisodes.myapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codeepisodes.myapplication.Activity.MainActivity;
import com.codeepisodes.myapplication.Activity.ProductActivity;
import com.codeepisodes.myapplication.Adapter.ProductAdapter;
import com.codeepisodes.myapplication.DB.Models.ProductTable;
import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private static final String TAG = "ProductFragment";
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private ProductTable productTable;
    private FloatingActionButton fab_addProduct;
    private Intent intent;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {

        View view = inflater.inflate ( R.layout.fragment_product , container , false );
        fab_addProduct = view.findViewById(R.id.fab_addProduct);

        setRecyclerView(view);

        Log.d ( TAG , "onCreateView: " + productTable.readAll () );

        fab_addProduct.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
               // Toast.makeText ( getActivity () , "clicado" , Toast.LENGTH_SHORT ).show ( );
                // Snackbar.make ( view, "Clicado", Snackbar.LENGTH_SHORT ).show ();
                intent = new Intent ( getActivity (), ProductActivity.class );
                startActivity ( intent );
            }

        } );

        return view;
    }

    private void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.product_recyclerview);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );

        productTable = new ProductTable ( getActivity () );
        adapter = new ProductAdapter ( productTable.readAll (), getActivity () );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume ( );

        adapter = new ProductAdapter ( productTable.readAll (), getActivity () );
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
