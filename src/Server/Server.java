package Server;
import sample.Bruker;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
                System.out.println("kjører");
                innTekst = new DataInputStream(socket.getInputStream());
                handling = innTekst.readUTF();
                System.out.println(handling);

                switch (handling) {
                    case "REGISTRER":
                        System.out.println("Dette er REGISTER");
                        regBruker = new TaskRegistrerBruker(socket);
                        regBruker.run();
                        break;
                    case "SOK":
                        kjørSøk = new TaskKjørSøk(socket);
                        kjørSøk.run();
                        break;
                    case "INTERESSERTIMEG":
                        System.out.println("Valgt meg");
                        visInteresserte = new TaskHentInteresserte(socket);
                        visInteresserte.run();
                        break;
                    case "INTERESSERT":
                        System.out.println("Mine valg");
                        visMatch = new TaskHentValg(socket);
                        visMatch.run();
                        break;
                    case "LOGIN":
                        personID = innTekst.readInt();
                        System.out.println("Logg Inn " + personID);
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