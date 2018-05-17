package Databases;

import javax.naming.NamingException;
import java.sql.*;

public class SQLiteClass {
    public static Connection conn;
    public static Statement stat;
    public static ResultSet rs;

    public static void Conn() throws ClassNotFoundException, SQLException, NamingException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:C:\\ImagesDB.s3db");
    }

    public static void addPicture(String arrayPicture) throws SQLException, ClassNotFoundException {
        try {
            Conn();
            stat = conn.createStatement();
            String[] ss = arrayPicture.split(",");
            stat.execute("INSERT INTO 'Images'('ImageName','PointsArray') VALUES('" + ss[ss.length] + "','" + arrayPicture + "'");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            stat.close();
            CloseDB();
        }
    }

    public static String getPicture(int id) throws SQLException, NamingException, ClassNotFoundException {
        Conn();
        stat = conn.createStatement();
        rs = stat.executeQuery("SELECT * FROM 'Images' WHERE '_id' = " + id + ");");
        return rs.toString();
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException {
        conn.close();
        stat.close();
        rs.close();
    }
}
