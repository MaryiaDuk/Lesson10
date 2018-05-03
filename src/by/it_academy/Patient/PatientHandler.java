package by.it_academy.Patient;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class PatientHandler extends DefaultHandler {
    private HashSet<Patient> listOfPatient = new HashSet<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String firstName = null;
    private String secondName = null;
    private String birthday = null;
    private String healthy = null;
    private boolean fName = false;
    private boolean sName = false;
    private boolean b = false;
    private boolean st = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("firstName")) {
            fName = true;
        } else if (qName.equalsIgnoreCase("secondName")) {
            sName = true;
        } else if (qName.equalsIgnoreCase("birthday")) {
            b = true;
        } else if (qName.equalsIgnoreCase("healthy")) {
            st = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (fName) {
            firstName = new String(ch, start, length);
            fName = false;
        } else if (sName) {
            secondName = new String(ch, start, length);
            sName = false;
        } else if (b) {
            birthday = new String(ch, start, length);
            b = false;
        } else if (st) {
            healthy = new String(ch, start, length);
            st = false;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("patient")) {
            listOfPatient.add(new Patient(firstName, secondName, convertDate(birthday), Boolean.valueOf(healthy)));
        }
    }

    private Date convertDate(String birthday) {
        try {
            Date date;
            return date = sdf.parse(birthday);
        } catch (ParseException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public HashSet<Patient> getListOfPatient() {
        return listOfPatient;
    }
}
