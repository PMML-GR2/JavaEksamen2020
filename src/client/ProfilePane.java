package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ProfilePane extends BorderPane {

    private Bruker bruker;
    private GridPane personInfo;
    private ImageView profilBilde;
    private Label fulltNavn;
    private Label kjonn;
    private Label alder;
    private Label interesser;
    private Label bosted;
    private Label tlfNr;
    private final double fontStorrelse = 18;
    private final Font fastFont = Font.font(fontStorrelse);


    public ProfilePane(Bruker bruker) {
        this.bruker = bruker;
        personInfo = new GridPane();
        personInfo.setHgap(40.0);
        personInfo.setVgap(12.0);


        profilBilde = new ImageView(new Image("https://goin.events/img/avatar.png"));
        fulltNavn = new Label("Navn: " + bruker.getFulltNavn());
        fulltNavn.setFont(fastFont);
        kjonn = new Label("Kjønn: " + bruker.getKjonn());
        kjonn.setFont(fastFont);
        alder = new Label("Alder: " + bruker.getAlder());
        alder.setFont(fastFont);
        interesser = new Label("Interresser: " + bruker.getInterresser());
        interesser.setFont(fastFont);
        bosted = new Label("Bosted: " + bruker.getBosted());
        bosted.setFont(fastFont);
        tlfNr = new Label("Telefon: " + bruker.getTlfNr());
        tlfNr.setFont(fastFont);


        profilBilde.setFitHeight(250.0);
        profilBilde.setFitWidth(250.0);

        personInfo.add(profilBilde, 2, 3);
        personInfo.add(fulltNavn, 2, 5);
        personInfo.add(kjonn, 2, 6);
        personInfo.add(alder, 2, 7);
        personInfo.add(interesser, 2, 8);
        personInfo.add(bosted, 2, 9);
        personInfo.add(tlfNr, 2, 10);


        setCenter(personInfo);


    }


}

