package sample;

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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {


    private Stage primaryStage;
    private Button registrerKnapp;
    private Button minProfil;
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


    private RegPane regPane;
    private ProfilePane profilePane;
    private InteressertPane interessertPane;
    private FinnMatchPane finnMatchPane;

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
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        menu = new HBox();
        profilKnapp();
        registrerKnapp();
        finnMatchKnapp();
        mineMatcherKnapp();
        intImegKnapp();
        appNavn = new Label("DateMe");
        appNavn.setPadding(new Insets(0,10,0,0));
        appNavn.setFont(
                Font.font("Verdana",
                        22)
        );
        menu.getChildren().addAll(appNavn, registrerKnapp, minProfil, finnMatch, mineMatcher, interesserte);

        mainPane.setTop(menu);


        regPane = new RegPane();
        interessertPane = new InteressertPane();
        finnMatchPane = new FinnMatchPane();


        primaryStage.setTitle("Registrering");
        primaryStage.setScene(new Scene(mainPane, 750, 850));
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

    private void profilKnapp() {
        minProfil = new Button("Min profil", imageView0);
        minProfil.setPadding(new Insets(2, 4, 2, 4));
        minProfil.setStyle("-fx-background-color:transparent;");
        minProfil.setOnAction(event -> {
            primaryStage.setTitle("Min profil");
            mainPane.setCenter(profilePane);
        });
    }


    private void finnMatchKnapp() {

        finnMatch = new Button("Finn match", imageView1);
        finnMatch.setPadding(new Insets(2, 4, 2, 4));
        finnMatch.setStyle("-fx-background-color:transparent;");
        finnMatch.setOnAction(event -> {
            primaryStage.setTitle("Finn match");
            mainPane.setCenter(finnMatchPane);
        });

    }

    private void mineMatcherKnapp() {
        mineMatcher = new Button("Mine matcher", imageView2);
        mineMatcher.setPadding(new Insets(2, 4, 2, 4));
        mineMatcher.setStyle("-fx-background-color:transparent;");
        mineMatcher.setOnAction(event -> {
            primaryStage.setTitle("Matchet med meg");
            mainPane.setCenter(interessertPane);
        });
    }

    private void intImegKnapp() {
        interesserte = new Button("Interessert i meg", imageView3);
        interesserte.setPadding(new Insets(2, 4, 2, 4));
        interesserte.setStyle("-fx-background-color:transparent;");
        interesserte.setOnAction(event -> {
            primaryStage.setTitle("interessert i meg");
            mainPane.setCenter(interessertPane);
        });
    }
}