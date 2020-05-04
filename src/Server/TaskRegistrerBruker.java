package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TaskRegistrerBruker implements Runnable{
    //InnVariabler til registrering
    String navn;
    String k;
    int alder;
    ArrayList<String> interesser = new ArrayList<>();
    String bosted;
    String tlf;

    //ServerVariabler
    Socket socket;
    DataInputStream innTekst = null;

    public TaskRegistrerBruker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

                //bruker til å splitte interesse tekst
                String fangOppString;

                //Lag dataOutput til klient
                innTekst = new DataInputStream(socket.getInputStream());

                //Skriv til klienten
                navn = utTekst.readUTF();
                k = utTekst.readUTF();
                alder = utTekst.readInt();
                fangOppString = utTekst.readUTF();
                bosted = utTekst.readUTF();
                tlf = utTekst.readUTF();

                //Splitt String og legg inn i ArrayList
                String [] splitTabell = fangOppString.split(",");
                for(String s: splitTabell){
                    interesser.add(s);
                }

                DatingDB.registrerBruker(navn,k,alder,interesser,bosted,tlf);
                System.out.println("En bruker er registrert");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                innTekst.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
