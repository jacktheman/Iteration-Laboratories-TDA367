package services;

import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-19.
 */
public class StateHandlerTest {

    private static final StateHandler stateHandler = StateHandler.getInstance();

    @Test
    public void changeToWriteState() throws Exception {
        stateHandler.changeToWriteState();
        assertTrue(stateHandler.isWriteState());
    }

    @Test
    public void changeToPaintState() throws Exception {
        stateHandler.changeToPaintState();
        assertFalse(stateHandler.isWriteState());
    }

    @AfterClass
    public static void returnToNormal() {
        stateHandler.changeToWriteState();
    }

}