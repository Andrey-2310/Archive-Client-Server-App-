package Person;
import org.eclipse.swt.widgets.*;
import Server.Cataloguer;

public class Person {
		
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
		
		/** The WorkingPlace. */
		private String employer;
		
		/** The job experience. */
		private String experience;
		
		/** The job position. */
		private String position;
		
		public Person(){
			super();
		}
		
		public Person(String surname, String name, String fathername, String phone, String email, String employee,
				String jobExperience, String jobPosition, String sex) {
			super();
			this.setSurname(surname);
			this.setName(name);
			this.setFathername(fathername);
			this.setPhone(phone);
			this.setEmail(email);
			this.setEmployer(employee);
			this.setJobExperience(jobExperience);
			this.setJobPosition(jobPosition);
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
		 * @param surname the new surname
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
		 * @param name the new name
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
		 * @param fathername the new fathername
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
		 * @param phone the new phone
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
		 * @param email the new email
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
		 * @param sex the new sex
		 */
		public void setSex(String sex) {
			this.sex = sex;
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
		 * @param employee the new employee
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
		 * @param jobExperience the new job experience
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
		 * @param jobPosition the new job position
		 */
		public void setJobPosition(String jobPosition) {
			this.position = jobPosition;
		}
		
		public String getXmlFileName() {
			return new String(getName() + getSurname());
		}
		
		public static void setUpPersons(){
			Cataloguer.add(new Person("Редковский", "Андрей", "Дмитриевич", "+375291974714", "redkovskiyandrey@gmail.com", "EPAM",
					"2", "Уборщик", "Мужской"));
			Cataloguer.add(new Person("Трусевич", "Валентин", "Сергеевич", "+375298573010", "vtrusevich@gmail.com",
					"OOO", "2", "Охранник", "Мужской"));
			
		}
}
