package com.ibc.training.main;

import java.util.Scanner;

import com.ibc.training.entity.Address;
import com.ibc.training.entity.Doctor;
import com.ibc.training.entity.Hospital;
import com.ibc.training.entity.Patient;
import com.ibc.training.service.DoctorService;
import com.ibc.training.service.DoctorServiceImpl;
import com.ibc.training.service.HospitalService;
import com.ibc.training.service.HospitalServiceImpl;
import com.ibc.training.service.PatientService;
import com.ibc.training.service.PatientServiceImpl;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		HospitalService hospitalService = new HospitalServiceImpl();

		DoctorService doctorService = new DoctorServiceImpl();

		PatientService patientService = new PatientServiceImpl();

		boolean running = true;
		while (running) {
			System.out.println("\n==================================");
			System.out.println("HOSPITAL MANGEMENT SYSTEM MENU");
			System.out.println("==================================");
			System.out.println("1.Add Hospital");
			System.out.println("2.View Hospital By Id");
			System.out.println("3.View All Hospitals");
			System.out.println("4.Add Doctor");
			System.out.println("5.View Doctor by Id");
			System.out.println("6.View All Doctors");
			System.out.println("7.Register Patient with Address");
			System.out.println("8.View Patient by Id");
			System.out.println("9.View all Patients");
			System.out.println("10.Delete Patient by Id");
			System.out.println("11.Exit");
			System.out.println("Choose an option from(1-11):");

			int choice = -1;
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				scanner.nextLine();

			} else {
				scanner.nextLine();
				System.out.println("Inavlid input.please choose a no btw 1-11");
				continue;
			}

			try {
				switch (choice) {
				case 1:
					System.out.println("Enter Hospital Name:");
					String hName = scanner.nextLine();
					Hospital hospital = new Hospital();
					hospital.setName(hName);
					hospitalService.addHospital(hospital);
					System.out.println("Hospital added successfully");
					break;

				case 2:
					System.out.println("Enter Hospital id");
					Long hId = scanner.nextLong();
					scanner.nextLine();
					Hospital foundH = hospitalService.getHospital(hId);
					if (foundH != null) {
						System.out.println(foundH);
					} else {
						System.out.println("Hospital not found");
					}
					break;

				case 3:
					System.out.println("\n--All Hospitals--");
					for (Hospital h : hospitalService.getAllHospitals()) {
						System.out.println(h);
					}
					break;

				case 4:
					System.out.println("Enter Doctor Name:");
					String dName = scanner.nextLine();
					System.out.println("Enter specializtion");
					String spec = scanner.nextLine();
					System.out.println("Enter Hospital Id to associate with this doctor/if none: ");
					Long assocHId = scanner.nextLong();
					scanner.nextLine();
					Doctor doctor = new Doctor();

					doctor.setName(dName);
					doctor.setSpecialization(spec);
					if (assocHId > 0) {
						Hospital hRef = hospitalService.getHospital(assocHId);
						if (hRef != null) {
							doctor.setHospital(hRef);

						} else {
							System.out.println("Hospital Od not found");
						}
					}
					doctorService.addDoctor(doctor);
					System.out.println("Doctor added scuccessfully");
					break;

				case 5:
					System.out.println("Enter DOctor Id:");
					Long dId = scanner.nextLong();
					scanner.nextLine();
					Doctor foundD = doctorService.getDoctor(dId);
					if (foundD != null) {
						System.out.println(foundD);
					} else {
						System.out.println("Doctor not found");
					}
					break;

				case 6:
					System.out.println("\n--All doctors--");
					for (Doctor d : doctorService.getAllDoctors()) {
						System.out.println(d);
					}
					break;
				case 7:
					System.out.println("Enter Patient name:");
					String pName = scanner.nextLine();
					System.out.println("Enter Address city:");
					String city = scanner.nextLine();
					System.out.println("Enter address state:");
					String state = scanner.nextLine();
					Address address = new Address();
					address.setCity(city);
					address.setState(state);
					Patient patient = new Patient();
					patient.setName(pName);
					patient.setAddress(address);
					patientService.registerPatient(patient);
					System.out.println("Patient registered successfully");

					break;

				case 8:
					System.out.println("Enter patient id:");
					Long pId = scanner.nextLong();
					scanner.nextLine();
					Patient foundP = patientService.getPatient(pId);
					if (foundP != null) {
						System.out.println(foundP);

					} else {
						System.out.println("patient not found");
					}
					break;

				case 9:
					System.out.println("\n--All patients--");
					for (Patient p : patientService.getAllPatients()) {
						System.out.println(p);

					}
					break;

				case 10:
					System.out.println("Enter Patient Id to delete:");
					Long delId = scanner.nextLong();
					scanner.nextLine();
					patientService.deletePatient(delId);

					break;

				case 11:
					running = false;

					System.out.println("Existing application");

					break;

				default:

					System.out.println("Invalid choice Plz pick btw 1-11");
				}
			} catch (Exception e) {
				System.out.println("Error:" + e.getMessage());
				e.printStackTrace();
			}

		}
		scanner.close();
	}

}
