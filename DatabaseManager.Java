import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3956/Mydatabase";
    private static final String USER = "Beacon Bin";
    private static final String PASSWORD = "123Mydatabase";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveFormData(String name, String mobile, String gender, String dob, String address) {
        String sql = "INSERT INTO registration (name, mobile, gender, dob, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, mobile);
            pstmt.setString(3, gender);
            pstmt.setString(4, dob);
            pstmt.setString(5, address);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayFormData(JTable table) {
        String sql = "SELECT * FROM registration";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Creating a table model and setting it to the JTable
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Mobile", "Gender", "DOB", "Address"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("mobile"), rs.getString("gender"), rs.getString("dob"), rs.getString("address")});
            }
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
