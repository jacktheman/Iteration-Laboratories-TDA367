package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.service.filemanagment.FileHandler;
import java.io.IOException;
import java.util.List;

/**
 * Created by aron on 2017-05-25.
 */
public class TagsListBridge {

    public static List<String> loadTagsFromTagList() throws IOException {
        return FileHandler.loadTags();
    }

    public static void addTagToTagsList(String... tags) throws IOException {
        FileHandler.addTags(tags);
    }

    public static void removeTagsFromTagList(String tag) throws IOException {
        FileHandler.removeTagFromTagList(tag);
    }

}
