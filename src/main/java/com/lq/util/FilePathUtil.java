package com.lq.util;

import java.io.File;

public class FilePathUtil {

    private static boolean isEmpty(String s)
    {
        return s == null || s.equals("");
    }

    public static String add(String path, String p)
    {
        if (isEmpty(path)) {
            return p;
        }
        if (isEmpty(p)) {
            return path;
        }
        if (path.endsWith("\\")) {
            return path + p;
        }
        return path + "\\" + p;
    }

    synchronized
    public static void createDir(String path)
    {
        File p = new File(path);
        if (!p.exists() && !p.mkdirs()) {
            throw new RuntimeException("!p.mkdirs()");
        }
    }

}
