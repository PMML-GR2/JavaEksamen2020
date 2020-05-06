package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ProfilePane extends BorderPane {

    private GridPane personInfo;
    private ImageView profilBilde;
    private Label fornavn;
    private Label etternavn;
    private Label kjonn;
    private Label alder;
    private Label interesser;
    private Label bosted;
    private Label tlfNr;
    private Button interessertKnapp;
    private final double fontStorrelse = 18;
    private final Font fastFont = Font.font(fontStorrelse);


    public ProfilePane() {
        personInfo = new GridPane();
        personInfo.setHgap(30.0);
        personInfo.setVgap(12.0);

        profilBilde = new ImageView(new Image("https://home.usn.no/lonnesta/Tor_Lonnestad.jpg"));
        fornavn = new Label("Fornavn:");
        fornavn.setFont(fastFont);
        etternavn = new Label("Etternavn:");
        etternavn.setFont(fastFont);
        kjonn = new Label("Kjønn:");
        kjonn.setFont(fastFont);
        alder = new Label("Alder:");
        alder.setFont(fastFont);
        interesser = new Label("Interresser:");
        interesser.setFont(fastFont);
        bosted = new Label("Bosted:");
        bosted.setFont(fastFont);
        tlfNr = new Label("Telefon:");
        tlfNr.setFont(fastFont);
        interessertKnapp = new Button("Interessert!");
        interessertKnapp.setStyle("-fx-background-color:#ce93a2;");
        interessertKnapp.setPrefSize(150, 35);


        profilBilde.setFitHeight(300.0);
        profilBilde.setFitWidth(250.0);

        personInfo.add(profilBilde, 2, 3);
        personInfo.add(fornavn, 2, 5);
        personInfo.add(etternavn, 2, 6);
        personInfo.add(kjonn, 2, 7);
        personInfo.add(alder, 2, 8);
        personInfo.add(interesser, 2, 9);
        personInfo.add(bosted, 2, 10);
        personInfo.add(tlfNr, 2, 11);
        personInfo.add(interessertKnapp, 8, 11);


        setCenter(personInfo);


    }


}

