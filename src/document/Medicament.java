package document;

import databaes.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Medicament {
    private Double medicament_rate;
    private String medicament_code;


    public  void addMedicament(String codeFolder,String medicamentCode){
        // wen to get Rate of medicament
        Double medicamentRate = Medicament.getMedicamentByField(medicamentCode,"medicament_rate");
        Boolean isInserted = saveMedicament(codeFolder,medicamentRate,medicamentCode);


    }

    public Boolean  saveMedicament(String codeFolder , Double medicamentRate , String medicamentCode){
        String query = "INSERT INTO medicamentList (folder_code,medicament_rate,medicament_code) VALUES ('"+codeFolder+"','"+medicamentRate+"','"+medicamentCode+"')";
        try {
            Query.insert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // getMedicamentRate
    public static Double getMedicamentByField(String medicamentCode,String field){

        try {
            ResultSet resultSet = Query.select("SELECT "+field+" FROM medicament_list WHERE medicament_code = '"+medicamentCode+"'");
            if (resultSet.next()) {
                return resultSet.getDouble(field);
            } else {
                return 0.0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }




}
