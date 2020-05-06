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

    static public ArrayList<Bruker> søkMatch(int PersonID, String kjonn, int minAlder, int maxAlder) {
        //Finn brukere
        String sql = "SELECT * FROM bruker "
                + "WHERE Kjonn = " + "'" + kjonn + "'"
                + " AND Alder BETWEEN " + minAlder + " AND " + maxAlder + " AND PersonID <> " + PersonID
                + " LIMIT 10;";

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
                String tlfNr = rs.getString("Tlf");
                int lengde = interesseTekst.length() - 1;
                String kuttInteresseTekst = interesseTekst.substring(1, lengde);
                String[] splitTabell = kuttInteresseTekst.split(",");

                brukerListe.add(new Bruker(personID, navn, kjønn, alder, new ArrayList<>(Arrays.asList(splitTabell)), bosted, tlfNr));
            }
             }catch(SQLException e){
                System.out.println(e.getMessage());
            }

                    return sammenligneInteresser(brukerListe, eier);

            //Sammenligner interessen til bruker og matcher og sorterer utifra hvor mye de matcher.
    }
    // Skriver ut profilinformasjon
    static public Bruker minProfil(int PersonID) {
        Bruker innloggetBruker = new Bruker();
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

                innloggetBruker = new Bruker(personID, navn, kjønn, alder, new ArrayList<>(Arrays.asList(splitTabell)), bosted,tlfNr);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("minProfil");
        return innloggetBruker;

    }

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
        ArrayList<Integer> interessert = new ArrayList<>();

        String sql = "SELECT * FROM interessert WHERE likerID = " + PersonID;
       System.out.print("interessert I meg før sql laging " + sql);

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
       if(interessert.size() != 0) {
           String sql2 = "SELECT * from bruker WHERE PersonID = ";
           int i = 0;
           for (int ID : interessert) {
               if (i >= 1)
                   sql2 += " OR PersonID = " + ID;
               else
                   sql2 += ID;
               i++;
           }
           System.out.println("interessert I meg etter sql laging " + sql2);
           return brukerFyll(sql2);
       } else return new ArrayList<Bruker>();
   }

   //Skriver ut alle brukere som pålogget-bruker er interessert i
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
       System.out.println(sql2 + "-Minevalg");
       return brukerFyll(sql2);
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
        System.out.println("BrukerFyll");
        return brukerListe;
    }
}
