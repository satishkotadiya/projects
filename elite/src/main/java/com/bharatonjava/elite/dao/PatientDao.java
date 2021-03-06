package com.bharatonjava.elite.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharatonjava.elite.domain.Patient;

@Repository
public class PatientDao implements IPatientDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Patient getPatientById(Long patientId) {
		Patient patient = null;
		Session session = this.sessionFactory.getCurrentSession();
		patient = (Patient) session.get(Patient.class, patientId); 
		return patient;
	}

	@Override
	public Long savePatient(Patient patient) {
		Session session = this.sessionFactory.getCurrentSession();
		Long patientId = (Long) session.save(patient);
		return patientId;
	}

	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long deletePatient(Long patientId) {
		// TODO Auto-generated method stub
		return 0l;
		
	}

	@Override
	public List<Patient> getPatients() {
		List<Patient> patients = null;
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Patient");
		patients = query.list();
		return patients;
	}

}
