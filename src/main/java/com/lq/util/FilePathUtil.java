package com.lq.util;

import java.io.File;

public class FilePathUtil {

    public static String add(String path, String p)
    {
        if (path.endsWith("\\")) {
            return path + p;
        }
        return path + "\\" + p;
    }

    public static void createDir(String path)
    {
        File p = new File(path);
        if (p.exists()) {
            return;
        }
        p.mkdirs();
    }

}
