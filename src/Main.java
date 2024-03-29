import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:/JavaProjekte/TestDB/" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";


    public static void main(String[] args) {



//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:/Volumes/Production/Courses/Programs/JavaPrograms/TestDB/testjava.db");
//            Statement statement = conn.createStatement()) {
//            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            //    Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/JavaProjekte/TestDB/testjava.db");
      //     conn.setAutoCommit(false);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text, " +
                    COLUMN_PHONE + " integer, " +
                    COLUMN_EMAIL + " text" +
                    ")");
//            statement.execute("INSERT INTO contacts(name,phone,email) " + "VALUES('joe' , 987654, 'joe@joe.de')");
//            statement.execute("INSERT INTO contacts(name,phone,email) " + "VALUES('james' , 1112222, 'james@james.de')");
//            statement.execute("INSERT INTO contacts(name,phone,email) " + "VALUES('momo' , 444555, 'momo@momo.de')");
//            statement.execute("INSERT INTO contacts(name,phone,email) " + "VALUES('ben' , 6665555, 'ben@ben.de')");
// statement.execute("UPDATE contacts SET phone=0912 WHERE name='joe'");
//            statement.execute("DELETE FROM contacts WHERE name='momo'");
            insertContact(statement,"Tim", 6545678, "tim@email.com");
            insertContact(statement,"Joe", 45632, "joe@anywhere.com");
            insertContact(statement,"Jane", 4829484, "jane@somewhere.com");
            insertContact(statement,"Fido", 9038, "dog@email.com");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                    COLUMN_PHONE + "=5566789" +
                    " WHERE " + COLUMN_NAME + "='Jane'");

            statement.execute("DELETE FROM " +TABLE_CONTACTS +
                    " WHERE " + COLUMN_NAME + "='Joe'");



            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while(results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " " +
                        results.getInt(COLUMN_PHONE) + " " +
                        results.getString(COLUMN_EMAIL));
            }
            results.close();


            statement.close();
            conn.close();

//            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\databases\\testjava.db");
//            Class.forName("org.sql.JDBC");

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                " ) " +
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
}




