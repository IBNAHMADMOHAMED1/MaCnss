import databaes.Query;
import document.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Folder {
    private String folderCode;
    private String typeOfArrangement;
    private String statusFolder;
    private String patientMatricNo;
    private Double totalAmount = 0.0;
    public static Double totalAmountReimbursed = 0.0;
    private List<Medicament> medicaments = new ArrayList<Medicament>();
    private Analysis analysis;
    private Radio radio;
    private static int tryCount = 0;


    // add new folder
    public static void addNewFolder() {
        Boolean isPatientExist;
        String patientMatricNo = getPatientMatricNo();
        isPatientExist = Patient.isPatientExist(patientMatricNo);
        if (!isPatientExist) {
            System.out.println("Patient does not exist");
            tryCount++;
            if (tryCount < 3) {
                addNewFolder();
            } else {
                System.out.println("You have exceeded the number of attempts");
                wait30Seconds();
            }
        } else {
            int patientId = Patient.getPatientId(patientMatricNo);
            System.out.println("Patient Id: " + patientId);
            int totalAmountOfFolder = getTotalAmountOfFolder();
            System.out.println("Total Amount of Folder: " + totalAmountOfFolder + " DH");
            String typeOfArrangement = getTypeOfArrangement();
            System.out.println("Type of Arrangement: " + typeOfArrangement);
            String folderCode = genrateFolderCode();
            System.out.println("Folder Code: " + folderCode);
            int totalAmountArrangement = getTotalAmountArrangement();
            System.out.println("Total Amount Arrangement: " + totalAmountArrangement + " DH");
            Double priceOfArrangement = getPriceOfArrangement(typeOfArrangement);
            System.out.println("Price of Arrangement: " + priceOfArrangement + " DH");
            totalAmountReimbursed = totalAmountOfFolder - priceOfArrangement;
            System.out.println("Total Amount Reimbursed: " + totalAmountReimbursed + " DH");
            // ask if the user wants to add medicaments
            String query =  "insert into FolderPatient (FolderNumber,PatientId,TypeOfArrangement,TotalAmount,TotalAmountReimbursement) values ('" + folderCode + "'," + patientId + ",'" + typeOfArrangement + "'," + totalAmountOfFolder + "," + totalAmountReimbursed + ")";
            Boolean isInserted = Folder.save(query);
            if (isInserted)
                System.out.println("Folder added successfully");
            else
                System.out.println("Folder not added");
            System.out.println("Do you want to add medicaments? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equals("n")) {
                // ask to save the folder
                System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhh");


            }
            if (answer.equals("y")) {
                // add medicaments
                System.out.println("How many medicaments do you want to add?");
                int numberOfMedicaments = scanner.nextInt();
                Double totalAmountOfMedicaments = 0.0;
                Double totalAmountOfMedicamentsReimbursed = 0.0;
                    while (numberOfMedicaments > 0) {
                        System.out.println("Enter the medicament code");
                        String medicamentCode = scanner.next();
                        System.out.println("Medicament code: " + medicamentCode);
                        Medicament medicament = new Medicament();
                        Double medicamentPrice = medicament.getMedicamentByField(medicamentCode, "medicament_price");
                        System.out.println("Medicament Price: " + medicamentPrice);
                        Double medicamentRate = medicament.getMedicamentByField(medicamentCode, "medicament_rate");
                        medicament.addMedicament(folderCode, medicamentCode);
                        totalAmountOfMedicaments += medicamentPrice;
                        totalAmountOfMedicamentsReimbursed += medicamentPrice * (medicamentRate / 100);

                        numberOfMedicaments--;
                    }

                totalAmountReimbursed +=  totalAmountOfMedicamentsReimbursed;
               // update
                Boolean isUpdated = updateTotalAmountReimbursed(folderCode);
                if (isUpdated)
                    System.out.println("Folder updated successfully");
                else
                    System.out.println("Folder not updated");
                // ask to if hava analysis
                System.out.println("Do you have analysis? (y/n)");
                String answerAnalysis = scanner.next();
                if (answerAnalysis.equals("y")) {
                    // add analysis
                    analyseOpration(folderCode, scanner);
                    // add radio
                    radioOpration(folderCode);
                }
                else{
                    radioOpration(folderCode);
                }


            }
        }


    }

    private static void analyseOpration(String folderCode, Scanner scanner) {
        System.out.println("Enter the total amount of analysis");
        Double totalAmountOfAnalysis = scanner.nextDouble();
        System.out.println("Total Amount of Analysis: " + totalAmountOfAnalysis);

        Double totalAmountOfAnalysisReimbursed = Analysis.getAmountReimbursed(totalAmountOfAnalysis);
        System.out.println("Total Amount of Analysis Reimbursed: " + totalAmountOfAnalysisReimbursed);
        totalAmountReimbursed += totalAmountOfAnalysisReimbursed;
        // update
        Boolean isUpdatedAnalysis = updateTotalAmountReimbursed(folderCode);
        if (isUpdatedAnalysis)
            System.out.println("Folder updated successfully");
        else
            System.out.println("Folder not updated");
    }

    private static Boolean updateTotalAmountReimbursed(String folderCode) {
        String queryUpdate = "update FolderPatient set TotalAmountReimbursement = " + totalAmountReimbursed + " where FolderNumber = '" + folderCode + "'";
        Boolean isUpdated = Folder.update(queryUpdate);
        return isUpdated;
    }

    // getTotalAmountOfFolder
    public static int getTotalAmountOfFolder() {
        int totalAmountOfFolder = 0;
        System.out.println("Enter the total amount of folder: ");
        Scanner scanner = new Scanner(System.in);
        try {
             totalAmountOfFolder = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input");
            getTotalAmountOfFolder();
        }
        return totalAmountOfFolder;
    }
    public static  String genrateFolderCode() {
        String folderCode = "FOLDER-";
        String random = String.valueOf((int) (Math.random() * 1000000));
        folderCode += random;
        return folderCode;
    }

    public static String getPatientMatricNo() {
        String patientMatricNo = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the patient matric number: ");
        patientMatricNo = scanner.nextLine();
        return patientMatricNo;
    }

    public static String getFolderCode() {
        String folderCode = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the folder code: ");
        folderCode = scanner.nextLine();
        return folderCode;
    }

    public static String getTypeOfArrangement() {
       //  (Geniral, Specialiste, Dentiste)
        String typeOfArrangement = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type of arrangement: ");
        System.out.println("1. General");
        System.out.println("2. Specialiste");
        System.out.println("3. Dentiste");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                typeOfArrangement = "General";
                break;
            case 2:
                typeOfArrangement = "Specialiste";
                break;
            case 3:
                typeOfArrangement = "Dentiste";
                break;
        }
        return typeOfArrangement;
    }


    public static void wait30Seconds() {
        try {
            System.out.println("Wait 30 seconds");
            Thread.sleep(30000);
            tryCount = 0;
            addNewFolder();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // getPriceOfArrangement
    public static Double getPriceOfArrangement(String typeOfArrangement) {
        Double priceOfArrangement = 0.0;
        switch (typeOfArrangement) {
            case "General":
                priceOfArrangement = 100.0;
                break;
            case "Specialiste":
                priceOfArrangement = 200.0;
                break;
            case "Dentiste":
                priceOfArrangement = 300.0;
                break;
        }
        return priceOfArrangement;
    }
    // ask getTotalAmountArrangement
    public static int getTotalAmountArrangement() {
        int totalAmountArrangement = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the total amount of the arrangement: ");
        totalAmountArrangement = scanner.nextInt();
        return totalAmountArrangement;

    }


    private static Boolean save(String query)
    {
        try {
            Query.insert(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static String checkFolderExist(String folderCode,String choice)
    {
        String query = "select * from FolderPatient where FolderNumber = '" + folderCode + "'";
        Boolean isExist;
        try {
            ResultSet resultSet = Query.select(query);
            isExist = resultSet.next();
            if (isExist) {
                System.out.println("Folder found");
                return resultSet.getString(choice);
            } else {
                System.out.println("Folder code not exist");
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Boolean validate(String query)
    {
        try {
            ResultSet resultSet = Query.select(query);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void validateFolder()
    {
        String folderCode = getFolderCode();
        String status = checkFolderExist(folderCode,"FolderStatus");
        if (!status.isEmpty()) {
            System.out.println("Folder status: " + status);
            // ask the if want update the status
            System.out.println("Do you want to update the status? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.equals("y")) {

                String newStatusString = getNewStatus();
                String query = "update FolderPatient set FolderStatus = '" + newStatusString + "' where FolderNumber = '" + folderCode + "'";
                Boolean isUpdated = update(query);
                if (isUpdated) {
                    System.out.println("Folder status updated successfully");
                    // ask to notify the patient
                    System.out.println("Do you want to notify the patient? (y/n)");
                    String choice2 = scanner.nextLine();
                    if (choice2.equals("y")) {
                        notifyPatient(folderCode, newStatusString);
                    }

                } else {
                    System.out.println("Folder status not updated");
                }
            } else {
                System.out.println("Folder status not updated");
            }
        } else {
            System.out.println("Folder not found");
            validateFolder();
        }
        System.out.println("Details of the folder");
        System.out.println("Folder code: " + folderCode);
        System.out.println("Total amount Reimbursed: " + checkFolderExist(folderCode,"TotalAmountReimbursed"));

    }

    private static void notifyPatient(String folderCode, String newStatusString) {
        String patientMatricNo = checkFolderExist(folderCode,"PatientMatricNo");
        String patientEmail = Patient.getPatientByField(patientMatricNo,"PatientEmail");
        if (!patientEmail.isEmpty()) {
            String subject = "Folder status updated";
            String message = "Your folder status has been updated to " + newStatusString;
            Mail.sendMail(patientEmail,subject,message);
            System.out.println("Email sent successfully");
        } else {
            System.out.println("Patient email not found");
        }
    }

    public static String getNewStatus() {
        String newStatus = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new status: ");
        System.out.println("1. En cours");
        System.out.println("2. Non remboursé");
        System.out.println("3. Reimbursed");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                newStatus = "En cours";
                break;
            case 2:
                newStatus = "Non remboursé";
                break;
            case 3:
                newStatus = "Reimbursed";
                break;
        }
        return newStatus;
    }

    public static Boolean update(String query)
    {
        try {
            Query.update(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //totalAmountOfMedicaments

    //Patient Ops

    public static void DislayPatientFolders(int ID) throws SQLException {
        String query ="SELECT * FROM FolderPatient WHERE PatientID =  "+ID;
        ResultSet resultSet = Query.select(query);

        while (resultSet.next()){
            DisplayData(resultSet);
        }
    }

    public static void DisplaySingleFolder(int ID) throws SQLException {
        String FolderID = getFolderCode();
        String query ="SELECT * FROM FolderPatient WHERE PatientID =  "+ID+" AND FolderNumber = '"+FolderID+"'";
        ResultSet resultSet = Query.select(query);
        if(resultSet.next()) DisplayData(resultSet);
        else System.out.println("Folder not found");

    }

    private static void DisplayData(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getString("FolderNumber"));
        System.out.println(resultSet.getString("CreatedAt"));
        System.out.println(resultSet.getString("FolderStatus"));
    }

    public static void radioOpration(String folderCode)
    {
        // ask if have radio
        System.out.println("Do you have radio? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        // add radio
        System.out.println("Enter the total amount of radio");
        Double totalAmountOfRadio = scanner.nextDouble();
        System.out.println("Total Amount of Radio: " + totalAmountOfRadio);

        Double totalAmountOfRadioReimbursed = Radio.getAmountReimbursed(totalAmountOfRadio);
        System.out.println("Total Amount of Radio Reimbursed: " + totalAmountOfRadioReimbursed);
        totalAmountReimbursed += totalAmountOfRadioReimbursed;
        // update
        Boolean isUpdatedRadio = updateTotalAmountReimbursed(folderCode);
        if (isUpdatedRadio)
            System.out.println("Folder updated successfully");
        else
            System.out.println("Folder not updated");

    }

}
