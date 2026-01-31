package multithreading_demo_production;


import java.sql.Connection;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OneToMany implements Runnable {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {

        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(16, 29);
        LocalTime end = LocalTime.of(23, 10);

        boolean inRange =
                (now.isAfter(start) || now.equals(start)) &&
                (now.isBefore(end) || now.equals(end));

        if (!inRange) {
            return;
        }

        try {
            rotateServices();
        } catch (Exception e) {
            e.printStackTrace(); // replace with logger
        }
    }

    private void rotateServices() {
        int service = counter.getAndIncrement() % 2;

        switch (service) {
            case 0:
                serviceAPMS();
                break;
            case 1:
                serviceSHG();
                break;
        }
    }

    private void serviceAPMS() {
        executeSync(
            "SELECT PINSTID, APPLICANT_ID, ADHAR_NO FROM APMS_PERSONAL_DETAILS",
            "UPDATE APMS_DOC_DETAILS SET ADHAR_NO=? WHERE PINSTID=? AND APPLICANT_ID=?",
            "APMS"
        );
    }

    private void serviceSHG() {
        executeSync(
            "SELECT PINSTID, APPLICANT_ID, ADHAR_NO FROM SHG_PERSONAL_DETAILS",
            "UPDATE SHG_DOC_DETAILS SET ADHAR_NO=? WHERE PINSTID=? AND APPLICANT_ID=?",
            "SHG"
        );
    }

    private void executeSync(String selectQuery,
                             String updateQuery,
                             String name) {

        try (Connection con = DBUtil.getConnection()) {

            con.setAutoCommit(false);

            List<Map<String, String>> data =
                    AdharService.getEncryptedAdhar(con, selectQuery);

            if (!data.isEmpty()) {
                AdharService.updateAdharInDocDetails(con, updateQuery, data);
            }

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
