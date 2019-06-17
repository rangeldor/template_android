package com.codeepisodes.myapplication.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.ImageView;

public class ImageUtils {

    public static void setImage(ImageView view, String path){
        setImage ( view, path, 512,512 );
    }

    public static void setImage(ImageView view , String path , int width , int heigth) {
        if (path != null && !path.isEmpty () ){
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            if ( bitmap != null ){
                Bitmap scaledBitmap = bitmap.createScaledBitmap ( bitmap, width, heigth, true );
                view.setImageBitmap ( scaledBitmap );
                view.setBackgroundColor ( Color.TRANSPARENT );
                view.setTag ( path );
                view.setScaleType ( ImageView.ScaleType.CENTER_CROP );
            }
        }
    }
}
