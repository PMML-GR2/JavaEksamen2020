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
        //Finn brukere
        String sql = "SELECT * FROM bruker "
                + "WHERE Kjonn = " + "'" + kjonn + "'"
                + " AND Alder BETWEEN " + minAlder + " AND " + maxAlder + " AND PersonID <> " + PersonID
                + " LIMIT 10;";


        ArrayList<Bruker> brukerListe = new ArrayList<>();
        Bruker bruker;

        Bruker eier;

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

            }
             }catch(SQLException e){
                System.out.println(e.getMessage());
            }

       String sql2 = "SELECT * FROM Bruker WHERE PersonID = " + PersonID;

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

                    eier = new Bruker(personID, navn, kjønn, alder, new ArrayList<>(Arrays.asList(splitTabell)), bosted,tlfNr);
                    sammenligneInteresser(brukerListe, eier);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //Sammenligner interessen til bruker og matcher og sorterer utifra hvor mye de matcher.
    }

    //Går igjennom alle matcher sine interesser og gir dem poeng dersom det er en match i interesse.
    //Sender ut liste med brukere sortert fra størst til minst poeng. Dem med mest poeng har mest til felles.
    static public void sammenligneInteresser(ArrayList<Bruker> brukerTabell, Bruker eier) {
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
                    }

                }
            }

            brukerInteresse.clear();
            nyTabell.add(b);
            System.out.println("-");
        }

        Collections.sort(nyTabell,Collections.reverseOrder());

        for(Bruker b: nyTabell) {
            System.out.println(b.getPersonID() + ": " + b.getPoengSum() + " " + b.getFornavn());
        }

    }
       

    // Navn og telefonNr til person som bruker liker blir vist
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

   static public ArrayList<Bruker> interessertIMeg(int PersonID) {
        ArrayList<Integer> interessert = new ArrayList<>();

        String sql = "SELECT * FROM interessert WHERE likerID = " + PersonID;

       try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
           while (rs.next()) {
               int personID = rs.getInt("PersonID");
               interessert.add(personID);
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
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
   }

   static public ArrayList<Bruker> mineValg(int PersonID) {
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
       String sql2 = "SELECT * from bruker WHERE PersonID = ";
       int i = 0;
       for (int ID : interessert) {
           if (i >= 1)
               sql2 += " OR PersonID = " + ID;
           else
               sql2 += ID;
           i++;
       }
       System.out.println("MineValg");
       return brukerFyll(sql2);
   }
           static public void visMineUtsendtInfo(int PersonID) {
       Bruker bruker;
       ArrayList<Bruker> brukerListe = new ArrayList<>();

        String sql = "SELECT * FROM interessert WHERE likerID = " + PersonID;

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
           System.out.println(e.getMessage());
       }

       String sql2 = "SELECT * FROM bruker WHERE PersonID = ";
        int i = 0;
               for(Bruker navn : brukerListe) {
                    if (i>= 1)
                        sql2 += " OR PersonID = " + navn;
                    else
                        sql2 += navn;
                   i++;
               }

       try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql2)) {
           while (rs.next()) {
               System.out.println(rs.getString("PersonID"));
           }
       }
       catch (SQLException e) {
           System.out.println(e.getMessage());
       }
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
        System.out.println("BrukerFyll");
        return brukerListe;
    }
}
