package com.ibc.training.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Doctor {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String specialization;

	@ManyToMany
	@JoinTable(name = "doctor_patient", joinColumns = @JoinColumn
	(name = "doctor_id"), inverseJoinColumns = @JoinColumn
	(name = "patient_id"))
	
	private Set<Patient> patients = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	
	private Hospital hospital;

	public Doctor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialization=" + specialization +  "]";
	}

}
