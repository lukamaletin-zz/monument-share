package com.example.lukamaletin.monumentshare.utils;

import android.os.Environment;
import android.util.Log;

import com.example.lukamaletin.monumentshare.MonumentShare;
import com.example.lukamaletin.monumentshare.R;

import org.androidannotations.annotations.EBean;

import java.io.File;
import java.io.IOException;

@EBean
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public File createImageFile() throws IOException {
        final String timestamp = String.valueOf(System.currentTimeMillis());
        final String imageFileName = "JPEG_" + timestamp + "_";
        Log.d("imageFileName", imageFileName);
        final File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        final File appStorageDir = new File(storageDir.getAbsoluteFile(), MonumentShare.getContext().getString(R.string.app_name));
        appStorageDir.mkdir();

        return File.createTempFile(imageFileName, ".jpg", appStorageDir);
    }
}
