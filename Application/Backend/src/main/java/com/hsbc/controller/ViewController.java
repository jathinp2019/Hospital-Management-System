package com.hsbc.controller;

import com.hsbc.beans.*;
import com.hsbc.exception.ProfileNotFoundException;
import com.hsbc.service.*;
import com.hsbc.util.ServiceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ViewController {
    private UserService userService;
    private DoctorService doctorService;
    private PatientService patientService;
    private AppointmentService appointmentService;

    public ViewController() {
        userService = ServiceFactory.getUserService();
        doctorService = ServiceFactory.getDoctorService();
        patientService = ServiceFactory.getPatientService();
        appointmentService = ServiceFactory.getAppointmentService();
    }

    // Main method to start the application
    public static void main(String[] args) {
        ViewController viewController = new ViewController();
        viewController.run();
    }

    // Main application loop
    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("1.Existing User");
        System.out.println("2.Add Patient");
        int option=scanner.nextInt();
        // Simulate login
        switch(option){
        case 1:
        try{
        scanner.nextLine();
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        User user = userService.authenticate(username, password);
        if (user != null) {
            System.out.println("Login successful. Role: " + user.getRole());
            handleUserRole(user);
        } else {
            System.out.println("Invalid credentials");
        }
        }
        catch(ProfileNotFoundException e){
            e.printStackTrace();
        }
        break;
        case 2:
        scanner.nextLine();
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter contact number:");
        String contactnumber=scanner.nextLine();
        System.out.println("Enter dob: ");
        String date = scanner.nextLine();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                Date dob;
                try {
                    dob = sdf.parse(date);
                    Patient patient=new Patient();
                    patient.setName(name);
                    patient.setDob(dob);
                    patientService.addPatient(patient);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        break;
        }
    }

    // Handle functionalities based on user role
    private void handleUserRole(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu ---");
            if ("admin".equals(user.getRole())) {
                System.out.println("1. Show All Doctors");
                System.out.println("2. Show All Patients");
                System.out.println("3. Cancel Appointment");
                System.out.println("4. Import Doctors");
                System.out.println("5. Add New User");
            } else if ("doctor".equals(user.getRole())) {
                System.out.println("1. View Appointments");
                System.out.println("2. Add Schedule");
                System.out.println("3. Suggest Medical Tests");
                System.out.println("4. Suggest Medicines");
            } else if ("patient".equals(user.getRole())) {
                System.out.println("1. Book Appointment");
                System.out.println("2. View Doctor Appointments");
            }
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    if ("admin".equals(user.getRole())) {
                        showAllDoctors();
                    } else if ("doctor".equals(user.getRole())) {
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = scanner.nextInt();
                        viewDoctorAppointments(doctorId);
                    } else if ("patient".equals(user.getRole())) {
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        System.out.println("Enter Doctor ID: ");
                        int doctorid=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Appointment Date: ");
                        String str=scanner.nextLine();
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        Date date;
                        try {
                            date = sdf.parse(str);
                            Appointment appointment = new Appointment(); // Assume user inputs are set here
                            appointment.setPatientId(patientId);
                            appointment.setDoctorId(doctorid);
                            appointment.setAppointmentDate(date);

                            bookAppointment(appointment);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    if ("admin".equals(user.getRole())) {
                        showAllPatients();
                    } else if ("doctor".equals(user.getRole())) {
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = scanner.nextInt();
                        System.out.print("Enter Available Date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        Date availabledate;
                        try {
                            availabledate = sdf.parse(date);
                            Schedule schedule = new Schedule(); // Assume user inputs are set here
                            schedule.setDoctorId(doctorId);
                            schedule.setAvailableDate(availabledate);
                            addSchedule(schedule);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }  
                    }
                    break;
                case 3:
                    if ("admin".equals(user.getRole())) {
                        System.out.print("Enter Appointment ID to cancel: ");
                        int appointmentId = scanner.nextInt();
                        cancelAppointment(appointmentId);
                    } else if ("doctor".equals(user.getRole())) {
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        System.out.print("Enter Suggested Tests: ");
                        String tests = scanner.nextLine();
                        suggestMedicalTests(patientId, tests);
                    }
                    break;
                case 4:
                    if ("admin".equals(user.getRole())) {
                        // Import doctors functionality is not yet implemented
                        System.out.println("Import Doctors functionality is not implemented yet.");
                    } else if ("doctor".equals(user.getRole())) {
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        System.out.print("Enter Suggested Medicines: ");
                        String medicines = scanner.nextLine();
                        suggestMedicines(patientId, medicines);
                    }
                    break;
                case 5:
                    if("admin".equals(user.getRole())){ //as only admins can add new users 
                        scanner.nextLine();
                        System.out.println("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.println("Enter user password: ");
                        String password = scanner.nextLine();
                        System.out.println("Enter user Role:");
                        String role=scanner.nextLine();
                        User user1=new User();
                        user.setUsername(username);
                        user.setPassword(password);
                        
                        userService.addUser(user1);
                        break;
                    }
                case 0:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Admin Functionalities
    public void showAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        doctors.forEach(System.out::println);
    }

    public void cancelAppointment(int appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
    }

    public void showAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        patients.forEach(System.out::println);
    }

    // User Functionalities
    public void addPatient(Patient patient) {
        patientService.addPatient(patient);
    }

    public void bookAppointment(Appointment appointment) {
        appointmentService.bookAppointment(appointment);
    }

    public void viewDoctorAppointments(int doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        appointments.forEach(System.out::println);
    }

    // Doctor Functionalities
    public void addSchedule(Schedule schedule) {
        doctorService.addSchedule(schedule);
    }

    public void suggestMedicalTests(int patientId, String tests) {
        System.out.println("Suggesting tests: " + tests + " for patient ID: " + patientId);
    }

    public void suggestMedicines(int patientId, String medicines) {
        System.out.println("Suggesting medicines: " + medicines + " for patient ID: " + patientId);
    }
}
