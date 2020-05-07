package Server;

import client.Bruker;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//Server venter på bruker forespørsel fra klient og utfører metoder i serveren utifra hvilken handling klient etterspør
public class Server {

    //initialiserer server variablene
    static int port = 8000;
    static ServerSocket serverSocket = startOpp(port);
    static String handling;
    static int personID;
    static Socket socket;
    static ArrayList<Bruker> brukerListe = new ArrayList<>();

    public static void main(String[] args) {
        DataInputStream innTekst = null;

        TaskKjørSøk kjørSøk;
        TaskRegistrerBruker regBruker;
        TaskHentValg visMatch;
        TaskBrukerLogin loginBruker;
        TaskHentInteresserte visInteresserte;

        //Lytter og venter på at noen skal koble seg til å lage ny bruker
        try {
            while (true) {
                socket = serverSocket.accept();
                innTekst = new DataInputStream(socket.getInputStream());
                handling = innTekst.readUTF();

                //Velger det som bruker har bedt om
                switch (handling) {
                    case "REGISTRER":
                        regBruker = new TaskRegistrerBruker(socket);
                        regBruker.run();
                        break;
                    case "SOK":
                        kjørSøk = new TaskKjørSøk(socket);
                        kjørSøk.run();
                        break;
                    case "INTERESSERTIMEG":
                        visInteresserte = new TaskHentInteresserte(socket);
                        visInteresserte.run();
                        break;
                    case "INTERESSERT":
                        visMatch = new TaskHentValg(socket);
                        visMatch.run();
                        break;
                    case "LOGIN":
                        personID = innTekst.readInt();
                        loginBruker = new TaskBrukerLogin(socket, personID);
                        loginBruker.run();
                        break;
                }
            }
        }
        catch (IOException ex){ex.printStackTrace();}
    }
    //Opprett en server
    static private ServerSocket startOpp(int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server er startet opp på " + port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocket;
    }
}