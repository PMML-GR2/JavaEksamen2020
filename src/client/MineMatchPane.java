package client;

import javafx.scene.layout.BorderPane;

public class MineMatchPane extends BorderPane {

    private MatchListePane matchListePane;
    private DataService dataService;

    private int listlengde = DataService.getInstance().getLagtTilBruker().size();
    private int harTelt = 0;

    public MineMatchPane() {
        dataService = DataService.getInstance();
        matchListePane = new MatchListePane();
        setCenter(matchListePane);
    }


    //skriver ut alle brukere, men funker ikke ordentlig
    public void printMineMatcher() {

            DataService.getInstance().getLagtTilBruker().forEach(bruker ->{

                    matchListePane.addValgtMatch(new MatchPane(bruker));


                    });

        }

    }





