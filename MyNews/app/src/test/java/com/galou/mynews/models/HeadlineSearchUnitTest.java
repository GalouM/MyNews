package com.galou.mynews.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by galou on 2019-04-10
 */
@RunWith(JUnit4.class)
public class HeadlineSearchUnitTest {
    private HeadlineSearch headline;

    @Before
    public void setup() throws Exception {
        headline = new HeadlineSearch();
    }

    @Test
    public void testHeadlineTitleIsCorrect() throws Exception {
        String title = "title";
        headline.setPrintHeadline(title);

        assertEquals(headline.getPrintHeadline(),title);
    }
}
