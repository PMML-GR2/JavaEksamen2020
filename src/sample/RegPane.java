package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegPane extends VBox {
    private Label overskrift;
    private Label labelFornavn;
    private TextField textFieldFornavn;
    private Label labelEtternavn;
    private TextField textFieldEtternavn;
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
    private Label labelAlderPartner;
    private TextField textFieldAlderPartner;
    private Label labelIntPartner;
    private TextField textFieldIntPartner;
    private Label labelPartnerKjonn;
    private RadioButton velgKvinne;
    private RadioButton velgMann;
    private RadioButton velgBeggeKjonn;
    private Button lagreKnapp;


    public RegPane() {
        setStyle("-fx-border-color: black");
        setPadding(new Insets(20, 100, 20, 100));


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

        labelFornavn = new Label("Fornavn: ");
        getChildren().add(labelFornavn);

        textFieldFornavn = new TextField("");
        textFieldFornavn.setMaxWidth(350.0);
        getChildren().add(textFieldFornavn);

        labelEtternavn = new Label("Etternavn: ");
        getChildren().add(labelEtternavn);

        textFieldEtternavn = new TextField("");
        textFieldEtternavn.setMaxWidth(350.0);
        getChildren().add(textFieldEtternavn);

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
/*
        labelAlderPartner = new Label("Ønsket alder på partner: ");
        getChildren().add(labelAlderPartner);

        textFieldAlderPartner = new TextField("");
        textFieldAlderPartner.setMaxWidth(350.0);
        getChildren().add(textFieldAlderPartner);

        labelIntPartner = new Label("Ønskede interesser hos partner: ");
        getChildren().add(labelIntPartner);

        textFieldIntPartner = new TextField("");
        textFieldIntPartner.setMaxWidth(350.0);
        getChildren().add(textFieldIntPartner);

        labelPartnerKjonn = new Label("Interessert i: ");
        getChildren().add(labelPartnerKjonn);

        velgKvinne = new RadioButton();
        velgKvinne.setText("Kvinner");
        getChildren().add(velgKvinne);

        velgMann = new RadioButton();
        velgMann.setText("Menn");
        getChildren().add(velgMann);

        velgBeggeKjonn = new RadioButton();
        velgBeggeKjonn.setText("Begge kjønn");
        getChildren().add(velgBeggeKjonn);

        ToggleGroup toggleGroup = new ToggleGroup();
        velgKvinne.setToggleGroup(toggleGroup);
        velgMann.setToggleGroup(toggleGroup);
        velgBeggeKjonn.setToggleGroup(toggleGroup);
*/
        lagreKnapp = new Button("Lagre!");
        lagreKnapp.setStyle("-fx-background-color:#ce93a2;");
        getChildren().add(lagreKnapp);

    }


}

/*
ArrayList<Bruker> matchBrukere = new ArrayList<>();
        ArrayList<Bruker> lagtTilBruker = new ArrayList<>();
        ArrayList<Bruker> brukerHarLagtTil = new ArrayList<>();
        Bruker bruker; //Din bruker
 */

