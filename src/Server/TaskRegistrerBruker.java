package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TaskRegistrerBruker implements Runnable{
    String navn;
    char k;
    int alder;
    ArrayList<String> interesser = new ArrayList<>();
    String bosted;
    String tlf;
    private int port;

    public TaskRegistrerBruker(int port){
        this.port = port;
    }

    DataInputStream utTekst = null;

    @Override
    public void run() {
        try {
            //Opprett en server

            ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server er startet opp på " + port);
            while (true) {
                //Lytter og venter på at noen skal koble seg til å lage ny bruker
                Socket socket = serverSocket.accept();

                System.out.println("Bruker koblet seg på");
                String fangOppString;
                //Lag dataOutput til klient
                utTekst = new DataInputStream(socket.getInputStream());

                //Skriv til klienten
                navn = utTekst.readUTF();
                k = utTekst.readChar();
                alder = utTekst.readInt();
                fangOppString = utTekst.readUTF();
                bosted = utTekst.readUTF();
                tlf = utTekst.readUTF();

                //Splitt String og legg inn i ArrayList
                String [] splitTabell = fangOppString.split(",");
                System.out.println(fangOppString);
                for(String s: splitTabell){
                    interesser.add(s);
                }

                DatingDB.insertData(navn,k,alder,interesser,bosted,tlf);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                utTekst.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
