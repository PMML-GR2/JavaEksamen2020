package client;

import javafx.scene.layout.BorderPane;

public class FinnMatchPane extends BorderPane {
    private SokePane sokepane;
    private InteressertListPane interessertListPane;
    private BorderPane mainPain;


    public FinnMatchPane(BorderPane mainPain) {
        this.mainPain = mainPain;

        interessertListPane = new InteressertListPane();
        sokepane = new SokePane(interessertListPane, this.mainPain);
        setLeft(sokepane);



        setRight(interessertListPane);


    }


}
