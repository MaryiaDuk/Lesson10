package by.it_academy;

import by.it_academy.OfflineBase.OfflinePatientBase;
import by.it_academy.OnlineBase.OnlinePatientBase;
import by.it_academy.Patient.Patient;

import java.util.HashSet;


public class CurrentBase {
    private HashSet<Patient> list;

    public HashSet<Patient> getCurrentBase(){
        OnlinePatientBase onlinePatientBase = new OnlinePatientBase();
        OfflinePatientBase offlinePatientBase = new OfflinePatientBase();
        list = offlinePatientBase.readData();
        list.addAll(onlinePatientBase.getOnlineBase());
        return list;
    }
}