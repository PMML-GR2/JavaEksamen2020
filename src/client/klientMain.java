package client;

import java.io.*;
import java.net.*;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.ArrayList;

public class klientMain{
    static String host = "localhost";
    static int port = 8000;
    static Socket socket;
    static int tildeltPersonID = 0;
    static ArrayList<Bruker> søkTreff = new ArrayList<>();
    static ArrayList<Bruker> interessertI = new ArrayList<>();
    static ArrayList<Bruker> likerMeg = new ArrayList<>();
    static Bruker bruker;

    static DataOutputStream skrivUt;
    static DataInputStream hentTekst;
    static ObjectOutputStream skrivObjekt;
    static ObjectInputStream lesObjekt;


    static public void oppStart(String handling) throws IOException, ClassNotFoundException {
        if (tildeltPersonID != 0) {
            DataService dataservice = DataService.getInstance();
            socket = new Socket(host,port);
            skrivUt = new DataOutputStream(socket.getOutputStream());

            skrivUt.writeUTF(handling);
            skrivUt.writeInt(tildeltPersonID);

            lesObjekt = new ObjectInputStream(socket.getInputStream());

            interessertI.addAll((ArrayList<Bruker>)lesObjekt.readObject());
            likerMeg.addAll((ArrayList<Bruker>)lesObjekt.readObject());
            bruker = (Bruker)lesObjekt.readObject();

            dataservice.setLagtTilBruker(interessertI);
            dataservice.setBrukerHarLagtTil(likerMeg);
            dataservice.setBruker(bruker);

            skrivUt.close();
            lesObjekt.close();
            socket.close();
        }
    }

    static public void registrerBruker(String handling,String navn, String kjonn, int alder, String interesser,String by, String tlf) throws IOException,
            ClassNotFoundException{

        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());
        hentTekst = new DataInputStream(socket.getInputStream());

        skrivUt.writeUTF(handling);
        skrivUt.writeUTF(navn);
        skrivUt.writeUTF(kjonn);
        skrivUt.writeInt(alder);
        skrivUt.writeUTF(interesser);
        skrivUt.writeUTF(by);
        skrivUt.writeUTF(tlf);

//Sender brukerens tildelte personID etter registrering.
        tildeltPersonID = hentTekst.readInt();
        skrivTilTekstFil(tildeltPersonID);

        oppStart("LOGIN");

        hentTekst.close();
        skrivUt.close();
        socket.close();
    }

    static public void sokKlient(String handling, String kjonn, int miniAlder, int maxAlder)  throws IOException,
            ClassNotFoundException{

        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());

        skrivUt.writeUTF(handling);
        skrivUt.writeUTF(kjonn);
        skrivUt.writeInt(tildeltPersonID);
        skrivUt.writeInt(miniAlder);
        skrivUt.writeInt(maxAlder);

        lesObjekt = new ObjectInputStream(socket.getInputStream());


        DataService.getInstance().setMatchBrukere((ArrayList<Bruker>)lesObjekt.readObject());

        lesObjekt.close();
        skrivUt.close();
        socket.close();
    }

    public static void interessertI(String handling,int likerID) throws IOException,
            ClassNotFoundException{
        DataService dataservice = DataService.getInstance();
        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());


        skrivUt.writeUTF(handling);
        skrivUt.writeInt(tildeltPersonID);
        skrivUt.writeInt(likerID);

        lesObjekt = new ObjectInputStream(socket.getInputStream());

        interessertI.addAll((ArrayList<Bruker>)lesObjekt.readObject());
        dataservice.setBrukerHarLagtTil(interessertI);

        lesObjekt.close();
        skrivUt.close();
        socket.close();

    }
    public static void interessertIMeg(String handling,int personID) throws IOException,
            ClassNotFoundException{

        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());


        skrivUt.writeUTF(handling);
        skrivUt.writeInt(personID);

        lesObjekt = new ObjectInputStream(socket.getInputStream());

        likerMeg.addAll((ArrayList<Bruker>)lesObjekt.readObject());

        lesObjekt.close();
        skrivUt.close();
        socket.close();
    }


    //skriver personID til en tekstfil på klientens pc.
    static public void skrivTilTekstFil(int personID){
        try{
            //skriv inn en filepath som passer deg
            File fil = new File("C:\\SKOLE\\emneOBJ2000\\eksamenstesting\\personID.txt");
            if(!fil.exists()){
                PrintWriter utSkriv = new PrintWriter(fil);
                utSkriv.print(personID);
                utSkriv.close();
            }

        }catch(FileNotFoundException ex){ex.printStackTrace(); }
        System.out.println("Skrivet til fil " + personID);
    }


    static public boolean hentIDFraTekstFil() throws IOException, ClassNotFoundException{
        //skriv inn en filepath som passer deg
        File fil = new File("C:\\SKOLE\\emneOBJ2000\\eksamenstesting\\personID.txt");
        if(fil.exists()) {
            Scanner skanner = new Scanner(fil);
            socket = new Socket(host, port);
            skrivUt = new DataOutputStream(socket.getOutputStream());
            int id = 0;
            while (skanner.hasNext()) {
                id = skanner.nextInt();
            }
            tildeltPersonID = id;
            skrivUt.writeInt(id);

            skrivUt.close();
            skanner.close();
            return true;
        }else return false;
    }

}

