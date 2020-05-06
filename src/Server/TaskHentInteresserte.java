package Server;

import sample.Bruker;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;



public class TaskHentInteresserte implements Runnable {
    int personID;
    ArrayList<Bruker> brukerListe = new ArrayList<>();

    Socket socket;
    DataInputStream innTekst = null;
    DataOutputStream utTekst = null;
    ObjectOutputStream outObject = null;

    public TaskHentInteresserte(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //Lag dataOutput til klient
            innTekst = new DataInputStream(socket.getInputStream());
            utTekst = new DataOutputStream(socket.getOutputStream());
            outObject = new ObjectOutputStream(socket.getOutputStream());

            personID = innTekst.readInt();

            //Skriver ut alle brukere som er lagret i db som interresant.
            brukerListe = DatingDB.interessertIMeg(personID);

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
