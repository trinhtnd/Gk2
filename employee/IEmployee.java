package employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



interface IEmployee {
	void showInfo();
}

class Employee implements IEmployee {
	protected String id;
	protected String fulname; 
	protected String birthday;
	protected String phone;
	protected String email;
	protected int employeeType;
	protected static int employeeCount = 0;
	
	public Employee(String id, String fulname, String birthday, String phone, String email, int employeeType) {
		this.id = id;
		this.fulname = fulname;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.employeeType = employeeType;
		employeeCount++;
	}

	@Override
	public void showInfo() {
		System.out.println("ID: "+ id);
		System.out.println("Full Name: "+ fulname);
		System.out.println("Birthday: "+ birthday);
		System.out.println("Phone: "+ phone);
		System.out.println("Email: "+ email);
		System.out.println("Employee Type: "+ employeeType);
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFulname() {
		return fulname;
	}

	public void setFulname(String fulname) {
		this.fulname = fulname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}


class Experience extends Employee {
	private int expInYear;
	private String proSkill;
	public Experience(String id, String fulname, String birthday, String phone, String email,
			int expInYear, String proSkill) {
		super(id, fulname, birthday, phone, email, 1);
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}
	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("Year of Experience: "+ expInYear);
		System.out.println("Professional Skill: "+ proSkill);
	}
	
	public int getExpInYear() {
		return expInYear;
	}
	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}
	public String getProSkill() {
		return proSkill;
	}
	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}
	
}

class Fresher extends Employee {
	private String graduationDate;
	private String graduationRank;
	private String education;
	public Fresher(String id, String fulname, String birthday, String phone, String email,
			String graduationDate, String graduationRank, String education) {
		super(id, fulname, birthday, phone, email, 2);
		this.graduationDate = graduationDate;
		this.graduationRank = graduationRank;
		this.education = education;
	}
	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("Graduation Date: " + graduationDate);
        System.out.println("Graduation Rank: " + graduationRank);
        System.out.println("Education: " + education);
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getGraduationRank() {
		return graduationRank;
	}
	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
}

class Intern extends Employee {
	private String majors;
	private String semester;
    private String universityName;
    
	public Intern(String id, String fulname, String birthday, String phone, String email,
			String majors, String semester, String universityName) {
		super(id, fulname, birthday, phone, email, 3);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
	}

	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		super.showInfo();
		System.out.println("Majors: " + majors);
        System.out.println("Semester: " + semester);
        System.out.println("University Name: " + universityName);
	}

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
}


class EmployeeManager {
	private ArrayList<Employee> employees = new ArrayList<>();
	
	public void addEmployee (Employee employee) {
		employees.add(employee);
		
	}
	
	public void loađEmployeesFromFile(String fileName) {
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine())!=null) {
				String[] data = line.split(",");
				int employeeType = Integer.parseInt(data[0]);
				
				Employee employee;
				switch (employeeType) {
				case 1:
					employee = new Experience(data[1], data[2], data[3], data[4], data[5], Integer.parseInt(data[6]), data[7]);
					break;
                case 2:
					employee = new Fresher(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
					break;
                case 3:
                	employee = new Intern(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
	                break;

				default:
					throw new IllegalArgumentException("Invalid employee type");
				}
				
				employees.add(employee);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveEployeesToFile(String fileName) {
		try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
			for (Employee employee : employees) {
				writer.println(employee.toString());			}
		
		} catch (Exception e) {
            e.printStackTrace();
		}
	}
	
	public void updateEmployee(String id, String newFullname, String newPhone) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				employee.setFulname(newFullname);
				employee.setPhone(newPhone);
				break;
			}
		}
	}
	
	public void deleteEmployee(String id) {
		employees.removeIf(employee -> employee.getId().equals(id));
	}
	
	public void displayAllEmployees() {
        for (Employee employee : employees) {
            employee.showInfo();
            System.out.println("---------------");
        }
    }
}



