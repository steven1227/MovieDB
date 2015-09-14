package com.example.rendongliu.brightomdb.imageManager;

import com.icemobile.framework.image.data.handler.BitmapInputSerializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by carlos on 30/07/15.
 */
public class SyncBitmapInputSerializer<T> extends BitmapInputSerializer<T> {

    /* To prevent OutOfMemoryExceptions when decoding multiple bitmaps at the same time, first the
     * inputstream is downloaded from the network and copied into memory and then de decoding is done
     * in a synchronized method, so only 1 bitmap decoded at a time.
     *  */
    public T readFrom(InputStream inputStream) throws IOException {
        InputStream memCopyStream = new ByteArrayInputStream(readInputStream(inputStream));
        return syncReadFrom(memCopyStream);
    }


    private synchronized T syncReadFrom(InputStream inputStream) throws IOException{
        return super.readFrom(inputStream);
    }

    private static byte[] readInputStream(InputStream _in) throws IOException {

        // Read dynamic
        ArrayList<Byte> bContent = new ArrayList<Byte>();
        int b = _in.read();
        while (b != -1) {
            bContent.add((byte) b);
            b = _in.read();
        }

        // Transform to byte[]
        byte[] sContent = new byte[bContent.size()];
        for (int i = 0; i < bContent.size(); i++) {
            sContent[i] = bContent.get(i);
        }

        return sContent;
    }

}