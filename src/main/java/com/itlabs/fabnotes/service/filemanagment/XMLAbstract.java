package com.itlabs.fabnotes.service.filemanagment;

/**
 * Created by aron on 2017-05-25.
 */
abstract class XMLAbstract {
    static final String NOTE_TAGS = "tags";

    static final String TEXT_CONTAINER = "textContainer";
    static final String IMAGE_CONTAINER = "imageContainer";
    static final String PAINTING_CONTAINER = "paintingContainer";

    static final String FONT_FAMILY_NAME = "fontFamilyName";
    static final String FONT_SIZE = "fontSize";
    static final String IS_BOLD = "isBold";
    static final String IS_ITALIC = "isItalic";
    static final String TEXT = "text";

    static final String URL = "url";

    static final String PAINTING = "painting";
    static final String PAINT_STROKE_DATA = "paintStrokeToData";
    static final String PAINTING_DATA = "paintingToData";
    static final String PAINT_RGBO = "rgbo";
    static final String PAINTBRUSH = "paintbrush";
    static final String PAINTBRUSH_SIZE = "size";

    static final String LAYOUT_X = "layoutX";
    static final String LAYOUT_Y = "layoutY";

    static final String FIT_WIDTH = "fitWidth";
    static final String FIT_HEIGHT = "fitHeight";

    static final char SPLITTER = ';';
}
