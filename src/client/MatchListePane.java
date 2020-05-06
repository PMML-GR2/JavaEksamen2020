package client;

import javafx.scene.layout.VBox;

public class MatchListePane extends VBox {

    public MatchListePane() {
    }

    public void addValgtMatch(MatchPane matchPane) {
        getChildren().add(matchPane);
    }
}
