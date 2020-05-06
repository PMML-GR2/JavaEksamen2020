package client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {


    private Stage primaryStage;
    private Button registrerKnapp;
    private Button finnMatch;
    private Button mineMatcher;
    private Button interesserte;
    private ImageView imageView;
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private Label appNavn;
    private BorderPane mainPane;
    private HBox menu;
    private Bruker bruker;
    private RegPane regPane;
    private FinnMatchPane finnMatchPane;
    private MineMatchPane mineMatchPane;
    private PersValgtMegPane persValgtMegPane;


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        primaryStage = stage;
        imageView = new ImageView(new Image("https://cdn4.iconfinder.com/data/icons/essential-part-3/32/207-User-512.png"));
        imageView.setFitHeight(25.0);
        imageView.setFitWidth(25.0);

        imageView0 = new ImageView(new Image("https://cdn4.iconfinder.com/data/icons/essential-part-3/32/209-User-512.png"));
        imageView0.setFitHeight(25.0);
        imageView0.setFitWidth(25.0);

        imageView1 = new ImageView(new Image("https://cdn3.iconfinder.com/data/icons/valentine-and-romance/64/07_search_find_love_romance_valentine_wedding-512.png"));
        imageView1.setFitHeight(25.0);
        imageView1.setFitWidth(25.0);

        imageView2 = new ImageView(new Image("https://cdn4.iconfinder.com/data/icons/web-essential-4/64/11-web_essential-512.png"));
        imageView2.setFitHeight(25.0);
        imageView2.setFitWidth(25.0);

        imageView3 = new ImageView(new Image("https://cdn3.iconfinder.com/data/icons/streamline-icon-set-free-pack/48/Streamline-60-512.png"));
        imageView3.setFitHeight(25.0);
        imageView3.setFitWidth(25.0);


        mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color:#ededed;");
        menu = new HBox();
        registrerKnapp();
        finnMatchKnapp();
        mineMatcherKnapp();
        intImegKnapp();
        appNavn = new Label("DateMe");
        appNavn.setPadding(new Insets(5, 20, 0, 0));
        appNavn.setFont(
                Font.font("Verdana",
                        22)
        );
        menu.getChildren().addAll(appNavn, registrerKnapp, finnMatch, mineMatcher, interesserte);
        menu.setStyle("-fx-background-color:#ce93a2;");
        menu.setPadding(new Insets(10, 10, 8, 10));


        bruker = new Bruker();
        regPane = new RegPane();
        finnMatchPane = new FinnMatchPane(mainPane);
        mineMatchPane = new MineMatchPane();
        persValgtMegPane = new PersValgtMegPane();

        mainPane.setTop(menu);

        if (DataService.getInstance().brukerErRegistrert()) {
            mainPane.setCenter(finnMatchPane);
        }
        else {
            mainPane.setCenter(regPane);
        }



        primaryStage.setTitle("DateMe");
        primaryStage.setScene(new Scene(mainPane, 800, 800));
        primaryStage.show();

    }

    private void registrerKnapp() {
        registrerKnapp = new Button("Registrering", imageView);
        registrerKnapp.setPadding(new Insets(2, 4, 2, 4));
        registrerKnapp.setStyle("-fx-background-color:transparent;");
        registrerKnapp.setOnAction(event -> {
            primaryStage.setTitle("Registrering");
            mainPane.setCenter(regPane);
        });
    }

    private void finnMatchKnapp() {

        finnMatch = new Button("Finn kjærligheten", imageView1);
        finnMatch.setPadding(new Insets(2, 4, 2, 4));
        finnMatch.setStyle("-fx-background-color:transparent;");
        finnMatch.setOnAction(event -> {
            primaryStage.setTitle("Finn kjærligheten");
            mainPane.setCenter(finnMatchPane);
        });

    }

    private void mineMatcherKnapp() {
        mineMatcher = new Button("Mine utvalgte", imageView2);
        mineMatcher.setPadding(new Insets(2, 4, 2, 4));
        mineMatcher.setStyle("-fx-background-color:transparent;");
        mineMatcher.setOnAction(event -> {
            primaryStage.setTitle("Mine utvalgte");
            mainPane.setCenter(mineMatchPane);
            mineMatchPane.printMineMatcher();
        });
    }

    private void intImegKnapp() {
        interesserte = new Button("Interessert i meg", imageView3);
        interesserte.setPadding(new Insets(2, 4, 2, 4));
        interesserte.setStyle("-fx-background-color:transparent;");
        interesserte.setOnAction(event -> {
            primaryStage.setTitle("interessert i meg");
            mainPane.setCenter(persValgtMegPane);
            persValgtMegPane.printValgtMeg();
        });
    }

}