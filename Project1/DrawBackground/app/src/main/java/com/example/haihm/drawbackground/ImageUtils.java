package com.example.haihm.drawbackground;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by haihm on 9/27/2017.
 */

public class ImageUtils {

    public static void saveImage(Bitmap bitmap, Context context) throws FileNotFoundException {
        String root = Environment.getExternalStorageDirectory().toString();
        File myFolder = new File(root + "/DrawingNotes");
        myFolder.mkdirs();

        String imageName = Calendar.getInstance().getTimeInMillis() + ".png";
        Log.e("fileLocation", "saveImage: " + imageName);

        File imageFile = new File(myFolder, imageName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            Toast.makeText(context, "saved!", Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
