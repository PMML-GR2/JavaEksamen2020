package client;

import javafx.scene.layout.BorderPane;

public class MineMatchPane extends BorderPane {

    private MatchListePane matchListePane;
    private DataService dataService;

    private int listlengde = DataService.getInstance().getBrukerHarLagtTil().size();
    private int harTelt = 0;

    public MineMatchPane() {
        dataService = DataService.getInstance();
        matchListePane = new MatchListePane();
        setCenter(matchListePane);
    }


    public void printMineMatcher() {
        if(harTelt < listlengde) {
            DataService.getInstance().getLagtTilBruker().forEach(bruker ->
                    matchListePane.addValgtMatch(new MatchPane(bruker)));
            harTelt++;
        }
    }

}



