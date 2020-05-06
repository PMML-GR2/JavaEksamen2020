package Server;

import sample.Bruker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    DataOutputStream utTekst = null;
    ArrayList<Bruker> brukerListe;

    public TaskKjørSøk(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
                System.out.println("Bruker gjør ett søk");

                //Lag dataOutput til klient
                innTekst = new DataInputStream(socket.getInputStream());
                utTekst = new DataOutputStream(socket.getOutputStream());

                //Hent fra klienten
                kjønn = innTekst.readUTF();
                personID = innTekst.readInt();
                minAlder = innTekst.readInt();
                maxAlder = innTekst.readInt();
                System.out.println(kjønn + " " +personID + " " + minAlder + " " + maxAlder);
                DatingDB.søkMatch(personID,kjønn,minAlder,maxAlder);


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
