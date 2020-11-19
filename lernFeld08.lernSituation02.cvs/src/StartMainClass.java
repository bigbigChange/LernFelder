/**
 * @author dariush
 *
 */
import java.util.List;

public class StartMainClass {

	public static void main(String[] args) {
		CSVHandler csv = new CSVHandler();
		XMLHandler xml = new XMLHandler();
		
		List<Schueler> students = csv.getAll();
		xml.makeNewDoc(students); 
		xml.printAll();
	}
}
