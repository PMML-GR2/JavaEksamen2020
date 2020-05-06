package client;

import javafx.scene.layout.VBox;

public class InteressertListPane extends VBox {

    /**
     * Creates a VBox layout with spacing = 0 and alignment at TOP_LEFT.
     */
    public InteressertListPane() {
       // setSpacing(5);
    }

    public void addInteressert(InteressertPane interessertPane) {
        getChildren().add(interessertPane);
    }
}
