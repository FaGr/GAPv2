/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.jsf_jpa_war;

/**
 *
 * @author valeriotanferna
 */
public class CsvWriteReadTest {

	/**
	 * @param args
	 */
	public void prova() {
		
		String fileName = System.getProperty("user.home")+"/aule.csv";
		
		//System.out.println("Write CSV file:");
		CsvFileWriter.writeCsvFile(fileName);
		
		//System.out.println("\nRead CSV file:");
		CsvFileReader.readCsvFile(fileName);

	}

}
