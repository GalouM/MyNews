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
public class MultimediaSearchUnitTest {
    private MutimediaSearch multimedia;

    @Before
    public void setup() throws Exception {
        multimedia = new MutimediaSearch();
    }

    @Test
    public void testURLIsCorrect() throws Exception {
        String url = "http://url";
        multimedia.setUrl(url);

        assertEquals(multimedia.getUrl(),url);
    }
}
