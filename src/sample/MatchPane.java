package sample;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class MatchPane extends VBox {
    private Label tekst;
    private Label overskrift;


    public MatchPane() {

        //InfoBox med Innhold
        overskrift = new Label("Matcher: ");
        getChildren().add(overskrift);
        for (int i = 0; i < 10; i++) {
            tekst = new Label(
                    "Kvinne, 25" + "\n" +
                            "Fra: Porsgrunn " + "\n" +
                            "Interesser: Dans, Musikk");
            tekst.setPadding(new Insets(10));
            tekst.setPrefWidth(300);
            getChildren().add(tekst);
            setStyle(
                    "-fx-border-style: solid inside;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-insets: 20;" +
                            "-fx-border-color: Black;"
            );
        }


    }

}
