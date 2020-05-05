package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TaskNavnOgTlf implements Runnable {
    int SpørrID;
    int VisID;
    ArrayList<String> navnOgTlf = new ArrayList<String>();



    String Navn = "";
    String Tlf = "";


    Socket socket;
    DataInputStream innTekst = null;
    DataOutputStream utTekst = null;

    public TaskNavnOgTlf(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Bruker ber om navn og tlf");

            //Lag dataOutput til klient
            innTekst = new DataInputStream(socket.getInputStream());
            utTekst = new DataOutputStream(socket.getOutputStream());

            SpørrID = innTekst.readInt();
            VisID = innTekst.readInt();

            //DatingDB.visNavnOgTlf(SpørrID,VisID);

            navnOgTlf.add(DatingDB.visNavnOgTlf(SpørrID, VisID).toString());

            Navn = navnOgTlf.get(0);






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
