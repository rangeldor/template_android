package com.codeepisodes.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeepisodes.myapplication.Activity.DetailActivity;
import com.codeepisodes.myapplication.Activity.ProductActivity;
import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.Fragment.ProductFragment;
import com.codeepisodes.myapplication.R;
import com.codeepisodes.myapplication.Utils.ImageUtils;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private static final String TAG = "ProductAdapter";
    private Context context;
    private List<Product> productList;
    private Product product;

    public ProductAdapter(List<Product> productList , Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int i) {
        View view = LayoutInflater.from ( viewGroup.getContext ( ) ).inflate ( R.layout.item_adapter_product , viewGroup , false );
        return new MyViewHolder ( view );
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder , int position) {
        product = productList.get ( position );

        holder.txt_title.setText ( product.getName ( ) );
        holder.txt_subtitle.setText ( product.getDescription ( ) );
        holder.txt_price.setText ( String.format ( context.getString ( R.string.format_price ) + " %d" , product.getPrice ( ) ) );

        ImageUtils.setImage ( holder.img_product, product.getImagePath (), 50, 50 );
    }

    @Override
    public int getItemCount() {
        return productList.size ( );
    }

    public void removeItem(int position) {
        productList.remove ( position );
        notifyItemRemoved ( position );
    }

    public void restoreItem(List<Product> productList , int position) {
        productList.add ( position , productList.get ( position ) );
        notifyItemInserted ( position );
    }

    public List<Product> getData() {
        return productList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        AppCompatImageView img_product;
        TextView txt_title, txt_subtitle, txt_price;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            this.img_product = itemView.findViewById ( R.id.img_product );
            this.txt_title = itemView.findViewById ( R.id.txt_title );
            this.txt_subtitle = itemView.findViewById ( R.id.txt_subtitle );
            this.txt_price = itemView.findViewById ( R.id.txt_price );

            itemView.setOnClickListener ( this );
            itemView.setOnLongClickListener ( this );
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition ( );

            product = productList.get ( position );

            ImageView imageView = view.findViewById ( R.id.img_product );

            Intent intent = new Intent ( context , DetailActivity.class );
            intent.putExtra ( ProductFragment.SELECTED_PRODUCT , product );

            // para fazer a animação:
            // * Inserir ->
            //    <item name="android:windowContentTransitions">true</item>
            //    no Thema do Style
            // Inserir -> android:transitionName="profile" no imageview da activity atual e na imageview da proxima activity   //Pode ser qualquer nome que seja coerente
            // Para mais detalhes ir : https://github.com/codepath/android_guides/wiki/Shared-Element-Activity-Transition
            Pair<View, String> pair = Pair.create ( (View) imageView , ViewCompat.getTransitionName ( imageView ) );
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation ( (Activity) context , pair );

            context.startActivity ( intent, optionsCompat.toBundle ( )  );

        }

        @Override
        public boolean onLongClick(View view) {
            int position = getLayoutPosition ( );

            product = productList.get ( position );

            Intent intent = new Intent ( context , ProductActivity.class );
            intent.putExtra ( ProductFragment.SELECTED_PRODUCT , product );
            context.startActivity ( intent );

            return true;
        }
    }
}
