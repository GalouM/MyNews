package com.galou.mynews.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by galou on 2019-03-30
 */
public abstract class TextUtil {

    public static boolean isTextContainsSpecialCharacter(String text){
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();

    }

    public static String convertQueryTermForAPI(String text){
        return text.replaceAll("\\s+", "%20");
    }

    public static String convertListInStringForAPI(List<String> list){
        return list.toString()
                .replaceAll(", ", "\"%20\"")
                .replace("[", "(\"")
                .replace("]", "\")");

    }
}
