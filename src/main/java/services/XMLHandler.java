package services;

import models.note.Note;
import models.noteobject.ImageContainer;
import models.noteobject.NoteObjectI;
import models.noteobject.PaintingContainer;
import models.noteobject.TextContainer;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import save.NoteSave;

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
import java.util.regex.Pattern;

/**
 * Created by aron on 2017-05-22.
 */
public class XMLHandler {

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

    private static final String LAYOUT_X = "layoutX";
    private static final String LAYOUT_Y = "layoutY";

    private static final String FIT_WIDTH = "fitWidth";
    private static final String FIT_HEIGHT = "fitHeight";

    private XMLHandler() {
    }

    public static void writeToXML(NoteSave noteSave) throws ParserConfigurationException, TransformerException {
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
        StreamResult result = new StreamResult(new File(FileHandler.FILE_PATH + noteSave.getName() + FileHandler.FILE_TYPE));

        transformer.transform(source, result);
    }

    private static void addTags(Element rootElement, List<String> tags) {
        Document doc = rootElement.getOwnerDocument();
        Element noteTags = doc.createElement(NOTE_TAGS);
        String tagString = "";
        for (String tag : tags) {
            tagString += tag + ".";
        }
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

        textCon.appendChild(fontFamilyName);
        textCon.appendChild(fontSize);
        textCon.appendChild(isBold);
        textCon.appendChild(isItalic);
        textCon.appendChild(layoutX);
        textCon.appendChild(layoutY);
        textCon.appendChild(text);

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

        imageCon.appendChild(url);
        imageCon.appendChild(fitWidth);
        imageCon.appendChild(fitHeight);
        imageCon.appendChild(layoutX);
        imageCon.appendChild(layoutY);

        rootElement.appendChild(imageCon);
    }

    private static void appendPaintingElement(Element rootElement, PaintingContainer paintingContainer) {

    }

    public static NoteSave readXMLToNote(File file) throws ParserConfigurationException, IOException, SAXException {
        if (file.exists()) {
            String name = file.getName().replace(FileHandler.FILE_TYPE, "");

            Document dom;
            // Make an  instance of the DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(file);

            Element doc = dom.getDocumentElement();

            return new NoteSave(name, getNoteTags(doc), getNoteContent(doc));
        }
        return null;
    }

    private static List<String> getNoteTags(Element doc) {
        NodeList nl = doc.getElementsByTagName(NOTE_TAGS);
        if (nl.getLength() == 1) {
            System.out.println(nl.item(0).getTextContent());
            String[] tagArray = nl.item(0).getTextContent().split(Pattern.quote("."));
            List<String> taglist = new ArrayList<>();
            for (String tag : tagArray)
                taglist.add(tag);
            return taglist;
        }
        return null;
    }

    private static List<NoteObjectI> getNoteContent(Element doc) {

        List<NoteObjectI> models = new ArrayList<>();

        NodeList nl = doc.getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node.getNodeName().equals(TEXT_CONTAINER))
                models.add(getTextContainer(node));
            else if (node.getNodeName().equals(IMAGE_CONTAINER))
                models.add(getImageContainer(node));
            else if (node.getNodeName().equals(PAINTING_CONTAINER))
                models.add(getPaintingContainer(node));
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
            if (name.equals(FONT_FAMILY_NAME))
                fontFamilyName = value;
            else if (name.equals(FONT_SIZE))
                fontSize = Integer.parseInt(value);
            else if (name.equals(IS_BOLD))
                isBold = Boolean.parseBoolean(value);
            else if (name.equals(IS_ITALIC))
                isItalic = Boolean.parseBoolean(value);
            else if (name.equals(LAYOUT_X))
                layoutX = Double.parseDouble(value);
            else if (name.equals(LAYOUT_Y))
                layoutY = Double.parseDouble(value);
            else if (name.equals(TEXT))
                text = value;
        }
        if (!fontFamilyName.isEmpty())
            TextContainer.setFontFamilyName(fontFamilyName);
        if (fontSize > 0)
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
            if (name.equals(URL))
                url = value;
            else if (name.equals(FIT_WIDTH))
                fitWidth = Double.parseDouble(value);
            else if (name.equals(FIT_HEIGHT))
                fitHeight = Double.parseDouble(value);
            else if (name.equals(LAYOUT_X))
                layoutX = Double.parseDouble(value);
            else if (name.equals(LAYOUT_Y))
                layoutY = Double.parseDouble(value);
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
        return null;
    }
}
