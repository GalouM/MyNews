package com.galou.mynews.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by galou on 2019-03-27
 */
@RunWith(JUnit4.class)
public class SectionMostPopularUnitTest {

    private SectionMostPopular section;

    @Before
    public void setup(){
        section = new SectionMostPopular();
    }

    @Test
    public void testNumberResultIsCorrect(){
        long numResult = 18;
        section.setNumResults(numResult);

        assertEquals(section.getNumResults(), numResult);
    }

    @Test
    public void testListArticleIsCorrect(){
        ArticleMostPopular article = new ArticleMostPopular();
        ArticleMostPopular otherArticle = new ArticleMostPopular();
        List<ArticleMostPopular> articleList = new ArrayList<>();
        articleList.add(article);
        articleList.add(otherArticle);
        section.setResults(articleList);

        assertEquals(section.getResults(), articleList);
    }
}
