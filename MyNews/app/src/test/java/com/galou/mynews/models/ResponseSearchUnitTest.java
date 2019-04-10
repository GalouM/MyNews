package com.galou.mynews.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by galou on 2019-04-10
 */
@RunWith(JUnit4.class)
public class ResponseSearchUnitTest {

    private ResponseSearch responseSearch;

    @Before
    public void setup() throws Exception {
        responseSearch = new ResponseSearch();
    }

    @Test
    public void testListArticleIsCorrect() throws Exception {
        ArticleSearch article1 = new ArticleSearch();
        ArticleSearch article2 = new ArticleSearch();
        List<ArticleSearch> list = new ArrayList<>();
        list.add(article1);
        list.add(article2);
        responseSearch.setDocs(list);

        assertEquals(responseSearch.getDocs(),list);
    }
}
