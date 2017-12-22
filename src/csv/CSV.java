/**
 * 
 */
package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Subnet;

/**
 * @author Adrien
 *
 */
public class CSV {
	
	
	/**
	 * Exports an ArrayList to a csv file.
	 * @param headers : table headers
	 * @param subnets : subnet / data to fill in the table
	 * @param filepath : path to the destination file
	 */
	public static void writeToCSV(String[] headers, ArrayList<Subnet> subnets, String filepath) {
		
		if (!filepath.endsWith(".csv")) {
			filepath += ".csv";
		}
		
		try {
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"",
						headers[0],
						headers[1],
						headers[2],
						headers[3],
						headers[4]
					));
			
			for (Subnet sub: subnets) {
				pw.println(String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
							sub.getAddr(),
							sub.getFirstIpHost(),
							sub.getLastIpHost(),
							sub.getBroadcast(),
							sub.getMask()
						));
			}
			
			pw.close();
			bw.close();
			fw.close();
			
		} catch (Exception e) {
			
		}
		
	}
	
}
