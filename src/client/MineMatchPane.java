package client;

import javafx.scene.layout.BorderPane;

public class MineMatchPane extends BorderPane {

    private MatchListePane matchListePane;
    private DataService dataService;

    public MineMatchPane() {
        dataService = DataService.getInstance();
        matchListePane = new MatchListePane();
        setCenter(matchListePane);
    }


    public void printMineMatcher() {
        DataService.getInstance().getLagtTilBruker().forEach(bruker ->
                matchListePane.addValgtMatch(new MatchPane(bruker)));

    }

}



