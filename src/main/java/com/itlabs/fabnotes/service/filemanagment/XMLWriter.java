package com.itlabs.fabnotes.service.filemanagment;

import com.itlabs.fabnotes.note.model.noteobject.noteobject.ImageContainer;
import com.itlabs.fabnotes.note.model.noteobject.noteobject.NoteObjectI;
import com.itlabs.fabnotes.note.model.noteobject.noteobject.PaintingContainer;
import com.itlabs.fabnotes.note.model.noteobject.noteobject.TextContainer;
import com.itlabs.fabnotes.note.utility.paint.PaintStrokeToData;
import com.itlabs.fabnotes.note.utility.paint.PaintingToData;
import com.itlabs.fabnotes.service.NoteSave;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-05-23.
 */
class XMLWriter extends XMLAbstract {

    private XMLWriter() {}

    static File writeToXML(NoteSave noteSave) throws ParserConfigurationException, TransformerException {
        File file = new File(FileHandler.FILE_PATH + noteSave.getName() + FileHandler.FILE_TYPE);

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(noteSave.getName());
        doc.appendChild(rootElement);

        addTags(rootElement, noteSave.getTags());
        appendElements(rootElement, noteSave.getModels());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);

        transformer.transform(source, result);

        return file;
    }

    private static void addTags(Element rootElement, List<String> tags) {
        Document doc = rootElement.getOwnerDocument();
        Element noteTags = doc.createElement(NOTE_TAGS);
        String tagString = "";
        for (String tag : tags) {
            tagString += tag + SPLITTER;
        }
        if (tags.size() > 0)
            tagString = tagString.substring(0, tagString.length() - 1);
        noteTags.appendChild(doc.createTextNode(tagString));
        rootElement.appendChild(noteTags);
    }

    private static void appendElements(Element rootElement, List<NoteObjectI> noteObjects) {
        for (NoteObjectI noteObject : noteObjects) {
            if (noteObject instanceof TextContainer) {
                appendTextElement(rootElement, (TextContainer) noteObject);
            } else if (noteObject instanceof ImageContainer) {
                appendImageElement(rootElement, (ImageContainer) noteObject);
            } else if (noteObject instanceof PaintingContainer) {
                appendPaintingElement(rootElement, (PaintingContainer) noteObject);
            }
        }
    }

    private static void appendTextElement(Element rootElement, TextContainer textContainer) {
        Document doc = rootElement.getOwnerDocument();
        Element textCon = doc.createElement(TEXT_CONTAINER);

        Element fontFamilyName = doc.createElement(FONT_FAMILY_NAME);
        Element fontSize = doc.createElement(FONT_SIZE);
        Element isBold = doc.createElement(IS_BOLD);
        Element isItalic = doc.createElement(IS_ITALIC);
        Element layoutX = doc.createElement(LAYOUT_X);
        Element layoutY = doc.createElement(LAYOUT_Y);
        Element text = doc.createElement(TEXT);

        fontFamilyName.appendChild(doc.createTextNode(textContainer.getFontFamilyName()));
        fontSize.appendChild(doc.createTextNode(Integer.toString(textContainer.getFontSize())));
        isBold.appendChild(doc.createTextNode(Boolean.toString(textContainer.isBold())));
        isItalic.appendChild(doc.createTextNode(Boolean.toString(textContainer.isItalic())));
        layoutX.appendChild(doc.createTextNode(Double.toString(textContainer.getLayoutX())));
        layoutY.appendChild(doc.createTextNode(Double.toString(textContainer.getLayoutY())));
        text.appendChild(doc.createTextNode(textContainer.getText()));

        appendChildren(textCon, fontFamilyName, fontSize, isBold, isItalic, layoutX, layoutY, text);

        rootElement.appendChild(textCon);
    }

    private static void appendImageElement(Element rootElement, ImageContainer imageContainer) {
        Document doc = rootElement.getOwnerDocument();
        Element imageCon = doc.createElement(IMAGE_CONTAINER);

        Element url = doc.createElement(URL);
        Element fitWidth = doc.createElement(FIT_WIDTH);
        Element fitHeight = doc.createElement(FIT_HEIGHT);
        Element layoutX = doc.createElement(LAYOUT_X);
        Element layoutY = doc.createElement(LAYOUT_Y);

        url.appendChild(doc.createTextNode(imageContainer.getURL()));
        fitWidth.appendChild(doc.createTextNode(Double.toString(imageContainer.getFitWidth())));
        fitHeight.appendChild(doc.createTextNode(Double.toString(imageContainer.getFitHeight())));
        layoutX.appendChild(doc.createTextNode(Double.toString(imageContainer.getLayoutX())));
        layoutY.appendChild(doc.createTextNode(Double.toString(imageContainer.getLayoutY())));

        appendChildren(imageCon, url, fitWidth, fitHeight, layoutX, layoutY);

        rootElement.appendChild(imageCon);
    }

    private static void appendPaintingElement(Element rootElement, PaintingContainer paintingContainer) {
        Document doc = rootElement.getOwnerDocument();
        Element paintingCon = doc.createElement(PAINTING_CONTAINER);

        Element fitWidth = doc.createElement(FIT_WIDTH);
        Element fitHeight = doc.createElement(FIT_HEIGHT);
        Element layoutX = doc.createElement(LAYOUT_X);
        Element layoutY = doc.createElement(LAYOUT_Y);
        Element painting = doc.createElement(PAINTING);

        fitWidth.appendChild(doc.createTextNode(Double.toString(paintingContainer.getFitWidth())));
        fitHeight.appendChild(doc.createTextNode(Double.toString(paintingContainer.getFitHeight())));
        layoutX.appendChild(doc.createTextNode(Double.toString(paintingContainer.getLayoutX())));
        layoutY.appendChild(doc.createTextNode(Double.toString(paintingContainer.getLayoutY())));

        appendChildren(painting, appendPaintStrokes(doc, paintingContainer.getPaintings()));

        appendChildren(paintingCon, fitWidth, fitHeight, layoutX, layoutY, painting);

        rootElement.appendChild(paintingCon);
    }

    private static Element[] appendPaintStrokes(Document doc, List<PaintStrokeToData> paintStrokeToDataList) {
        List<Element> paintStrokes = new ArrayList<>();
        for (PaintStrokeToData paintStrokeData : paintStrokeToDataList) {
            Element paintStroke = doc.createElement(PAINT_STROKE_DATA);
            appendChildren(paintStroke, appendPaint(doc, paintStrokeData.getPaintStroke()));
            paintStrokes.add(paintStroke);
        }
        Element[] elements = new Element[paintStrokes.size()];
        for (int i = 0; i < elements.length; i++)
            elements[i] = paintStrokes.get(i);
        return elements;
    }

    private static Element[] appendPaint(Document doc, List<PaintingToData> paintingToDataList) {
        List<Element> paintings = new ArrayList<>();
        for (PaintingToData paintingData : paintingToDataList) {
            Element paintData = doc.createElement(PAINTING_DATA);

            Element rgbo = doc.createElement(PAINT_RGBO);
            Element paintbrush = doc.createElement(PAINTBRUSH);
            Element paintbrushSize = doc.createElement(PAINTBRUSH_SIZE);
            Element paintLayoutX = doc.createElement(LAYOUT_X);
            Element paintLayoutY = doc.createElement(LAYOUT_Y);

            String redGreenBlueOpacity = "" + paintingData.getColor().getRed() + String.valueOf(SPLITTER) + paintingData.getColor().getGreen()
                    + String.valueOf(SPLITTER) + paintingData.getColor().getBlue() + String.valueOf(SPLITTER) + paintingData.getColor().getOpacity();

            rgbo.appendChild(doc.createTextNode(redGreenBlueOpacity));
            paintbrush.appendChild(doc.createTextNode(paintingData.getPaintbrush().toString()));
            paintbrushSize.appendChild(doc.createTextNode(Double.toString(paintingData.getSize())));
            paintLayoutX.appendChild(doc.createTextNode(Double.toString(paintingData.getX())));
            paintLayoutY.appendChild(doc.createTextNode(Double.toString(paintingData.getY())));

            appendChildren(paintData, rgbo, paintbrush, paintbrushSize, paintLayoutX, paintLayoutY);

            paintings.add(paintData);
        }
        Element[] elements = new Element[paintings.size()];
        for (int i = 0; i < elements.length; i++)
            elements[i] = paintings.get(i);
        return elements;
    }

    private static void appendChildren(Element rootElement, Element... children) {
        for (Element child : children)
            rootElement.appendChild(child);
    }

}
