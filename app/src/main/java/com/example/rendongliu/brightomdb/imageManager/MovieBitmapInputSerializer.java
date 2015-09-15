package com.example.rendongliu.brightomdb.imageManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.icemobile.framework.icehttpconnection.InputSerializer;
import com.icemobile.framework.image.data.ImageInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rendong.liu on 15/09/15.
 */
public class MovieBitmapInputSerializer<T> implements InputSerializer<T> {

    public static final int BUFFER_SIZE = 2 * 1024;
    private final ImageInfo imageInfo;
    @Override
    public T readFrom(InputStream inputStream) throws IOException {
        byte[] data = readInputStream(inputStream);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        int inSampleSize = 1;
        if (imageInfo != null && imageInfo.getPreferredDimensions() != null) {
            inSampleSize = calculateInSampleSize(options, imageInfo.getPreferredDimensions().width, imageInfo.getPreferredDimensions().height);
        }

        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;

        final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return (T)bitmap;
    }

    public MovieBitmapInputSerializer(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    private static byte[] readInputStream(InputStream _in) throws IOException {
        Log.d("BitmapInputSerializer", "readInputStream starts reading...");
        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;

        byte[] data = new byte[BUFFER_SIZE];

        while ((nRead = _in.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;
        Log.d("BitmapInputSerializer", "readInputStream time: " + elapsedTime);
        byte[]result = buffer.toByteArray();
        buffer.close();


        return result;

    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
