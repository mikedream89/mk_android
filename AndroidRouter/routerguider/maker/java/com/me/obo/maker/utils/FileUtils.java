package com.me.obo.maker.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 云山 on 2017/11/28.
 */

public class FileUtils {
    public static List<File> getAllFiles(File workFile) {
        List<File> files = new ArrayList<>();
        File[]sonFiles = workFile.listFiles();

        for (File sonFile: sonFiles) {
            if (sonFile.isDirectory()) {
                files.addAll(getAllFiles(sonFile));
            } else {
                files.add(sonFile);
            }
        }
        return files;
    }

}
