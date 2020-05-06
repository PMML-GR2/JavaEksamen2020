package sample;

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
/*    private ArrayList<String> matcher;*/


    //alle personer som har trykket på deg, med navn, alder, telefon, sted, kjønn, interesse

    //https://cdn4.iconfinder.com/data/icons/momenticons-basic/32x32/favorites.png

    public InteressertPane() {
        testBox1 = new VBox();
        vboxSkjema = new VBox();
        matchprofil = new VBox();
        matchprofilalt = new HBox();

     /*   matcher = new ArrayList();*/


        overskrift = new Label("Matcher: ");
        setPadding(new Insets(20, 5, 20, 100));
        setTop(overskrift);


        imageView2 = new ImageView(new Image("https://cdn4.iconfinder.com/data/icons/momenticons-basic/32x32/favorites2-add.png"));
        imageView2.setFitHeight(25.0);
        imageView2.setFitWidth(25.0);
        profilHjerteknapp = new Button("  ",imageView2);
        profilHjerteknapp.setPadding(new Insets(20,0,0,0));
        profilHjerteknapp.setStyle("-fx-background-color:transparent;");

      /*  matcher.add("");*/
        tekst = new Label(
                "Kvinne, 25" + "\n" +
                        "Fra: Porsgrunn " + "\n" +
                        "Interesser: Dans, Musikk");

        tekst.setPadding(new Insets(10));
        tekst.setPrefWidth(300);
        matchprofil.getChildren().add(tekst);

        matchprofilalt.getChildren().addAll(profilHjerteknapp,matchprofil);

        vboxSkjema.getChildren().add(matchprofilalt);

        setLeft(vboxSkjema);

    }

}
