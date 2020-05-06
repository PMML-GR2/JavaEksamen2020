package sample;
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
    static int tildeltPersonID;
    static ArrayList<Bruker> interessertI = new ArrayList<>();
    static ArrayList<Bruker> likerMeg = new ArrayList<>();
    static Bruker bruker;

    static DataOutputStream skrivUt;
    static DataInputStream hentTekst;
    static ObjectOutputStream skrivObjekt;
    static ObjectInputStream lesObjekt;

    //kjører spørringene
    public static void main(String[] args) {
        try{
            hentIDFraTekstFil();
            interessertI("INTERESSERT",tildeltPersonID,11);
            interessertIMeg("INTERESSERTIMEG", tildeltPersonID, 14);
            sokKlient("SOK","M", tildeltPersonID,18, 70);
            registrerBruker("REGISTRER","Ida", "K", 23,"musikk,Hest,Steinkasting","Bø","323352352");
            oppStart("LOGIN", tildeltPersonID);
        }catch(IOException ex){
            //Kanskje skrive en besked i GUI om at man har skrevet inn ulovlig/feil informasjon???
            ex.printStackTrace();
        }catch(ClassNotFoundException ex2){
            ex2.printStackTrace();
        }
    }
    static public void oppStart(String handling, int tildeltPersonID) throws IOException, ClassNotFoundException {
        if (tildeltPersonID != 0) {
            //System.out.print("Jeg er inni her");
            socket = new Socket(host,port);
            skrivUt = new DataOutputStream(socket.getOutputStream());

            skrivUt.writeUTF(handling);
            skrivUt.writeInt(tildeltPersonID);

            lesObjekt = new ObjectInputStream(socket.getInputStream());

            interessertI.addAll((ArrayList<Bruker>)lesObjekt.readObject());
            likerMeg.addAll((ArrayList<Bruker>)lesObjekt.readObject());
            bruker = (Bruker)lesObjekt.readObject();

            System.out.println(interessertI);
            System.out.println(likerMeg);
            System.out.println(bruker);

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
        int tildeltPersonID = 0;
//skrivHandle = new ObjectOutputStream(socket.getOutputStream());

        skrivUt.writeUTF(handling);
        skrivUt.writeUTF(navn);
        skrivUt.writeUTF(kjonn);
        skrivUt.writeInt(alder);
        skrivUt.writeUTF(interesser);
        skrivUt.writeUTF(by);
        skrivUt.writeUTF(tlf);

        System.out.println("Data sendt");

//Sender brukerens tildelte personID etter registrering.
        tildeltPersonID = hentTekst.readInt();
        skrivTilTekstFil(tildeltPersonID);

        hentTekst.close();
        skrivUt.close();
        socket.close();
//skrivHandle.close();
    }

    static public void sokKlient(String handling, String kjonn, int personID,int miniAlder, int maxAlder)  throws IOException,
            ClassNotFoundException{

        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());
//skrivHandle = new ObjectOutputStream(socket.getOutputStream());

        skrivUt.writeUTF(handling);
        skrivUt.writeUTF(kjonn);
        skrivUt.writeInt(tildeltPersonID);
        skrivUt.writeInt(miniAlder);
        skrivUt.writeInt(maxAlder);


        System.out.println("SøkData sendt");
//in.close();
        skrivUt.close();
        socket.close();
    }

    public static void interessertI(String handling,int personID, int likerID) throws IOException,
            ClassNotFoundException{
        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());


        skrivUt.writeUTF(handling);
        skrivUt.writeInt(personID);
        skrivUt.writeInt(likerID);

        lesObjekt = new ObjectInputStream(socket.getInputStream());

        interessertI.addAll((ArrayList<Bruker>)lesObjekt.readObject());

        System.out.println(interessertI + "Hallaaaa");

        lesObjekt.close();
        skrivUt.close();
        socket.close();

    }
    public static void interessertIMeg(String handling,int personID, int likerID) throws IOException,
            ClassNotFoundException{

        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());


        skrivUt.writeUTF(handling);
        skrivUt.writeInt(personID);
        skrivUt.writeInt(likerID);

        lesObjekt = new ObjectInputStream(socket.getInputStream());

        likerMeg.addAll((ArrayList<Bruker>)lesObjekt.readObject());

        System.out.println(likerMeg + "Hallaaaa");

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
                //tildeltPersonID = personID; ??
                utSkriv.print(personID);
                utSkriv.close();
            }


        }catch(FileNotFoundException ex){ex.printStackTrace(); }
        System.out.println("Skrivet til fil " + personID);

    }


    static public void hentIDFraTekstFil() throws IOException, ClassNotFoundException{

        //skriv inn en filepath som passer deg
        File fil = new File("C:\\SKOLE\\emneOBJ2000\\eksamenstesting\\personID.txt");
        Scanner skanner = new Scanner(fil);
        socket = new Socket(host,port);
        skrivUt = new DataOutputStream(socket.getOutputStream());
        int id = 0;
        while(skanner.hasNext()){
            id = skanner.nextInt();
        }

        System.out.println(id);
        tildeltPersonID = id;

        skrivUt.writeInt(id);

        skrivUt.close();
        skanner.close();



    }

}

