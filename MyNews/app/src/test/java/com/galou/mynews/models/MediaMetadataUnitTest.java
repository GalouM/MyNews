package com.galou.mynews.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by galou on 2019-03-27
 */
@RunWith(JUnit4.class)
public class MediaMetadataUnitTest {

    private MediaMetadata mediaMetadata;

    @Before
    public void setup(){
        mediaMetadata = new MediaMetadata();
    }

    @Test
    public void testUrlIsCorrect(){
        String url = "http://url";
        mediaMetadata.setUrl(url);

        assertEquals(mediaMetadata.getUrl(), url);
    }

    @Test
    public void testWidthIsCorrect(){
        long width = 5;
        mediaMetadata.setWidth(width);

        assertEquals(mediaMetadata.getWidth(), width);
    }

    @Test
    public void testHeightIsCorrect(){
        long height = 5;
        mediaMetadata.setHeight(height);

        assertEquals(mediaMetadata.getHeight(), height);
    }
}
