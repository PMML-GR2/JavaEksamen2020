package client;

import javafx.scene.layout.BorderPane;

public class PersValgtMegPane extends BorderPane {
    private ValgtMegListPane valgtMegListPane;
    private DataService dataService;

    public PersValgtMegPane() {
        dataService = DataService.getInstance();
        valgtMegListPane = new ValgtMegListPane();
        setCenter(valgtMegListPane);
    }

    public void printValgtMeg() {
        DataService.getInstance().getBrukerHarLagtTil().forEach(bruker ->
                valgtMegListPane.addValgtMeg(new ValgtMegPane(bruker)));

    }
}
