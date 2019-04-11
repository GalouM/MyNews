package com.galou.mynews.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by galou on 2019-04-11
 */
@RunWith(JUnit4.class)
public class MetaSearchUnitTest {
    private MetaSearch metaSearch;

    @Before
    public void setup() throws Exception {
        metaSearch = new MetaSearch();
    }

    @Test
    public void testHistIsCorrect() throws Exception {
        Integer hit = 10;
        metaSearch.setHits(hit);

        assertEquals(metaSearch.getHits(),hit);
    }

    @Test
    public void testOffsetIsCorrect() throws Exception {
        Integer offset = 10;
        metaSearch.setOffset(offset);

        assertEquals(metaSearch.getOffset(), offset);
    }
}
