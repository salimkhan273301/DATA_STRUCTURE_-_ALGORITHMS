package multithreading_demo_production;



import java.sql.*;
import java.util.*;

public class AdharService {

    public static List<Map<String, String>> getEncryptedAdhar(
            Connection con, String query) {

        List<Map<String, String>> res = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= count; i++) {
                    row.put(meta.getColumnName(i),
                            rs.getString(i) != null ? rs.getString(i) : "");
                }
                res.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching Aadhaar data", e);
        }
        return res;
    }

    public static void updateAdharInDocDetails(
            Connection con, String updateQuery,
            List<Map<String, String>> data) {

        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {

            for (Map<String, String> row : data) {
                ps.setString(1, row.get("ADHAR_NO"));
                ps.setString(2, row.get("PINSTID"));
                ps.setString(3, row.get("APPLICANT_ID"));
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating Aadhaar data", e);
        }
    }
}
