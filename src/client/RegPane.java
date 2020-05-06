package client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Arrays;

public class RegPane extends VBox {
    private Label overskrift;
    private Label labelFulltnavn;
    private TextField textFieldFulltnavn;
    private Label labelKjonn;
    private TextField textfieldKjonn;
    private Label labelAlder;
    private TextField textFieldAlder;
    private Label labelInteresser;
    private TextField textFieldinterresser;
    private Label labelBosted;
    private TextField textFieldbosted;
    private Label labelTlfNr;
    private TextField textFieldTlfNr;
    private Button lagreKnapp;
    private DataService dataService;


    public RegPane() {
        setStyle("-fx-border-color: black");
        setPadding(new Insets(20, 100, 20, 220));

        dataService = DataService.getInstance();
        overskrift = new Label("REGISTRER DEG: ");
        overskrift.setFont(
                Font.font("Verdana",
                        FontWeight.BOLD,
                        26)
        );
        setStyle(
                "-fx-spacing: 10"
        );
        getChildren().add(overskrift);


        labelFulltnavn = new Label("Fullt navn: ");
        getChildren().add(labelFulltnavn);

        textFieldFulltnavn = new TextField("");
        textFieldFulltnavn.setMaxWidth(350.0);
        getChildren().add(textFieldFulltnavn);

        labelKjonn = new Label("Kjønn: ");
        getChildren().add(labelKjonn);

        textfieldKjonn = new TextField("");
        textfieldKjonn.setMaxWidth(350.0);
        getChildren().add(textfieldKjonn);

        labelAlder = new Label("Alder: ");
        getChildren().add(labelAlder);

        textFieldAlder = new TextField("");
        textFieldAlder.setMaxWidth(350.0);
        getChildren().add(textFieldAlder);

        labelInteresser = new Label("Interesser: ");
        getChildren().add(labelInteresser);

        textFieldinterresser = new TextField("");
        textFieldinterresser.setMaxWidth(350.0);
        getChildren().add(textFieldinterresser);

        labelBosted = new Label("Bosted: ");
        getChildren().add(labelBosted);

        textFieldbosted = new TextField("");
        textFieldbosted.setMaxWidth(350.0);
        getChildren().add(textFieldbosted);

        labelTlfNr = new Label("Telefon: ");
        getChildren().add(labelTlfNr);

        textFieldTlfNr = new TextField("");
        textFieldTlfNr.setMaxWidth(350.0);
        getChildren().add(textFieldTlfNr);


        lagreKnapp = new Button("Lagre!");
        lagreKnapp.setStyle("-fx-background-color:#ce93a2;");
        lagreKnapp.setPrefSize(150, 35);
        lagreKnapp.setOnAction(event -> {
            lagre();
        });

        getChildren().add(lagreKnapp);

    }

    private Bruker registrerNyBruker() {
        Bruker nyBruker = new Bruker();
        nyBruker.setFulltNavn(textFieldFulltnavn.getText());
        nyBruker.setKjonn(textfieldKjonn.getText());
        nyBruker.setAlder(Integer.parseInt(textFieldAlder.getText()));
        nyBruker.setInterresser(Arrays.asList(textFieldinterresser.getText().split(",")));
        nyBruker.setBosted(textFieldbosted.getText());
        nyBruker.setTlfNr(textFieldTlfNr.getText());

        return nyBruker;
    }

    public void lagre() {
        dataService.registrerNyBruker(registrerNyBruker());

    }


}


