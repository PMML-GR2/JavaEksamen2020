package client;

import javafx.scene.layout.BorderPane;

public class PersValgtMegPane extends BorderPane {
    private ValgtMegListPane valgtMegListPane;
    private DataService dataService;
    private int listlengde = DataService.getInstance().getBrukerHarLagtTil().size();
    private int harTelt = 0;

    public PersValgtMegPane() {
        dataService = DataService.getInstance();
        valgtMegListPane = new ValgtMegListPane();
        setCenter(valgtMegListPane);
    }

    public void printValgtMeg() {
        if(harTelt < listlengde) {
            DataService.getInstance().getBrukerHarLagtTil().forEach(bruker ->
                    valgtMegListPane.addValgtMeg(new ValgtMegPane(bruker)));
            harTelt++;
        }
    }
}
