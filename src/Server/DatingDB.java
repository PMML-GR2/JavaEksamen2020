package Server;
import java.sql.*;
import java.util.*;

import sample.Bruker;

public class DatingDB {

    public static Connection connect() {
        String url = "jdbc:sqlite:C://SKOLE/emneOBJ2000/dbeksamen/datingdb.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    static public synchronized int registrerBruker(String navn, String kjonn, int alder, ArrayList<String> interesser, String bosted, String tlf) {
      /*
       System.out.println(navn + kjonn + alder + bosted + tlf);
       for(String s: interesser) {
           System.out.println(s);
       }
*/
        int persID = 0;

        String sql = "INSERT INTO bruker(Navn, Kjonn, Alder, Interesser, Bosted, Tlf) VALUES (?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, navn);
            pstmt.setString(2, kjonn);
            pstmt.setInt(3, alder);
            pstmt.setString(4, String.valueOf(interesser));
            pstmt.setString(5, bosted);
            pstmt.setString(6, tlf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sql = "SELECT MAX(PersonID) AS 'PersonID' FROM bruker";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            persID = rs.getInt("PersonID");
            System.out.print("INNI HEEEEEEEEER" + persID);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "HEIEHIEFHSDF");
        }

        return persID;
    }

    static public void søkMatch(int PersonID, String kjonn, int minAlder, int maxAlder) {
        String sql = "SELECT * FROM bruker "
                + "WHERE Kjonn = " + "'" + kjonn + "'"
                + " AND Alder BETWEEN " + minAlder + " AND " + maxAlder
                + " LIMIT 10;";


        HashMap<Integer, ArrayList<String>> interesseMap = new HashMap<>();
        ArrayList<Bruker> brukerListe = new ArrayList<>();

        Bruker bruker;
        String [] interess = {"Bil","Hest","musikk"," Steinkasting"};
        Bruker eier = new Bruker(8, "Felix", "M", 27, new ArrayList<>(Arrays.asList(interess)), "Film", "2352352");

        /*
        //DUMMY LISTE
        ArrayList<String> interesseListe = new ArrayList<>();
        interesseListe.add("Bil");
        interesseListe.add("Hest");
        interesseListe.add("musikk");
        interesseListe.add("Steinkasting");
*/
        //Henter alle brukere som matcher på alder og kjønn
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                //Henter all brukerinfoen og legger dem inn i brukertabellen.
                int personID = rs.getInt("PersonID");
                String navn = rs.getString("Navn");
                String kjønn = rs.getString("Kjonn");
                int alder = rs.getInt("Alder");
                String interesseTekst = rs.getString("interesser");
                String bosted = rs.getString("Bosted");
                String tlfNr = rs.getString("Tlf");
                int lengde = interesseTekst.length() - 1;
                String kuttInteresseTekst = interesseTekst.substring(1, lengde);
                String[] splitTabell = kuttInteresseTekst.split(",");

                bruker = new Bruker(personID, navn, kjønn, alder, new ArrayList<>(Arrays.asList(splitTabell)), bosted, tlfNr);
                brukerListe.add(bruker);
                //System.out.println(eier.toString());

            }

            //Sammenligner interessen til bruker og matcher og sorterer utifra hvor mye de matcher.
            sammenligneInteresser(brukerListe, eier);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Går igjennom alle matcher sine interesser og gir dem poeng dersom det er en match i interesse.
    //Sender ut liste med brukere sortert fra størst til minst poeng. Dem med mest poeng har mest til felles.
    static public void sammenligneInteresser(ArrayList<Bruker> brukerTabell, Bruker eier) {
        //HashMap<Integer, Integer> poengMap = new HashMap<>();
        //HashMap<Integer, ArrayList<String>> matchMap = new HashMap<>();
        int i = 0;
        ArrayList<String> eierInteresse = new ArrayList<>();
        eierInteresse.addAll(eier.getInterresser());

        ArrayList<String> brukerInteresse = new ArrayList<>();

        ArrayList<Bruker> nyTabell = new ArrayList<>();


        for (Bruker b : brukerTabell ) {
            int poeng = 0;
            brukerInteresse.addAll(b.getInterresser());
            for (String s : eierInteresse) {
                s.trim();
                for (String j : brukerInteresse) {
                    j.trim();
                    if (s.equals(j)) {
                        b.setPoengSum(b.getPoengSum() + 1);
                        nyTabell.add(b);
                       System.out.print("(TRUE!)");
                    }
                    System.out.print(b.getPersonID() + ": " + s + "-" + j + ",  ");
                }
            }
            System.out.println("-");
        }

        Collections.sort(nyTabell);
        //System.out.println("Etter: " +nyTabell);

        for(Bruker b: nyTabell) {
            System.out.println(b.getPoengSum() + " " + b.getFornavn());
        }

        //For å sammenligne interesser og tilføre dem poeng mapper jeg dem til poengsum
           /*
        for (Bruker b : brukerTabell) {
            matchMap.put(b.getPersonID(), b.getInterresser());
            i++;
        }
        //brukerTabell.get(i).getInterresser()


        for (int key : matchMap.keySet()) {
            int poeng = 0;

            for (String s : eier) {
                s.trim();
                for (String j : matchMap.get(key)) {
                    j.trim();
                    if (s.equals(j)) {
                        poeng += 1;
                        System.out.print("(TRUE!)");
                    }
                    System.out.print(key + ": " + s + "-" + j + ",  ");
                }
            }
            poengMap.put(key, poeng);
            System.out.println();
        }
        System.out.println(poengMap);


        //Skriver ut resultatet i sortert rekkefølge

        Iterator it = poengMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            for(Bruker b: brukerTabell){
                if(b.getPersonID() == (int)pair.getKey()){
                    b.setPoengSum((int)pair.getValue());
                }
            }
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

         */
       // Collections.sort(brukerTabell);
        //for(Bruker b: brukerTabell)
        //System.out.println(b.getPoengSum());
    }

   static public void visInteresser() {
        String sql = "SELECT Interesser FROM bruker";

        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("Interesser"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static public void  selectTableWhere(int personID, String k, ArrayList alder){
       System.out.println("Fant en " + k + " i alder fra " + alder.get(0)+ " - " + alder.get(1)+ " til person " + personID);
    }
}
