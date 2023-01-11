package com.github.ryuzu.TestWebServer.utilities;

public class StrapiUtilities {
    public static String getStrapiUrl(String database) {
        return getStrapiUrl("http", "1337", database);
    }

    public static String getStrapiUrl(String scheme, String port, String database) {
        return scheme + "://strapi:" + port + "/api/" + database;
    }
}
