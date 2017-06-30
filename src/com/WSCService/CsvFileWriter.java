package com.WSCService;

/**
 * Created by alee on 6/29/17.
 */
import com.sforce.soap.partner.sobject.*;

import java.io.FileWriter;
import java.io.IOException;

class CsvFileWriter {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "id,Name";
    private static boolean isHeaderSet = false;

    static void writeCsvFile(SObject[] records) {

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("./test7.csv",true);

            //Write the CSV file header
            if(!isHeaderSet) {
                fileWriter.append(FILE_HEADER);

                //Add a new line separator after the header
                fileWriter.append(NEW_LINE_SEPARATOR);
                isHeaderSet = true;
            }
            //Write a new student object list to the CSV file
            for (SObject account : records) {
                fileWriter.append(String.valueOf(account.getField("Id")));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append( "\"" + String.valueOf(account.getField("Name"))+ "\"");
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }
}
