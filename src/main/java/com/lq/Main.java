package com.lq;

public class Main {

    private static final String SOURCE = "C:\\Users\\luqingterity\\Desktop\\realestate\\WEB-INF\\classes";

    private static final String TOP_PACKAGE_NAME = "com";

    private static final String DEST = "C:\\Users\\luqingterity\\Desktop\\target";

    private static final String FERN_FLOWER_PATH = "C:\\Users\\luqingterity\\Desktop\\decompile-folder\\fernflower.jar";

    public static void main(String[] args) throws Exception
    {
        new Decompiler(SOURCE, TOP_PACKAGE_NAME, DEST, FERN_FLOWER_PATH).start();
    }

}
