package com.itlabs.fabnotes.service.state;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI{

    void getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException;

    void getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException;

}
