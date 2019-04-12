package com.galou.mynews.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.galou.mynews.utils.NumberUtil.getNumberPages;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by galou on 2019-04-12
 */
@RunWith(JUnit4.class)
public class NumberUtilUnitTest {
    @Test
    public void fifty_sendFive(){
        int numberArticle = 50;
         assertEquals(5, getNumberPages(numberArticle));
    }

    @Test
    public void fiftyFive_sendFivePlusOne() {
        int numberArticle = 55;
        assertEquals(6, getNumberPages(numberArticle));

    }
}
