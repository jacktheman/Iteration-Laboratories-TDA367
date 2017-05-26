package com.itlabs.fabnotes.note.model.noteobject;

import com.itlabs.fabnotes.note.model.PaintingContainer;
import org.junit.Test;
import com.itlabs.fabnotes.note.utility.paint.PaintStrokeToData;
import com.itlabs.fabnotes.note.utility.paint.Paintbrush;

import static org.junit.Assert.*;

/**
 * Created by jackflurry on 2017-05-19.
 */
public class PaintingContainerTest {

    private final int TEST_INIT_LAYOUT = 100;
    private final int TEST_NUMBER = 50;
    private final int TEST_BIGGER_NUMBER = 75;
    private PaintingContainer container = new PaintingContainer(TEST_INIT_LAYOUT,TEST_INIT_LAYOUT);

    @Test
    public void duplicateContainer() throws Exception {
        PaintingContainer container2 = (PaintingContainer)container.duplicate();
        assertTrue(container2.getLayoutX() == container.getLayoutX());
        assertTrue(container2.getLayoutY() == container.getLayoutY());
        assertTrue(container2.getFitHeight() == container.getFitHeight());
        assertTrue(container2.getFitWidth() == container.getFitWidth());

        for(int i = 0 ; i < container.getPaintings().size() ; i++){
            assertTrue(container.getPaintings().get(i) == container2.getPaintings().get(i));
        }
    }

    @Test
    public void setLayoutX() throws Exception {
        container.setLayoutX(TEST_NUMBER);
        assertTrue(container.getLayoutX() == TEST_NUMBER);
    }

    @Test
    public void setLayoutY() throws Exception {
        container.setLayoutY(TEST_NUMBER);
        assertTrue(container.getLayoutY() == TEST_NUMBER);
    }

    @Test
    public void testPaintbrush() throws Exception {
        PaintingContainer.setPaintbrush(Paintbrush.CIRCLE);
        assertEquals(PaintingContainer.getPaintbrush(),Paintbrush.CIRCLE);
    }

    @Test
    public void testPaintings() throws Exception {
        PaintStrokeToData stroke = new PaintStrokeToData();
        container.addPainting(stroke);
        assertSame(container.getPaintings().size(),1);
        container.removeLastPainting();
        assertSame(container.getPaintings().size(),0);
    }

    @Test
    public void testPaintingResizer() throws Exception {
        container.setFitHeight(TEST_NUMBER);
        container.setFitWidth(TEST_NUMBER);
        assertTrue(container.getFitHeight() == TEST_NUMBER);
        assertTrue(container.getFitWidth()== TEST_NUMBER);
        container.paintingSizeCounter(TEST_BIGGER_NUMBER,TEST_BIGGER_NUMBER);
        assertTrue(container.getFitHeight()>TEST_NUMBER && container.getFitWidth()>TEST_NUMBER);

    }

}
