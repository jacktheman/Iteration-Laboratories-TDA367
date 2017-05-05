package views.noteobject;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;

import java.io.Serializable;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class TableView extends Node implements Serializable {
    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds baseBounds, BaseTransform baseTransform) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double v, double v1) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm mxNodeAlgorithm, MXNodeAlgorithmContext mxNodeAlgorithmContext) {
        return null;
    }
}
