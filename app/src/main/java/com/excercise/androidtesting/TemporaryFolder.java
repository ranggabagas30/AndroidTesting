package com.excercise.androidtesting;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

public class TemporaryFolder {

    private final String temporaryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    private String folderPath = null;

    public void newFolder(String folderName) {
        File file = new File(temporaryPath, folderName);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.folderPath = temporaryPath + File.separator + folderName;
    }

    public File newFile(String fileName, String fileExt) {
        if (!TextUtils.isEmpty(folderPath)) {
            File file = new File(folderPath, fileName + "." + fileExt);
            if (!file.exists()) {
                try {
                    return File.createTempFile(fileName, fileExt, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file;
        } else return null;
    }
}
