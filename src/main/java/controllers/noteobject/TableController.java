package controllers.noteobject;

import javafx.scene.control.MenuItem;
import views.noteobject.TableContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class TableController extends NoteObjectController<TableContainerView> {

    public TableController(double x, double y, double rows, double columns) {
        super(new TableContainerView(rows, columns));
        super.getNode().setLayoutX(x);
        super.getNode().setLayoutY(y);
    }


    @Override
    List<MenuItem> initContextMenuItems() {
        List<MenuItem> menuItemList = new ArrayList<>();
        return menuItemList;
    }
}
