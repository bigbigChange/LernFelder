/**
 * @author dariush
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLHandler {

	private String path = "/Users/dariush/Downloads/Quiz/lf8Quiz/src/players.xml";

	private File file;

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document doc;

	private TransformerFactory transformerFactory;
	private Transformer transformer;

	// private StreamResult streamResultConsole; // (needed for makeNewDoc() method) 
	private StreamResult streamResultFile;

	private DOMSource domSource;

	// Constructor
	public XMLHandler() {

		file = new File(path);

		try {

			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	// Constructor 2
	public XMLHandler(String path) {
		this.path = path;
	}

	
	// Begin Methods
	
	public void printAll() {

		try {

			doc = builder.parse(file);

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("player");
		

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			System.out.println("\n\nCurrent Element: " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				// String name = elem.getAttribute("name");

				Node node1 = elem.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("joker").item(0);
				String joker = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("blamiert").item(0);
				String blamiert = node3.getTextContent();

				Node node4 = elem.getElementsByTagName("fragen").item(0);
				String fragen = node4.getTextContent();

				System.out.printf("\n Name: %S   \n\n", name);
				System.out.printf("| Joker:    |%5s   |\n", joker);
				System.out.printf("| Blamiert: |%5s   |\n", blamiert);
				System.out.printf("| Fragen:   |%5s   |\n\n", fragen);
				
			}
		}
	}


	public Document makeNewDoc(List<Schueler> players) {

		String 		name;
		int 		joker, 
					blamiert, 
					fragen;

		doc = builder.newDocument();
		
		Element root = doc.createElementNS("LF8_LS2", "players");
		doc.appendChild(root);

		for (Schueler s : players) {

			name 		= s.getName();
			joker 		= s.getJoker();
			blamiert 	= s.getBlamiert();
			fragen 		= s.getFragen();

			root.appendChild(createStudent(doc, name, joker, blamiert, fragen));
		}
		try {
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			domSource = new DOMSource(doc);

			// streamResultConsole = new StreamResult(System.out); // uncomment in attributes
			// transf.transform(domSource, streamResultConsole);

			streamResultFile = new StreamResult(file);
			transformer.transform(domSource, streamResultFile);

		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return doc;

	} // End makeNewDoc()
	
	

	private static Node createStudent(Document doc, String name, int joker, int blamiert, int fragen) {

		Element player = doc.createElement("player");

		player.setAttribute("name", name);
		player.appendChild(createStudentElement(doc, "name", name));
		player.appendChild(createStudentElement(doc, "joker", 		intToString(joker)));
		player.appendChild(createStudentElement(doc, "blamiert", 	intToString(blamiert)));
		player.appendChild(createStudentElement(doc, "fragen", 		intToString(fragen)));

		return player;
	}

	private static Node createStudentElement(Document doc, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));

		return node;
	}

	private static String intToString(int value) {

		String s = String.valueOf(value);

		return s;
	}

}
