/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.jsf_jpa_war;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author valeriotanferna
 */

public class CsvFileReader {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	
	//Aula attributes index
	private static final int AULA_ID_IDX = 0;
	private static final int AULA_FNAME_IDX = 1;
	private static final int AULA_POSTI_IDX = 2;
	private static final int AULA_OCCUPATA = 3; 
	private static final int AULA_PROF = 4;
        private static final int AULA_MAIL = 5;
	public static Aula aulatmp;
        
	public static void readCsvFile(String fileName) {

		BufferedReader fileReader = null;
     
        try {
        	
        	//Create a new list of aula to be filled by CSV file data 
        	List<Aula> aula = new ArrayList<Aula>();
        	
            String line = "";
            
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            
            //Read the CSV file header to skip it
            fileReader.readLine();
            
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                	//Create a new aula object and fill his  data
                    aulatmp = new Aula(Long.parseLong(tokens[AULA_ID_IDX]), 
                            tokens[AULA_FNAME_IDX], 
                            Integer.parseInt(tokens[AULA_POSTI_IDX]), 
                            Boolean.valueOf(tokens[AULA_OCCUPATA]), 
                            Boolean.valueOf(tokens[AULA_PROF]), 
                            tokens[AULA_MAIL]);
                    aula.add(aulatmp);
                    }
            }
            
            //Print the new aula list
            for (Aula aula_i : aula) {
				System.out.println(aula_i.toString());
			}
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

	}

}

