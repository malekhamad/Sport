package com.geniusmind.sport.Helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class imageHelper {

    // encode image to base 64 . . . ;
    public static String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    // encode image from base64 to bitmap .. .
    public static Bitmap encodeBase64(String base64){
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return  BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }

    // fetch playground images as array . . . ;
    public static String[] getPlaygroundImages(String images){
        String [] imageList = images.split("&");
        return imageList;

    }

}
