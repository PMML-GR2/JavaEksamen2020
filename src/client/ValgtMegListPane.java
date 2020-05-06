package client;

import javafx.scene.layout.VBox;

public class ValgtMegListPane extends VBox {

    public ValgtMegListPane() {
    }

    public void addValgtMeg(ValgtMegPane valgtMegPane) {
        getChildren().add(valgtMegPane);
    }
}
