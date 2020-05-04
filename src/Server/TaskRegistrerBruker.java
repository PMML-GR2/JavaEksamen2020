package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TaskRegistrerBruker implements Runnable{
    //InnVariabler til registrering
    String navn;
    char k;
    int alder;
    ArrayList<String> interesser = new ArrayList<>();
    String bosted;
    String tlf;

    //ServerVariabler
    Socket socket;
    DataInputStream innTekst = null;
    DataOutputStream utTekst = null;

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
                utTekst = new DataOutputStream(socket.getOutputStream());

                //Skriv til klienten
                navn = innTekst.readUTF();
                k = innTekst.readChar();
                alder = innTekst.readInt();
                fangOppString = innTekst.readUTF();
                bosted = innTekst.readUTF();
                tlf = innTekst.readUTF();

                //Splitt String og legg inn i ArrayList
                String [] splitTabell = fangOppString.split(",");
                for(String s: splitTabell){
                    interesser.add(s);
                }

                DatingDB.insertData(navn,k,alder,interesser,bosted,tlf);

                utTekst.writeInt(101);
                System.out.println("En bruker er registrert");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                innTekst.close();
                utTekst.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
