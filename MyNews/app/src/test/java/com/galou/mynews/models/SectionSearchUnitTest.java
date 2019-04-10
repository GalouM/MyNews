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
public class SectionSearchUnitTest {
    private SectionSearch section;

    @Before
    public void setup() throws Exception {
        section = new SectionSearch();
    }

    @Test
    public void testResponseIsCorrect() throws Exception {
        ResponseSearch response = new ResponseSearch();
        section.setResponse(response);

        assertEquals(section.getResponse(),response);
    }

}
