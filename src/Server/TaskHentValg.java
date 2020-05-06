package Server;

import client.Bruker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TaskHentValg implements Runnable {
    int SpørrID;
    int VisID;
    ArrayList<Bruker> brukerListe = new ArrayList<>();


    Socket socket;
    DataInputStream innTekst = null;
    DataOutputStream utTekst = null;
    ObjectOutputStream outObject = null;

    public TaskHentValg(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Bruker ber om navn og tlf");

            //Lag dataOutput til klient
            innTekst = new DataInputStream(socket.getInputStream());
            utTekst = new DataOutputStream(socket.getOutputStream());
            outObject = new ObjectOutputStream(socket.getOutputStream());

            SpørrID = innTekst.readInt();
            VisID = innTekst.readInt();

            brukerListe = DatingDB.mineValg(SpørrID);
            DatingDB.oppdaterInteressert(SpørrID, VisID);

            outObject.writeObject(brukerListe);
            System.out.println("TASK");
            System.out.println(brukerListe);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                utTekst.close();
                innTekst.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
