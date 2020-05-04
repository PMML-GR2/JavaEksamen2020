package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    //static private Thread registrerBruker = new Thread(new TaskRegistrerBruker(8000));

    public static void main(String[] args) {
        ArrayList<String> interesser = new ArrayList<String>();
        //registrerBruker.start();
        DatingDB.registrerBruker("Mikael", "M", 30, interesser, "Bø", "88888888");
        DatingDB.visInteresser();
    }


}
