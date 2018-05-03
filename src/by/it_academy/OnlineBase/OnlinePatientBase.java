package by.it_academy.OnlineBase;

import by.it_academy.Patient.Patient;
import by.it_academy.Patient.PatientHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class OnlinePatientBase implements OnlineBase{
    private HashSet<Patient> listOfPatient;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public HashSet<Patient> getOnlineBase() {
        try {
            PatientHandler ph = new PatientHandler();
            XMLReader myReader = XMLReaderFactory.createXMLReader();
            myReader.setContentHandler(ph);
            myReader.parse(new InputSource(new URL("https://raw.githubusercontent.com/MaryiaDuk/OnlineBase/master/online.xml").openStream()));
            listOfPatient = ph.getListOfPatient();
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return listOfPatient;
    }
}
