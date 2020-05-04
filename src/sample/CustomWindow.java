package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class CustomWindow extends Pane {
    /**
     * Creates a Scene for a specific root Node.
     *
     * @param root The root node of the scene graph
     * @throws NullPointerException if root is null
     */
    public CustomWindow(Parent root) {
        super(root);
    }
}
