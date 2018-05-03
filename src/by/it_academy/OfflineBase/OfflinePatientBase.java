package by.it_academy.OfflineBase;

import by.it_academy.Patient.Patient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class OfflinePatientBase implements OfflineBase{
    private HashSet<Patient> list = new HashSet<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void writeData(HashSet<Patient> listOfPatient) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element hospital = doc.createElement("hospital");
            doc.appendChild(hospital);
            for (Patient x : listOfPatient) {
                Element patient = doc.createElement("patient");
                Element firstName = doc.createElement("firstName");
                Element secondName = doc.createElement("secondName");
                Element birthday = doc.createElement("birthday");
                Element healthy = doc.createElement("healthy");
                firstName.setTextContent(x.getFirstName());
                secondName.setTextContent(x.getLastName());
                birthday.setTextContent(sdf.format(x.getBirthday()));
                healthy.setTextContent(String.valueOf(x.isHealthy()));
                hospital.appendChild(patient);
                patient.appendChild(firstName);
                patient.appendChild(secondName);
                patient.appendChild(birthday);
                patient.appendChild(healthy);            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("OfflineBase.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public HashSet<Patient> readData() {
        String name = null, secondName = null, birthday = null, stateOfHealth = null;
        try {
            File file = new File("OfflineBase.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            Node hospital = document.getDocumentElement();
            NodeList patients = hospital.getChildNodes();
            for (int i = 0; i < patients.getLength(); i++) {
                Node patient = patients.item(i);
                if (patient.getNodeType() != Node.TEXT_NODE) {
                    NodeList patientProps = patient.getChildNodes();
                    for (int j = 0; j < patientProps.getLength(); j++) {
                        Node item = patientProps.item(j);
                        if (item.getNodeType() != Node.TEXT_NODE) {
                            if (item.getNodeName().equals("firstName"))
                                name = item.getChildNodes().item(0).getTextContent();
                            else if (item.getNodeName().equals("secondName"))
                                secondName = item.getChildNodes().item(0).getTextContent();
                            else if (item.getNodeName().equals("birthday"))
                                birthday = item.getChildNodes().item(0).getTextContent();
                            else if (item.getNodeName().equals("healthy"))
                                stateOfHealth = item.getChildNodes().item(0).getTextContent();
                        }
                    }
                    list.add(new Patient(name, secondName, convertDate(birthday), Boolean.valueOf(stateOfHealth)));
                }
            }
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Date convertDate(String birthday) {
        try {
            return sdf.parse(birthday);
        } catch (ParseException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
