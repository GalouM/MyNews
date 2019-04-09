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
public class ArticleMostPopularUnitTest {

    private ArticleMostPopular article;

    @Before
    public void setup() throws Exception {
        article = new ArticleMostPopular();
    }

    @Test
    public void testTitleIsCorrect() throws Exception {
        String title = "MyArticle";
        article.setTitle(title);

        assertEquals(article.getTitle(),title);
    }

    @Test
    public void testUrlIsCorrect() throws Exception {
        String url = "http://myarticle";
        article.setUrl(url);

        assertEquals(article.getUrl(),url);
    }

    @Test
    public void testSectionIsCorrect() throws Exception {
        String section = "business";
        article.setSection(section);

        assertEquals(article.getSection(),section);
    }

    @Test
    public void testPublishedDateIsCorrect() throws Exception {
        String date = "2019/03/21";
        article.setPublishedDate(date);

        assertEquals(article.getPublishedDate(),date);
    }

    @Test
    public void testListMediaIsCorrect() throws  Exception {
        Media media = new Media();
        Media otherMedia = new Media();
        List<Media> listMedia = new ArrayList<>();
        listMedia.add(media);
        listMedia.add(otherMedia);
        article.setMedia(listMedia);

        assertEquals(article.getMedia(), listMedia);
    }

    @Test
    public void testSubSectionIsCorrect() throws Exception {
        String subsection = "test";

        article.setSubsection(subsection);

        assertEquals(article.getSubsection(), subsection);

    }
}
