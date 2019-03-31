package com.galou.mynews.utils;

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

    public static String[] separateTextBySpace(String text){
        return text.split("\\s+");
    }
}
