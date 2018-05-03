package by.it_academy.InputPatients;

import by.it_academy.Patient.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputPatient implements InputPatient {
    private String firstName;
    private String lastName;
    private String birthday;
    private boolean healthy;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private HashSet<Patient> listOfPatient = new HashSet<>();
    private Patient patient;

    @Override
    public Patient addPatient() {
        try {
            System.out.println("Введите данные пациента:");
            Scanner scanner = new Scanner(System.in);
            firstName = scanner.nextLine();
            lastName = scanner.nextLine();
            birthday = scanner.nextLine();
            healthy = scanner.nextBoolean();
            Date date = sdf.parse(birthday);
            return new Patient(firstName, lastName, date, healthy);
        } catch (ParseException e) {
            System.out.println("Неверная дата рождения!!! Попробуйте снова!");
            return addPatient();
        }
        catch (InputMismatchException e){
            System.out.println("Неверное состояние здоровья");
            return addPatient();
        }
    }

    public HashSet<Patient> getListOfPatient() {
        patient = addPatient();
        listOfPatient.remove(patient);
        listOfPatient.add(patient);
        return listOfPatient;
    }
}
