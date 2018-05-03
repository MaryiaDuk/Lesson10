package by.it_academy;

import by.it_academy.InputPatients.ConsoleInputPatient;
import by.it_academy.OfflineBase.OfflinePatientBase;
import by.it_academy.OnlineBase.OnlinePatientBase;
import by.it_academy.Patient.Patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private HashSet<Patient> list = new HashSet<>();
    private OfflinePatientBase offlinePatientBase = new OfflinePatientBase();
    private OnlinePatientBase onlinePatientBase = new OnlinePatientBase();
    private ConsoleInputPatient consoleInputPatient = new ConsoleInputPatient();
    private CurrentBase currentBase = new CurrentBase();
    private HashSet<Patient> listP = new HashSet<>();


    private int skin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.  Локальная база\n" + "2. Онлайн база\n" + "3. Общая база\n"
                + "4. Записать пациента\n" + "5. Выход");
        return scanner.nextInt();
    }

    public void showMenu() {
        boolean exit = true;
        while (exit) {
            switch (skin()) {
                case 1:
                    showLocalBase();
                    break;
                case 2:
                    showRemoteBase();
                    break;
                case 3:
                    showCommonBase();
                    break;
                case 4:
                    inputAndSavePatient();
                    break;
                case 5:
                    exit = false;
                    break;
            }
        }
    }

    private void inputAndSavePatient() {
        list.addAll(offlinePatientBase.readData());
        Patient patient = consoleInputPatient.addPatient();
        listP.add(patient);
        testUnique();
        offlinePatientBase.writeData(list);
        System.out.println("Пациент сохранен!");
        list.clear();
    }

    private void showRemoteBase() {
        HashSet<Patient> onlineList;
        onlineList = onlinePatientBase.getOnlineBase();
        for (Patient patient : onlineList) {
            System.out.println(patient.toString());
        }
        onlineList.clear();
    }

    private void showLocalBase() {
        HashSet<Patient> localList;
        localList = offlinePatientBase.readData();
        localList.clear();
        localList = offlinePatientBase.readData();
        for (Patient patient : localList) {
            System.out.println(patient.toString());
        }
        localList.clear();
    }

    private void showCommonBase() {
        HashSet<Patient> currentList;
        currentList = currentBase.getCurrentBase();
        for (Patient patient : currentList) {
            System.out.println(patient.toString());
        }
        currentList.clear();
    }

    private void uniquePatients(List<Patient> patient1, List<Patient> patient2) {
        for (int i = 0; i < patient1.size(); i++)
            for (int j = 0; j < patient2.size(); j++)
                if (patient1.get(i).getFirstName().equals(patient2.get(j).getFirstName()) &&
                        patient1.get(i).getLastName().equals(patient2.get(j).getLastName())) {
                    patient1.remove(i);
                }
    }

    private void testUnique(){
        List<Patient> list1 = new ArrayList<>();
        List<Patient> listP1 = new ArrayList<>();
        listP1.addAll(listP);
        list1.addAll(list);
        uniquePatients(list1, listP1);
        list.clear();
        list1.addAll(listP1);
        list.addAll(list1);
    }
}