package Server;

import java.sql.*;
import java.util.*;
import client.Bruker;

//database klassen - Tar seg av all kobling mot databasen og
//sender ut resultat til Task klassene som tar seg av socket kommunikasjon
public class DatingDB {

    //kobler seg til databasen
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


    // Registrerer ny bruker
    static public synchronized int registrerBruker(String navn, String kjonn, int alder, ArrayList<String> interesser, String bosted, String tlf) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        oppdaterInteressert(1,persID);
        return persID;
    }


    // Søker etter matcher ut i fra like interesser
    static public ArrayList<Bruker> søkMatch(int PersonID, String kjonn, int minAlder, int maxAlder) {
        //Finn brukere
        if (PersonID != 0) {
        String sql = "SELECT * FROM bruker "
                + "WHERE Kjonn = " + "'" + kjonn + "'"
                + " AND Alder BETWEEN " + minAlder + " AND " + maxAlder + " AND PersonID <> " + PersonID;

            ArrayList<Bruker> brukerListe = new ArrayList<>();
            Bruker eier = minProfil(PersonID);


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
                String tlf = rs.getString("Tlf");

                int lengde = interesseTekst.length() - 1;
                String kuttInteresseTekst = interesseTekst.substring(1, lengde);
                String[] splitTabell = kuttInteresseTekst.split(",");

                brukerListe.add(new Bruker(personID, navn, kjønn, alder, new ArrayList<>(Arrays.asList(splitTabell)),bosted,tlf));
            }
             }catch(SQLException e){
                System.out.println(e.getMessage());
            }


            //Sammenligner interessen til bruker og matcher og sorterer utifra hvor mye de matcher.
            if (brukerListe.size() != 0) {
                return sammenligneInteresser(brukerListe, eier);
            } else return new ArrayList<Bruker>();
        } else return new ArrayList<Bruker>();
        }


    // Skriver ut profilinformasjon
    static public Bruker minProfil(int PersonID) {
        if (PersonID != 0) {
            Bruker innloggetBruker = new Bruker();
            String sql2 = "SELECT * FROM bruker WHERE PersonID = " + PersonID;

            try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql2)) {
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
                    innloggetBruker = new Bruker(personID, navn, kjønn, alder, new ArrayList<>(Arrays.asList(splitTabell)), bosted, tlfNr);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return innloggetBruker;
        } return new Bruker();
    }


    //Sammenligner interesse med innlogget bruker og skriver ut alle brukerene i sortert rekkefølge
    static public ArrayList<Bruker> sammenligneInteresser(ArrayList<Bruker> brukerTabell, Bruker eier) {
        int i = 0;
        ArrayList<String> eierInteresse = new ArrayList<>();
        eierInteresse.addAll(eier.getInterresser());
        ArrayList<String> brukerInteresse = new ArrayList<>();
        ArrayList<Bruker> nyTabell = new ArrayList<>();

        for (Bruker b : brukerTabell ) {
            int poeng = 0;
            brukerInteresse.addAll(b.getInterresser());
            for (String s : eierInteresse) {
                s = s.trim();
                s = s.toLowerCase();
                for (String j : brukerInteresse) {
                    j = j.trim();
                    j = j.toLowerCase();
                    if (s.equals(j)) {
                        b.setPoengSum(b.getPoengSum() + 1);
                    }
                }
            }
            brukerInteresse.clear();
            nyTabell.add(b);
        }

        //sorterer de etter poeng
        Collections.sort(nyTabell,Collections.reverseOrder());

        //Skriv ut bare top 10;
        if(nyTabell.size() > 10){
            int fjern = nyTabell.size() - 10;
            while(fjern != 0){
                nyTabell.remove(nyTabell.size()-1);
                fjern--;
            }
        }
        return nyTabell;
    }


    // Legger inn interessert forholdet mellom brukere
   static public void oppdaterInteressert(int PersonID1, int PersonID2) {
       String sql2 = "INSERT INTO interessert VALUES (?,?)";

       try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql2)) {
           pstmt.setInt(1, PersonID1);
           pstmt.setInt(2, PersonID2);
           pstmt.executeUpdate();
       }
       catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }


   //Skriver ut alle som liker meg
   static public ArrayList<Bruker> interessertIMeg(int PersonID) {
        if (PersonID != 0) {
            ArrayList<Integer> interessert = new ArrayList<>();

            String sql = "SELECT * FROM interessert WHERE likerID = " + PersonID;

            try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    int personID = rs.getInt("bruker_PersonID");
                    interessert.add(personID);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if (interessert.size() != 0) {
                String sql2 = "SELECT * from bruker WHERE PersonID = ";
                int i = 0;
                for (int ID : interessert) {
                    if (i >= 1)
                        sql2 += " OR PersonID = " + ID;
                    else
                        sql2 += ID;
                    i++;
                }
                return brukerFyll(sql2);
            } else return new ArrayList<>();
        } return new ArrayList<>();
   }


   //Skriver ut alle brukere som pålogget-bruker er interessert i
   static public ArrayList<Bruker> mineValg(int PersonID) {
       if (PersonID != 0) {
           ArrayList<Integer> interessert = new ArrayList<>();

           String sql = "SELECT * FROM interessert WHERE bruker_PersonID = " + PersonID;

           try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   int likerID = rs.getInt("likerID");
                   interessert.add(likerID);
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
           if (interessert.size() != 0) {
               String sql2 = "SELECT * from bruker WHERE PersonID = ";
               int i = 0;
               for (int ID : interessert) {
                   if (i >= 1)
                       sql2 += " OR PersonID = " + ID;
                   else
                       sql2 += ID;
                   i++;
               }
               return brukerFyll(sql2);
           } else return new ArrayList<>();
       } return new ArrayList<>();
   }


    //Hjelpemetode for å skrive ut alle brukere som har fulgt en kriterie laget utenfor
    static ArrayList<Bruker> brukerFyll(String sql) {
        Bruker bruker;
        ArrayList<Bruker> brukerListe = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
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
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return brukerListe;
    }
}
