package com.piisoft.upecfacerecognition.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by a on 1/23/2017.
 */

public class Image {
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    public static  Bitmap bitmapFromJpg(String ImagePath){
        BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
        bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
        return  BitmapFactory.decodeFile(ImagePath ,bitmapFatoryOptions);
    }

    public static void saveBitmapToJpg(Bitmap bitmap, String path, String IamgeName) {

        File folder = new File(path);
        OutputStream fOut = null;
        File file = new File(path, IamgeName); // the File to save to
        try

        {
            if (!folder.exists()) {
                folder.mkdirs();
            }
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            //MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            fOut.flush();
            fOut.close(); // do not forget to close the stream
            bitmap = null;
        } catch (FileNotFoundException exception)
        {
            exception.printStackTrace();

        } catch (IOException e)

        {
            e.printStackTrace();
        }
    }
}
