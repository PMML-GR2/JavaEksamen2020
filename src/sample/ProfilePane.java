package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ProfilePane extends Pane {

    private Button tilbakeKnapp;

    public ProfilePane() {


        tilbakeKnapp = new Button("Tilbake");
        getChildren().add(tilbakeKnapp);




    }


}
