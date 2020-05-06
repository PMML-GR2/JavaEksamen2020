package client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MatchPane extends BorderPane {
    private Label tekst;
    private Label overskrift;
    private VBox vboxSkjema;
    private VBox testBox1;
    private ImageView imageView;
    private VBox matchprofil;
    private HBox matchprofilalt;
    private Button valgtMatcKnapp;


    public MatchPane(Bruker bruker) {
        testBox1 = new VBox();
        vboxSkjema = new VBox();
        matchprofil = new VBox();
        matchprofilalt = new HBox();
        setPadding(new Insets(1, 5, 1, 100));

        imageView = new ImageView(new Image("https://cdn4.iconfinder.com/data/icons/momenticons-basic/32x32/favorites.png"));
        imageView.setFitHeight(25.0);
        imageView.setFitWidth(25.0);
        valgtMatcKnapp = new Button("  ", imageView);
        valgtMatcKnapp.setPadding(new Insets(20, 0, 0, 0));
        valgtMatcKnapp.setStyle("-fx-background-color:transparent;");
        valgtMatcKnapp.setOnAction(event -> {
            DataService.getInstance().getLagtTilBruker().add(bruker);
        });


        tekst = new Label(
                "Navn: " + bruker.getFulltNavn() + "\n" +
                        "Mobil: " + bruker.getTlfNr() + "\n" +
                        bruker.getKjonn() + ", " + bruker.getAlder() + "\n" +
                        "Fra: " + bruker.getBosted() + "\n" +
                        "Interesser: " + String.join(", ", bruker.getInterresser()));

        tekst.setPadding(new Insets(10));
        tekst.setPrefWidth(300);
        matchprofil.getChildren().add(tekst);

        matchprofilalt.getChildren().addAll(valgtMatcKnapp, matchprofil);

        vboxSkjema.getChildren().add(matchprofilalt);

        setCenter(vboxSkjema);

    }

}

