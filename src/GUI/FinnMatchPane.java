package GUI;

import javafx.scene.layout.BorderPane;

public class FinnMatchPane extends BorderPane {
    private SokePane sokepane;
    private InteressertPane interessertPane;

    public FinnMatchPane(){
        sokepane = new SokePane();
        setRight(sokepane);
        interessertPane = new InteressertPane();
        setLeft(interessertPane);

    }


}
