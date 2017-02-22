/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.jsf_jpa_war;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author valeriotanferna
 */

public class CsvFileWriter {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "id,aula, posti, occupata, prof, mail";

	public static void writeCsvFile(String fileName) {
		
		//Create new aula objects
		Aula a11 = new Aula(1, "a11", 43, false, false, null);
		Aula a12 = new Aula(1, "a12", 106, false, false, null);
                
                Aula a13 = new Aula(1, "a13", 108, false, false, null);
                Aula a21 = new Aula(1, "a21", 142, false, false, null);
                
                Aula a22 = new Aula(1, "a22", 99, false, false, null);
                Aula a23 = new Aula(1, "a23", 48, false, false, null);
                
                Aula a24 = new Aula(1, "a24", 48, false, false, null);
                Aula a25 = new Aula(1, "a25", 88, false, false, null);
                
                Aula a26 = new Aula(1, "a26", 148, false, false, null);
                Aula a28 = new Aula(1, "a28", 148, false, false, null);
                
                
                
		
		//Create a new list of aula objects
		List<Aula> aula = new ArrayList<Aula>();
		aula.add(a11);
		aula.add(a12);
                aula.add(a13);
                aula.add(a21);
                aula.add(a22);
                aula.add(a23);
                aula.add(a24);
                aula.add(a25);
                aula.add(a26);
                aula.add(a28);
		
		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new aula object list to the CSV file
			for (Aula aula_i : aula) {
				fileWriter.append(String.valueOf(aula_i.getId()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(aula_i.getNomeaula());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(aula_i.getPosti()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(aula_i.getOccupata()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(aula_i.getProf()));
                                fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(aula_i.getMail());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
}

