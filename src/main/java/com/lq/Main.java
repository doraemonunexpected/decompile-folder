package com.lq;

public class Main {

    private static final String SOURCE = "C:\\Users\\lq\\Desktop\\import";

    private static final String TOP_PACKAGE_NAME = "course";

    private static final String DEST = "C:\\Users\\lq\\Desktop\\target1";

    public static void main(String[] args) throws Exception
    {
        new Decompiler(SOURCE, TOP_PACKAGE_NAME, DEST).start();
    }

}
