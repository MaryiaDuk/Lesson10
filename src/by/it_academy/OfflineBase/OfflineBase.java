package by.it_academy.OfflineBase;

import by.it_academy.Patient.Patient;

import java.util.HashSet;

/**
 * Created by masha on 02.05.2018.
 */
public interface OfflineBase {
    void writeData(HashSet<Patient> listOfPatient);

    HashSet<Patient> readData();
}
