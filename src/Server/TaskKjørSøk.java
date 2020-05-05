package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TaskKjørSøk implements Runnable {
    String kjønn;
    ArrayList<Integer> alder = new ArrayList<>();
    int personID;

   Socket socket;
    DataInputStream innTekst = null;
    DataOutputStream utTekst = null;

    public TaskKjørSøk(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
                System.out.println("Bruker gjør ett søk");

                int minAlder;
                int maxAlder;

                //Lag dataOutput til klient
                innTekst = new DataInputStream(socket.getInputStream());
                utTekst = new DataOutputStream(socket.getOutputStream());

                //Skriv til klienten
                kjønn = innTekst.readUTF();
                personID = innTekst.readInt();
                minAlder = innTekst.readInt();
                maxAlder = innTekst.readInt();

                //Splitt String og legg inn i ArrayList
                System.out.println(minAlder + " " + maxAlder);

                alder.add(minAlder);
                alder.add(maxAlder);

                DatingDB.selectTableWhere(personID,kjønn,alder);


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
