package com.galou.mynews.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.galou.mynews.utils.DateUtil.isEndDateBeforeBeginDate;
import static com.galou.mynews.utils.TextUtil.convertListInStringForAPI;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by galou on 2019-03-30
 */
@RunWith(JUnit4.class)
public class TextUtilUnitTest {
    @Test
    public void detectSpecialCharacters(){
        String string =  "3333$$$$";

        assertTrue(TextUtil.isTextContainsSpecialCharacter(string));
    }

    @Test
    public void doNotDetectSpecialCharacters(){
        String string =  "3333fffff";

        assertFalse(TextUtil.isTextContainsSpecialCharacter(string));
    }

    @Test
    public void splitTextCorrectly(){
        String string = "test test2";
        String convertedTerm = "test%20test2";

        assertEquals(convertedTerm, TextUtil.convertQueryTermForAPI(string));
    }

    @Test
    public void convertListCorrectly(){
        List<String> list = new ArrayList<>();
        list.add("section1");
        list.add("section2");
        String expectedString = "(\"section1\"%20\"section2\")";

        assertEquals(expectedString, convertListInStringForAPI(list));
    }

}
