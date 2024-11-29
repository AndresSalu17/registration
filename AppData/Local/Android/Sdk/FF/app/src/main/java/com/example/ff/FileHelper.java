package com.example.ff;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {
    public static boolean saveToFile(Context context, String filename, String data) {
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(data.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
