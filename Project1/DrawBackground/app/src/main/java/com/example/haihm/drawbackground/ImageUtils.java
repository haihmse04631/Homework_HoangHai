package com.example.haihm.drawbackground;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
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
    private static File tempFile;
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

            MediaScannerConnection.scanFile(context, new String[]{imageFile.getAbsolutePath()}, null, null);
            Toast.makeText(context, "saved!", Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Uri getUriFromImage(Context context){
        //create temp file
        tempFile = null;

        try {
            tempFile = File.createTempFile(
                    Calendar.getInstance().getTime().toString(), ".jpg", context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            );

            Log.e("Uri", "GetUriFromImage: " + tempFile.getPath());
            tempFile.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get uri

        Uri uri = null;
        if(tempFile != null){
            uri = FileProvider.getUriForFile(
                    context,
                    context.getPackageName() + ".provider",
                    tempFile
            );
        }
        Log.e("Uri", "Get Uri from Image: " + uri);
        return uri;
    }

    public static Bitmap getBitmap(Context context ){
        Bitmap bitmap = BitmapFactory.decodeFile(tempFile.getPath());
        //scale
        int screenWith = context.getResources().getDisplayMetrics().widthPixels;
        double ratio = (double) bitmap.getWidth() / bitmap.getHeight();

        Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, screenWith, (int) (screenWith/ratio), false);
        return scaleBitmap;
    }

}
