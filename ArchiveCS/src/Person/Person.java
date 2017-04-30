package Person;

import org.eclipse.swt.widgets.*;
import Server.Cataloguer;

public class Person {


	/** The job. */
	private PersonJob personJob;
	
	private PersonInfo personInfo;

	public Person() {
		super();
		personJob = new PersonJob();
		personInfo=new PersonInfo();
	}

	public Person(String surname, String name, String fathername, String phone, String email, String employer,
			String jobExperience, String jobPosition, String sex) {
		super();
		this.personJob = new PersonJob(employer, jobExperience, jobPosition);
		this.personInfo = new PersonInfo( surname,  name,  fathername,  phone,  email,  employer, sex);
		
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return personInfo.getSurname();
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		personInfo.setSurname(surname);
	}

	/*
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return personInfo.getName();
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		personInfo.setName(name);
	}

	/**
	 * Gets the fathername.
	 *
	 * @return the fathername
	 */
	public String getFathername() {
		return personInfo.getFathername();
	}

	/**
	 * Sets the fathername.
	 *
	 * @param fathername
	 *            the new fathername
	 */
	public void setFathername(String fathername) {
		personInfo.setFathername(fathername);
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return personInfo.getPhone();
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		personInfo.setPhone(phone);
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return personInfo.getEmail();
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		personInfo.setEmail(email);
	}

	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return personInfo.getSex();
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex
	 *            the new sex
	 */
	public void setSex(String sex) {
		personInfo.setSex(sex);
	}

	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public String getEmployer() {
		return personJob.getEmployer();
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee
	 *            the new employee
	 */
	public void setEmployer(String employer) {
		this.personJob.setEmployer(employer);

	}

	/**
	 * Gets the job experience.
	 *
	 * @return the job experience
	 */
	public String getJobExperience() {
		return personJob.getJobExperience();
	}

	/**
	 * Sets the job experience.
	 *
	 * @param jobExperience
	 *            the new job experience
	 */
	public void setJobExperience(String jobExperience) {
		this.personJob.setJobExperience(jobExperience);
	}

	/**
	 * Gets the job position.
	 *
	 * @return the job position
	 */
	public String getJobPosition() {
		return personJob.getJobPosition();
	}

	/**
	 * Sets the job position.
	 *
	 * @param jobPosition
	 *            the new job position
	 */
	public void setJobPosition(String jobPosition) {
		this.personJob.setJobPosition(jobPosition);
	}

	public String getXmlFileName() {
		return new String(getName() + getSurname());
	}

	public static void setUpPersons() {
		Cataloguer.add(new Person("Redkoskiy", "Andrey", "Dmitrievich", "+375291974714", "redkovskiyandrey@gmail.com",
				"EPAM", "2", "Developer", "Man"));
		Cataloguer.add(new Person("Trusevich", "Valentin", "Sergeevich", "+375298573010", "vtrusevich@gmail.com", "OOO",
				"2", "Security", "Man"));
		Cataloguer.add(new Person("Karachun", "Anton", "Antonovich", "+3752911122330", "tonymontana@tut.by", "BSUIR",
				"2", "Student", "Man"));

	}
}

class PersonJob {
	/** The WorkingPlace. */
	private String employer;

	/** The job experience. */
	private String experience;

	/** The job position. */
	private String position;

	public PersonJob() {
		super();
	}

	public PersonJob(String employer, String experience, String position) {
		setEmployer(employer);
		setJobExperience(experience);
		setJobPosition(position);
	}

	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public String getEmployer() {
		return employer;
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee
	 *            the new employee
	 */
	public void setEmployer(String employee) {
		this.employer = employee;
	}

	/**
	 * Gets the job experience.
	 *
	 * @return the job experience
	 */
	public String getJobExperience() {
		return experience;
	}

	/**
	 * Sets the job experience.
	 *
	 * @param jobExperience
	 *            the new job experience
	 */
	public void setJobExperience(String jobExperience) {
		this.experience = jobExperience;
	}

	/**
	 * Gets the job position.
	 *
	 * @return the job position
	 */
	public String getJobPosition() {
		return position;
	}

	/**
	 * Sets the job position.
	 *
	 * @param jobPosition
	 *            the new job position
	 */
	public void setJobPosition(String jobPosition) {
		this.position = jobPosition;
	}

}

class PersonInfo{
	
	/** The surname. */
	private String surname;

	/** The name. */
	private String name;

	/** The fathername. */
	private String fathername;

	/** The phone. */
	private String phone;

	/** The email. */
	private String email;

	/** The sex. */
	private String sex;
	
	public PersonInfo(){
		super();
	}
	public PersonInfo(String surname, String name, String fathername, String phone, String email, String employer, String sex){
		super();
		this.setSurname(surname);
		this.setName(name);
		this.setFathername(fathername);
		this.setPhone(phone);
		this.setEmail(email);
		this.setSex(sex);
	}
	
	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the fathername.
	 *
	 * @return the fathername
	 */
	public String getFathername() {
		return fathername;
	}

	/**
	 * Sets the fathername.
	 *
	 * @param fathername
	 *            the new fathername
	 */
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex
	 *            the new sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
}
