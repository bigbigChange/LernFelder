/**
 * @author dariush
 *
 */
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

public class CSVHandler {

	private String file = 			"/Users/dariush/studentNamesCSV.csv";
	private String delimiter = 		";";
	private String line = "";

	// Constructor 1
	public CSVHandler() {
	}

	// Constructor 2
	public CSVHandler(String delimiter, String file) {
		this.delimiter = delimiter;
		this.file = file;
	}

	
	// Begin Methods

	public List<Schueler> getAll() {

		Schueler s = null;
		List<Schueler> students = new ArrayList<Schueler>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while ((line = br.readLine()) != null) {
				String[] student = line.split(delimiter);
				s = new Schueler(student[1] + " " + student[0], 0, 0, 0);
				students.add(s);
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}

	public void printAll(List<Schueler> students) {
		for (Schueler s : students) {
			System.out.println(s.getName());
		}
	}
}