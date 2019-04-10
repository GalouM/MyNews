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
public class ArticleSearchUnitTest {
    private ArticleSearch article;

    @Before
    public void setup() throws Exception {
        article = new ArticleSearch();
    }

    @Test
    public void testHeadlineIsCorrect() throws Exception {
        HeadlineSearch headline = new HeadlineSearch();
        article.setHeadline(headline);

        assertEquals(article.getHeadline(),headline);
    }

    @Test
    public void testURLIsCorrect() throws Exception {
        String url = "http://test";
        article.setWebUrl(url);

        assertEquals(article.getWebUrl(),url);
    }

    @Test
    public void testMultimediaIsCorrect() throws Exception {
        MutimediaSearch multimedia = new MutimediaSearch();
        MutimediaSearch multimedia2 = new MutimediaSearch();
        List<MutimediaSearch> listMutimedia = new ArrayList<>();
        listMutimedia.add(multimedia);
        listMutimedia.add(multimedia2);
        article.setMultimedia(listMutimedia);

        assertEquals(article.getMultimedia(), listMutimedia);
    }

    @Test
    public void testSectionIsCorrect() throws Exception {
        String section = "test";
        article.setSectionName(section);

        assertEquals(article.getSectionName(),section);
    }

    @Test
    public void testSubSectionIsCorrect() throws Exception {
        String subsection = "test";
        article.setSubsectoinName(subsection);

        assertEquals(article.getSubsectoinName(),subsection);
    }

    @Test
    public void testPubDateIsCorrect() throws Exception {
        String pubDate = "10/10/10";
        article.setPubDate(pubDate);

        assertEquals(article.getPubDate(),pubDate);
    }

}
