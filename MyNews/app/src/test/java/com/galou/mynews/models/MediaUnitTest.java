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
public class MediaUnitTest {

    private Media media;

    @Before
    public void setup() {
        media = new Media();
    }

    @Test
    public void testTypeIsCorrect(){
        String type = "photo";
        media.setType(type);

        assertEquals(media.getType(), type);
    }

    @Test
    public void testMetadataListIsCorrect(){
        MediaMetadata mediaMetadata = new MediaMetadata();
        MediaMetadata otherMediaMetadata = new MediaMetadata();
        List<MediaMetadata> mediaMetadataList = new ArrayList<>();
        mediaMetadataList.add(mediaMetadata);
        mediaMetadataList.add(otherMediaMetadata);
        media.setMediaMetadata(mediaMetadataList);

        assertEquals(media.getMediaMetadata(), mediaMetadataList);
    }
}
