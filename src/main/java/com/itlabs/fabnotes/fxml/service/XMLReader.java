package com.itlabs.fabnotes.fxml.service;

import javafx.scene.paint.Color;
import com.itlabs.fabnotes.noteobject.model.ImageContainer;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;
import com.itlabs.fabnotes.noteobject.model.PaintingContainer;
import com.itlabs.fabnotes.noteobject.model.TextContainer;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import com.itlabs.fabnotes.save.NoteSave;
import com.itlabs.fabnotes.noteobject.utility.PaintStrokeToData;
import com.itlabs.fabnotes.noteobject.utility.Paintbrush;
import com.itlabs.fabnotes.noteobject.utility.PaintingToData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aron on 2017-05-22.
 */
class XMLReader {
    private static final String NOTE_TAGS = "tags";

    private static final String TEXT_CONTAINER = "textContainer";
    private static final String IMAGE_CONTAINER = "imageContainer";
    private static final String PAINTING_CONTAINER = "paintingContainer";

    private static final String FONT_FAMILY_NAME = "fontFamilyName";
    private static final String FONT_SIZE = "fontSize";
    private static final String IS_BOLD = "isBold";
    private static final String IS_ITALIC = "isItalic";
    private static final String TEXT = "text";

    private static final String URL = "url";

    private static final String PAINTING = "painting";
    private static final String PAINT_STROKE_DATA = "paintStrokeToData";
    private static final String PAINTING_DATA = "paintingToData";
    private static final String PAINT_RGBO = "rgbo";
    private static final String PAINTBRUSH = "paintbrush";
    private static final String PAINTBRUSH_SIZE = "size";

    private static final String LAYOUT_X = "layoutX";
    private static final String LAYOUT_Y = "layoutY";

    private static final String FIT_WIDTH = "fitWidth";
    private static final String FIT_HEIGHT = "fitHeight";

    private static final char SPLITTER = ';';

    static NoteSave readXMLToNote(File file) throws ParserConfigurationException, IOException, SAXException {
        if (file.exists()) {
            String name = file.getName().replace(FileHandler.FILE_TYPE, "");
            
            Document dom;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(file);

            Element doc = dom.getDocumentElement();

            return new NoteSave(name, getNoteTags(doc), getNoteContent(doc));
        }
        return null;
    }

    private static List<String> getNoteTags(Element doc) {
        NodeList nl = doc.getElementsByTagName(NOTE_TAGS);
        if (nl.getLength() == 1) {
            String[] tagArray = nl.item(0).getTextContent().split(String.valueOf(SPLITTER));
            List<String> taglist = new ArrayList<>();
            taglist.addAll(Arrays.asList(tagArray));
            return taglist;
        }
        return null;
    }

    private static List<NoteObjectI> getNoteContent(Element doc) {
        List<NoteObjectI> models = new ArrayList<>();
        NodeList nl = doc.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            switch (node.getNodeName()) {
                case TEXT_CONTAINER:
                    models.add(getTextContainer(node));
                    break;
                case IMAGE_CONTAINER:
                    models.add(getImageContainer(node));
                    break;
                case PAINTING_CONTAINER:
                    models.add(getPaintingContainer(node));
                    break;
            }
        }
        return models;
    }

    private static NoteObjectI getTextContainer(Node node) {
        String fontFamilyName = "";
        int fontSize = 0;
        boolean isBold = false;
        boolean isItalic = false;
        double layoutX = 0;
        double layoutY = 0;
        String text = "";
        
        NodeList nl = node.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node item = nl.item(i);
            String name = item.getNodeName();
            String value = item.getTextContent();
            switch (name) {
                case FONT_FAMILY_NAME:
                    fontFamilyName = value;
                    break;
                case FONT_SIZE:
                    fontSize = Integer.parseInt(value);
                    break;
                case IS_BOLD:
                    isBold = Boolean.parseBoolean(value);
                    break;
                case IS_ITALIC:
                    isItalic = Boolean.parseBoolean(value);
                    break;
                case LAYOUT_X:
                    layoutX = Double.parseDouble(value);
                    break;
                case LAYOUT_Y:
                    layoutY = Double.parseDouble(value);
                    break;
                case TEXT:
                    text = value;
                    break;
            }
        }
        
        if (fontFamilyName.isEmpty() || fontSize == 0)
            return null;
        
        TextContainer.setFontFamilyName(fontFamilyName);
        TextContainer.setFontSize(fontSize);
        TextContainer.setIsBold(isBold);
        TextContainer.setIsItalic(isItalic);
        
        return new TextContainer(text, layoutX, layoutY);
    }

    private static NoteObjectI getImageContainer(Node node) {
        String url = "";
        double fitWidth = 0;
        double fitHeight = 0;
        double layoutX = 0;
        double layoutY = 0;

        NodeList nl = node.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node item = nl.item(i);
            String name = item.getNodeName();
            String value = item.getTextContent();
            switch (name) {
                case URL:
                    url = value;
                    break;
                case FIT_WIDTH:
                    fitWidth = Double.parseDouble(value);
                    break;
                case FIT_HEIGHT:
                    fitHeight = Double.parseDouble(value);
                    break;
                case LAYOUT_X:
                    layoutX = Double.parseDouble(value);
                    break;
                case LAYOUT_Y:
                    layoutY = Double.parseDouble(value);
                    break;
            }
        }
        
        if (url.isEmpty())
            return null;
        
        ImageContainer imageContainer = new ImageContainer(url);
        imageContainer.setFitWidth(fitWidth);
        imageContainer.setFitHeight(fitHeight);
        imageContainer.setLayoutX(layoutX);
        imageContainer.setLayoutY(layoutY);
        
        return imageContainer;
    }

    private static NoteObjectI getPaintingContainer(Node node) {
        List<PaintStrokeToData> paintStrokeToDataList = new ArrayList<>();
        double fitWidth = 0;
        double fitHeight = 0;
        double layoutX = 0;
        double layoutY = 0;

        NodeList nl = node.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node item = nl.item(i);
            String name = item.getNodeName();
            String value = item.getTextContent();
            switch (name) {
                case FIT_WIDTH:
                    fitWidth = Double.parseDouble(value);
                    break;
                case FIT_HEIGHT:
                    fitHeight = Double.parseDouble(value);
                    break;
                case LAYOUT_X:
                    layoutX = Double.parseDouble(value);
                    break;
                case LAYOUT_Y:
                    layoutY = Double.parseDouble(value);
                    break;
                case PAINTING:
                    for (int j = 0; j < item.getChildNodes().getLength(); j++) {
                        PaintStrokeToData paintStrokeToData = new PaintStrokeToData();
                        Node paintStrokeData = item.getChildNodes().item(j);
                        if (paintStrokeData.getNodeName().equals(PAINT_STROKE_DATA)) {
                            for (int k = 0; k < paintStrokeData.getChildNodes().getLength(); k++) {
                                Node paintData = paintStrokeData.getChildNodes().item(k);
                                if (paintData.getNodeName().equals(PAINTING_DATA)) {
                                    Color color = null;
                                    Paintbrush paintbrush = null;
                                    double paintbrushSize = 0;
                                    double paintLayoutX = 0;
                                    double paintLayoutY = 0;
                                    NodeList paintDrawDataNL = paintData.getChildNodes();
                                    for (int l = 0; l < paintDrawDataNL.getLength(); l++) {
                                        Node paintDrawData = paintDrawDataNL.item(l);
                                        String textContent = paintDrawData.getTextContent();
                                        switch (paintDrawData.getNodeName()) {
                                            case PAINT_RGBO:
                                                String[] rgbo = textContent.split(String.valueOf(SPLITTER));
                                                double red = Double.parseDouble(rgbo[0]);
                                                double green = Double.parseDouble(rgbo[1]);
                                                double blue = Double.parseDouble(rgbo[2]);
                                                double opacity = Double.parseDouble(rgbo[3]);
                                                color = Color.color(red, green, blue, opacity);
                                                break;
                                            case PAINTBRUSH:
                                                paintbrush = Paintbrush.parsePaintbrush(textContent);
                                                break;
                                            case PAINTBRUSH_SIZE:
                                                paintbrushSize = Double.parseDouble(textContent);
                                                break;
                                            case LAYOUT_X:
                                                paintLayoutX = Double.parseDouble(textContent);
                                                break;
                                            case LAYOUT_Y:
                                                paintLayoutY = Double.parseDouble(textContent);
                                                break;
                                        }
                                    }
                                    paintStrokeToData.addPaintToStroke(new PaintingToData(paintLayoutX, paintLayoutY, paintbrush, paintbrushSize, color));
                                }
                            }
                        }
                        paintStrokeToDataList.add(paintStrokeToData);
                    }
                    break;
            }
        }

        if (paintStrokeToDataList.isEmpty())
            return null;

        PaintingContainer paintingContainer = new PaintingContainer(layoutX, layoutY, true);
        paintingContainer.setFitWidth(fitWidth);
        paintingContainer.setFitHeight(fitHeight);
        paintingContainer.setPaintings(paintStrokeToDataList);

        return paintingContainer;
    }
}
