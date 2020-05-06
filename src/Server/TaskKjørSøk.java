package Server;

import sample.Bruker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TaskKjørSøk implements Runnable {
    String kjønn;
    int minAlder;
    int maxAlder;
    int personID;

    Socket socket;
    DataInputStream innTekst = null;
    ObjectOutputStream utObject = null;

    ArrayList<Bruker> brukerListe = new ArrayList<>();

    public TaskKjørSøk(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
                System.out.println("Bruker gjør ett søk");

                //Lag dataOutput til klient
                innTekst = new DataInputStream(socket.getInputStream());

                //Hent fra klienten
                kjønn = innTekst.readUTF();
                personID = innTekst.readInt();
                minAlder = innTekst.readInt();
                maxAlder = innTekst.readInt();
                System.out.println(kjønn + " " +personID + " " + minAlder + " " + maxAlder);

                brukerListe.addAll(DatingDB.søkMatch(personID,kjønn,minAlder,maxAlder));

            System.out.println("Ble jeg ferdig? : " + brukerListe);
                utObject = new ObjectOutputStream(socket.getOutputStream());

                utObject.writeObject(brukerListe);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                utObject.close();
                innTekst.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
