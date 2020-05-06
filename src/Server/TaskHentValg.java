package Server;

import sample.Bruker;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

//Skriver ut alle brukere som innlogget bruker er interessert i
public class TaskHentValg implements Runnable {
    int SpørrID;
    int VisID;
    ArrayList<Bruker> brukerListe = new ArrayList<>();

    Socket socket;
    DataInputStream innTekst = null;
    ObjectOutputStream outObject = null;

    public TaskHentValg(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //Lag dataOutput til klient
            innTekst = new DataInputStream(socket.getInputStream());
            outObject = new ObjectOutputStream(socket.getOutputStream());

            SpørrID = innTekst.readInt();
            VisID = innTekst.readInt();

            //Legger inn interessert forholdet mellom brukere
            DatingDB.oppdaterInteressert(SpørrID, VisID);
            //Skriver ut alle brukere som er lagret i db som interresant.
            brukerListe = DatingDB.mineValg(SpørrID);
            outObject.writeObject(brukerListe);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                innTekst.close();
                outObject.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
