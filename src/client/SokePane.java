package client;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class SokePane extends BorderPane {

    private Label sokAldertxt;
    private Label soktilfra;
    private TextField sokAlderFelt1;
    private TextField sokAlderFelt2;
    private Label sokKjonntxt;
    private VBox vboxSkjema;
    private HBox sokfelt1;
    private HBox sokfelt2;
    private HBox sokfelt3;
    private HBox sokfelt4;
    private HBox sokfelt5;
    private RadioButton sokKvinne;
    private RadioButton sokMann;
    private RadioButton sokBeggeKjonn;
    private VBox testBox1;
    private Button sokKnapp;
    private ImageView imageView1;
    private DataService dataService;
    private Sok sok;
    private InteressertListPane interessertListPane;
    private BorderPane mainPane;


    public SokePane(InteressertListPane interessertListPane, BorderPane mainPane) {
        this.mainPane = mainPane;
        this.interessertListPane = interessertListPane;
        dataService = DataService.getInstance();

        setPadding(new Insets(0,0,0,70));
        testBox1 = new VBox();
        vboxSkjema = new VBox();
        sokfelt1 = new HBox();
        sokfelt2 = new HBox();
        sokfelt3 = new HBox();
        sokfelt4 = new HBox();
        sokfelt5 = new HBox();



        sokKjonntxt = new Label("Interessert i: ");
        sokKjonntxt.setPadding(new Insets(25,0,8,0));
        sokKvinne = new RadioButton();
        sokKvinne.setText("Kvinner");
        sokKvinne.setPadding(new Insets(0,10,0,0));

        sokMann = new RadioButton();
        sokMann.setText("Menn");
        sokMann.setPadding(new Insets(0,10,0,10));

        sokBeggeKjonn = new RadioButton();
        sokBeggeKjonn.setText("Begge kjønn");
        sokBeggeKjonn.setPadding(new Insets(0,10,0,10));


        ToggleGroup toggleGroup = new ToggleGroup();
        sokKvinne.setToggleGroup(toggleGroup);
        sokMann.setToggleGroup(toggleGroup);
        sokBeggeKjonn.setToggleGroup(toggleGroup);

        sokAldertxt = new Label("Søk etter ønsket alder:");
        sokAldertxt.setPadding(new Insets(25,0,8,0));
        sokAlderFelt1 = new TextField();
        sokAlderFelt1.setMaxWidth(50.0);
        soktilfra = new Label("_");
        soktilfra.setPadding(new Insets(0,10,0,10));
        sokAlderFelt2 = new TextField();
        sokAlderFelt2.setMaxWidth(50.0);


        //Søkerknappen
        imageView1 = new ImageView(new Image("https://cdn3.iconfinder.com/data/icons/valentine-and-romance/64/07_search_find_love_romance_valentine_wedding-512.png"));
        imageView1.setFitHeight(25.0);
        imageView1.setFitWidth(25.0);
        sokKnapp = new Button("Finn kjærligheten", imageView1);
        sokKnapp.setStyle("-fx-background-color:#ce93a2;");
        sokfelt5.setPadding(new Insets(40,0,8,0));

        sokKnapp.setOnAction(event -> {
            sok = new Sok();
            sok.setBeggeKjonn(sokBeggeKjonn.isSelected());
            sok.setKvinne(sokKvinne.isSelected());
            sok.setMann(sokMann.isSelected());
            sok.setFraAlder(Integer.parseInt(sokAlderFelt1.getText()));
            sok.setTilAlder(Integer.parseInt(sokAlderFelt2.getText()));

            String kjønn = "K";
            if(sok.isMann()){
                kjønn = "M";
            }
            try {
                klientMain.sokKlient("SOK",kjønn,
                        Integer.parseInt(sokAlderFelt1.getText()), Integer.parseInt(sokAlderFelt2.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            interessertListPane.getChildren().clear();
            dataService.getMatchBrukere().stream()
                    //.filter(bruker -> sok.isBeggeKjonn() || bruker.getKjonn().equals(sok.isMann() ? "Mann" : "Kvinne"))
                    //.filter(bruker -> bruker.getAlder() >= sok.getFraAlder() && bruker.getAlder() <= sok.getTilAlder())
                    .forEach(bruker -> {
                        interessertListPane.addInteressert(new InteressertPane(bruker, this.mainPane));
                    });
        });




        sokfelt1.getChildren().addAll(sokKjonntxt);
        sokfelt2.getChildren().addAll(sokKvinne,sokMann,sokBeggeKjonn);
        sokfelt3.getChildren().addAll(sokAldertxt);
        sokfelt4.getChildren().addAll(sokAlderFelt1,soktilfra,sokAlderFelt2);
        sokfelt5.getChildren().addAll(sokKnapp);
        testBox1.getChildren().addAll(sokfelt1,sokfelt2,sokfelt3,sokfelt4,sokfelt5);
        setLeft(testBox1);

    }




}
