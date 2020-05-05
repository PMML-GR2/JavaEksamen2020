package Server;
import java.sql.*;
import java.util.ArrayList;

public class DatingDB {

    public static Connection connect() {
        String url = "jdbc:sqlite:C://SKOLE/emneOBJ2000/dbeksamen/datingdb.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException | ClassNotFoundException e) {
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
               persID =  rs.getInt("PersonID");
               System.out.print("INNI HEEEEEEEEER" + persID);
       }
       catch (SQLException e) {
           System.out.println(e.getMessage() + "HEIEHIEFHSDF");
       }

    return persID;
   }
   static public void visNavnOgTlf(int PersonID1, int PersonID2) {
       String sql = "SELECT Navn, Tlf FROM bruker WHERE PersonID = " + PersonID2;

       try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
           while (rs.next()) {
               System.out.println(rs.getString("Navn") + "\t" +
                       rs.getString("Tlf"));
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
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

   static public void visMineUtsendtInfo(int PersonID) {
        ArrayList<String> bedtomNavn = new ArrayList<String>();
        String sql = "SELECT bruker_PersonID FROM interessert WHERE likerID = " + PersonID;

       try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
           while (rs.next()) {
               bedtomNavn.add(rs.getString("Bruker_PersonID"));
           }
           //System.out.println(bedtomNavn.toString());
       }
       catch (SQLException e) {
           System.out.println(e.getMessage());
       }

       String sql2 = "SELECT PersonID FROM bruker WHERE PersonID = ";
        int i = 0;
               for(String navn : bedtomNavn) {
                    if (i>= 1)
                        sql2 += " OR PersonID = " + navn;
                    else
                        sql2 += navn;
                   i++;
               }
               //System.out.println(sql2);

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

}
