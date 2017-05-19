
import models.noteobject.PaintingContainer;
import org.junit.Test;
import utilities.PaintStrokeToData;
import utilities.Paintbrush;

import static org.junit.Assert.*;

/**
 * Created by jackflurry on 2017-05-19.
 */
public class PaintingContainerTest {

    @Test
    public void duplicateContainer(){
        PaintingContainer container = new PaintingContainer(100,100);
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
    public void testPaintbrush(){
        PaintingContainer.setPaintbrush(Paintbrush.CIRCLE);
        assertEquals(PaintingContainer.getPaintbrush(),Paintbrush.CIRCLE);
    }

    @Test
    public void testPaintings(){
        PaintStrokeToData stroke = new PaintStrokeToData();
        PaintingContainer container = new PaintingContainer(100,100);
        container.addPainting(stroke);
        assertSame(container.getPaintings().size(),1);
        container.removeLastPainting();
        assertSame(container.getPaintings().size(),0);
    }

    @Test
    public void testPaintingResizer(){
        PaintingContainer container = new PaintingContainer(100,100);
        container.setFitHeight(50);
        container.setFitWidth(50);
        assertTrue(container.getFitHeight() == 50);
        assertTrue(container.getFitWidth()== 50.0);
        container.paintingSizeCounter(100,100);
        assertTrue(container.getFitHeight()>50 && container.getFitWidth()>50);

    }

}
