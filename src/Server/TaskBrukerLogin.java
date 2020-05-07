package Server;

import client.Bruker;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

//denne klassen tar seg av socket kommunikasjonen når en bruker skal skru på appen.
//sender dataene til behandling i DatingDB å sender svaret ut i arraylist til klient
public class TaskBrukerLogin implements Runnable {
    int personID;
    ArrayList<Bruker> interessertI = new ArrayList<>();
    ArrayList<Bruker> likerMeg = new ArrayList<>();
    Bruker minBruker;

    Socket socket;
    ObjectOutputStream outObject = null;

    public TaskBrukerLogin(Socket socket, int personID){
        this.socket = socket;
        this.personID = personID;
    }

    @Override
    public void run() {
        try {
            //Lag dataOutput til klient
            outObject = new ObjectOutputStream(socket.getOutputStream());

            //bruker metodene i DatingDB for å behandle dataene med sqli
            minBruker = DatingDB.minProfil(personID);
            interessertI = DatingDB.mineValg(personID);
            likerMeg = DatingDB.interessertIMeg(personID);
            //Skriver ut arraylist
            outObject.writeObject(interessertI);
            outObject.writeObject(likerMeg);
            outObject.writeObject(minBruker);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outObject.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
