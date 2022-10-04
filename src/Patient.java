import databaes.Query;

import java.sql.ResultSet;

public class Patient {

    public static Boolean isPatientExist(String patientMatricNo) {
        try {
            ResultSet resultSet = Query.select("select * from Patient where PatientMatricNo = '"+patientMatricNo+"'");
            if (resultSet.next()) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // get id of patient by matricNo
    public static int getPatientId(String patientMatricNo) {
        try {
            ResultSet resultSet = Query.select("select * from Patient where PatientMatricNo = '"+patientMatricNo+"'");
            if (resultSet.next()) {
                return resultSet.getInt("PatientId");
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
