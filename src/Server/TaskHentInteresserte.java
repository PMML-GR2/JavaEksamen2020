package Server;

import client.Bruker;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

//skriver ut alle brukere i systemet som har trykket seg innteressert i innlogget bruker
public class TaskHentInteresserte implements Runnable {
    int personID;
    ArrayList<Bruker> brukerListe = new ArrayList<>();

    Socket socket;
    DataInputStream innTekst = null;
    ObjectOutputStream outObject = null;

    public TaskHentInteresserte(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //Lag dataOutput til klient
            innTekst = new DataInputStream(socket.getInputStream());
            outObject = new ObjectOutputStream(socket.getOutputStream());

            personID = innTekst.readInt();

            //Skriver ut alle brukere som er lagret i db som interresant.
            brukerListe = DatingDB.interessertIMeg(personID);
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