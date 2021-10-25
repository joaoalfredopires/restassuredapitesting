package br.com.cwi.restassuredapitest.utils;

public class Utils {

    public static String getSchemaBasePath(String pack, String nameSchema){
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/cwi/restassuredapitest/tests/"
                + pack
                + "/schema/"
                + nameSchema
                +".json";
    }
}
