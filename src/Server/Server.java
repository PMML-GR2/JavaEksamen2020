package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static private Thread registrerBruker = new Thread(new TaskRegistrerBruker(8000));

    public static void main(String[] args) {
        registrerBruker.start();
    }


}
