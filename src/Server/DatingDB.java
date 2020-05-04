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
   static public void registrerBruker(String navn, String kjonn, int alder, ArrayList<String> interesser, String bosted, String tlf) {
       //System.out.println(navn + k + alder + bosted + tlf);
       //for(String s: interesser) {
       //    System.out.println(s);
       //}
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
}