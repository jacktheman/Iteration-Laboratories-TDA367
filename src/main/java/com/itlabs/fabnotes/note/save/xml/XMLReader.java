package com.itlabs.fabnotes.note.save.xml;

import com.itlabs.fabnotes.note.save.NoteSave;
import javafx.scene.paint.Color;
import com.itlabs.fabnotes.note.model.ImageContainer;
import com.itlabs.fabnotes.note.model.NoteObjectI;
import com.itlabs.fabnotes.note.model.PaintingContainer;
import com.itlabs.fabnotes.note.model.TextContainer;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import com.itlabs.fabnotes.note.utility.paint.PaintStrokeToData;
import com.itlabs.fabnotes.note.utility.paint.Paintbrush;
import com.itlabs.fabnotes.note.utility.paint.PaintingToData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aron on 2017-05-22.
 */
public class XMLReader extends XMLAbstract {

    private XMLReader() {}

    public static NoteSave readXMLToNote(File file) throws ParserConfigurationException, IOException, SAXException {
        if (file.exists()) {
            String name = file.getName().replace(getFileType(file.getName()), "");

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
                    TextContainer.setFontFamilyName(value);
                    break;
                case FONT_SIZE:
                    TextContainer.setFontSize(Integer.parseInt(value));
                    break;
                case IS_BOLD:
                    TextContainer.setIsBold(Boolean.parseBoolean(value));
                    break;
                case IS_ITALIC:
                    TextContainer.setIsItalic(Boolean.parseBoolean(value));
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

        return createImageContainer(url, fitWidth, fitHeight, layoutX, layoutY);
    }

    private static NoteObjectI getPaintingContainer(Node node) {
        List<PaintStrokeToData> paintStrokeToDataList = null;
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
                    paintStrokeToDataList = getPaintStrokeToDataList(item);
                    break;
            }
        }

        if (paintStrokeToDataList == null)
            return null;

        return createPaintingContainer(paintStrokeToDataList, fitWidth, fitHeight, layoutX, layoutY);
    }

    private static List<PaintStrokeToData> getPaintStrokeToDataList(Node item) {
        List<PaintStrokeToData> paintStrokeToDataList = new ArrayList<>();
        for (int j = 0; j < item.getChildNodes().getLength(); j++) {
            Node paintStrokeData = item.getChildNodes().item(j);
            paintStrokeToDataList.add(paintStrokeToDataCreator(paintStrokeData));
        }
        return paintStrokeToDataList;
    }

    private static PaintStrokeToData paintStrokeToDataCreator(Node paintStrokeData) {
        PaintStrokeToData paintStrokeToData = new PaintStrokeToData();
        if (paintStrokeData.getNodeName().equals(PAINT_STROKE_DATA)) {
            for (int k = 0; k < paintStrokeData.getChildNodes().getLength(); k++) {
                Node paintData = paintStrokeData.getChildNodes().item(k);
                if (paintData.getNodeName().equals(PAINTING_DATA)) {
                    paintStrokeToData.addPaintToStroke(paintingToDataCreator(paintData.getChildNodes()));
                }
            }
        }
        return paintStrokeToData;
    }

    private static PaintingToData paintingToDataCreator(NodeList paintDrawDataNL) {
        Color color = null;
        Paintbrush paintbrush = null;
        double paintbrushSize = 0;
        double paintLayoutX = 0;
        double paintLayoutY = 0;
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
        return new PaintingToData(paintLayoutX, paintLayoutY, paintbrush, paintbrushSize, color);
    }

    private static ImageContainer createImageContainer(String URL, double fitWidth, double fitHeight, double layoutX, double layoutY) {
        ImageContainer imageContainer = new ImageContainer(URL);
        imageContainer.setFitWidth(fitWidth);
        imageContainer.setFitHeight(fitHeight);
        imageContainer.setLayoutX(layoutX);
        imageContainer.setLayoutY(layoutY);
        return imageContainer;
    }

    private static PaintingContainer createPaintingContainer(List<PaintStrokeToData> paintStrokeToDataList,
                                                             double fitWidth, double fitHeight, double layoutX, double layoutY) {
        PaintingContainer paintingContainer = new PaintingContainer(layoutX, layoutY, true);
        paintingContainer.setFitWidth(fitWidth);
        paintingContainer.setFitHeight(fitHeight);
        paintingContainer.setPaintings(paintStrokeToDataList);
        return paintingContainer;
    }

}
