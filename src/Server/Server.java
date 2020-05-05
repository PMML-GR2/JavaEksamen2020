package Server;

import Felles.Handling;
import sample.Bruker;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    //static private Thread registrerBruker = new Thread(new TaskRegistrerBruker(8000));

    //initialiserer server variablene
    static int port = 8000;
    static ServerSocket serverSocket = startOpp(port);
    static String handling;
    static  Socket socket;
    static ArrayList<Bruker> brukerListe = new ArrayList<>();


    public static void main(String[] args) {

        DataInputStream innTekst = null;

        TaskKjørSøk kjørSøk;
        TaskRegistrerBruker regBruker;
        TaskNavnOgTlf visMatch;

        //DatingDB.visNavnOgTlf(10,13);
        //ArrayList<String> test = new ArrayList<>();
        //test.add(DatingDB.visNavnOgTlf(10,13).toString());
        System.out.println(DatingDB.visNavnOgTlf(10,13).toString());


        //Lytter og venter på at noen skal koble seg til å lage ny bruker
/*
        try{
            while(true) {
                Socket socket = serverSocket.accept();

                innTekst = new DataInputStream(socket.getInputStream());
                handling = innTekst.readUTF();

                switch (handling) {
                    case "REGISTRER":
                        System.out.println("Dette er REGISTER");
                        regBruker = new TaskRegistrerBruker(socket);
                        regBruker.run();
                        break;
                    case "SOK":
                        System.out.println("SOOK");
                        kjørSøk = new TaskKjørSøk(socket);
                        kjørSøk.run();
                        break;
                    case "KONTAKT":
                        System.out.println("Kontakt");
                        break;
                    case "INTERESSERT":
                        System.out.println("Interessert");
                        visMatch = new TaskNavnOgTlf(socket);
                        visMatch.run();
                        break;
                    case "LOGGINN":
                        System.out.println("Logg Inn Id: " + innTekst.readInt());
                        break;
                }
            }

                 }catch (IOException ex){ex.printStackTrace();}

    }


    //Opprett en server
    static private ServerSocket startOpp(int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server er startet opp på " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return serverSocket;
    }
}
