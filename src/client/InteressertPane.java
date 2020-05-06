package client;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InteressertPane extends BorderPane {
    private Label tekst;
    private Label overskrift;
    private VBox vboxSkjema;
    private VBox testBox1;
    private ImageView imageView2;
    private VBox matchprofil;
    private HBox matchprofilalt;
    private Button profilHjerteknapp;
    private BorderPane mainPane;

    public InteressertPane(Bruker bruker, BorderPane mainPane) {
        this.mainPane = mainPane;
        testBox1 = new VBox();
        vboxSkjema = new VBox();
        matchprofil = new VBox();
        matchprofilalt = new HBox();


        imageView2 = new ImageView(new Image("https://cdn4.iconfinder.com/data/icons/momenticons-basic/32x32/favorites2-add.png"));
        imageView2.setFitHeight(25.0);
        imageView2.setFitWidth(25.0);
        profilHjerteknapp = new Button("  ",imageView2);
        profilHjerteknapp.setPadding(new Insets(20,0,0,0));
        profilHjerteknapp.setStyle("-fx-background-color:transparent;");
        profilHjerteknapp.setOnAction(event -> {
            DataService.getInstance().getLagtTilBruker().add(bruker);
            mainPane.setCenter(new ProfilePane(bruker));
        });


        tekst = new Label(
                bruker.getKjonn() + " " + bruker.getAlder() +  "\n" +
                        "Fra: " + bruker.getBosted() + "\n" +
                        "Interesser: " + String.join(", ", bruker.getInterresser()));

        tekst.setPadding(new Insets(10));
        tekst.setPrefWidth(300);
        matchprofil.getChildren().add(tekst);

        matchprofilalt.getChildren().addAll(profilHjerteknapp,matchprofil);

        vboxSkjema.getChildren().add(matchprofilalt);

        setLeft(vboxSkjema);

    }

}
