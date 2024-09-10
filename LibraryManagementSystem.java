package com.project;

import java.util.*;

class NotFoundException extends Exception{
	public NotFoundException(String message) {
		super(message);
	}
}

class Admin{
	private String username;
	private String pasword;
	private List<Librarian> librarians=new ArrayList<>();
	
	public Admin(String username,String password) {
		this.username=username;
		this.pasword=password;
		
	}
	public boolean login(String username,String password) {
		return this.username.equals(username) &&
				this.pasword.equals(password);
	}
	public void addLibrarian(Librarian librarian) {
		librarians.add(librarian);
		System.out.println("Librarian added successfully!");
	}
	public void updateLibrarian (int id,String name) 
		throws NotFoundException{
			Librarian librarian =findLibrarian(id);
			librarian.setName(name);
			System.out.println("Librarian updated succesfully");
		}
	public void getLibraian(int id) throws NotFoundException{
		Librarian librarian =findLibrarian(id);
		System.out.println("Librarian ID: " + librarian.getId() + ", Name: " + librarian.getName());
	}
	public void getAllLibrarians() {
        for (Librarian librarian : librarians) {
            System.out.println("Librarian ID: " + librarian.getId() + ", Name: " + librarian.getName());
        }
	}
        public void deleteLibrarian(int id) throws NotFoundException {
            Librarian librarian = findLibrarian(id);
            librarians.remove(librarian);
            System.out.println("Librarian deleted successfully.");
        }
        private Librarian findLibrarian(int id) throws NotFoundException {
            return librarians.stream()
                    .filter(librarian -> librarian.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Librarian not found!"));
        }
	}
class Librarian {
    private int id;
    private String name;
    private String username;
    private String password;

    public Librarian(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Student {
    private int id;
    private String name;
    private String username;
    private String password;

    public Student(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class LibrarianModule {
    private List<Student> students = new ArrayList<>();
    
    public boolean login(String username, String password, List<Librarian> librarians) {
        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username) && librarian.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
 
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    
    public void updateStudent(int id, String name) throws NotFoundException {
        Student student = findStudent(id);
        student.setName(name);
        System.out.println("Student updated successfully.");
    }

   
    public void getStudent(int id) throws NotFoundException {
        Student student = findStudent(id);
        System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName());
    }

    
    public void getAllStudents() {
        for (Student student : students) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName());
        }
    }

    
    public void deleteStudent(int id) throws NotFoundException {
        Student student = findStudent(id);
        students.remove(student);
        System.out.println("Student deleted successfully.");
    }

    
    public Student findStudent(int id) throws NotFoundException {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student not found!"));
    }

    public Student findStudentByUsername(String username) throws NotFoundException {
        return students.stream()
                .filter(student -> student.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student not found!"));
    }
}
class StudentModule {
    private Student student;

    public StudentModule(Student student) {
        this.student = student;
    }

   
    public boolean login(String username, String password) {
        return student.getUsername().equals(username) && student.getPassword().equals(password);
    }

   
    public void viewProfile() {
        System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName());
    }
}

public class LibraryManagementSystem {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        Admin admin = null;
        List<Librarian> registeredLibrarians = new ArrayList<>();
        LibrarianModule librarianModule = new LibrarianModule();
        while (true) {
            System.out.println("Choose role : 1. Admin 2. Librarian 3. Student 4. Exit");
            int role = scanner.nextInt();
            scanner.nextLine();
            switch (role) {
            case 1: 
                if (admin == null) {
                    System.out.println("Admin Registration ");
                    System.out.println("Enter Admin Username:");
                    String adminUsername = scanner.nextLine();
                    System.out.println("Enter Admin Password:");
                    String adminPassword = scanner.nextLine();
                    admin = new Admin(adminUsername, adminPassword);
                    System.out.println("Admin registered successfully!");
                }

                System.out.println("Enter Admin Username:");
                String adminUsername = scanner.nextLine();
                System.out.println("Enter Admin Password:");
                String adminPassword = scanner.nextLine();
                if (admin.login(adminUsername, adminPassword)) {
                    System.out.println("Admin logged in successfully.");
                    boolean exit = false;
                    while (!exit) {
                        System.out.println("Choose an operation: ");
                        System.out.println("1. Add Librarian");
                        System.out.println("2. Update Librarian");
                        System.out.println("3. Get Particular Librarian");
                        System.out.println("4. Get All Librarians");
                        System.out.println("5. Delete Librarian");
                        System.out.println("6. Exit to Main Menu");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                        case 1:
                            System.out.println("Enter Librarian ID:");
                            int libId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter Librarian Name:");
                            String libName = scanner.nextLine();
                            System.out.println("Enter Librarian Username:");
                            String libUsername = scanner.nextLine();
                            System.out.println("Enter Librarian Password:");
                            String libPassword = scanner.nextLine();
                            Librarian newLibrarian = new Librarian(libId, libName, libUsername, libPassword);
                            admin.addLibrarian(newLibrarian);
                            registeredLibrarians.add(newLibrarian);
                            break;
                        case 2:
                            System.out.println("Enter Librarian ID to Update:");
                            libId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter New Name:");
                            libName = scanner.nextLine();
                            try {
                                admin.updateLibrarian(libId, libName);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            System.out.println("Enter Librarian ID:");
                            libId = scanner.nextInt();
                            scanner.nextLine();
                            try {
                                admin.getLibraian(libId);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4:
                            admin.getAllLibrarians();
                            break;
                        case 5:
                            System.out.println("Enter Librarian ID to Delete:");
                            libId = scanner.nextInt();
                            scanner.nextLine();
                            try {
                                admin.deleteLibrarian(libId);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 6:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            } else {
                System.out.println("Invalid admin credentials.");
            }
            break;
            case 2: 
                if (registeredLibrarians.isEmpty()) {
                    System.out.println("No librarians registered. Please contact Admin.");
                    break;
                }

                System.out.println("Enter Librarian Username:");
                String librarianUsername = scanner.nextLine();
                System.out.println("Enter Librarian Password:");
                String librarianPassword = scanner.nextLine();
                if (librarianModule.login(librarianUsername, librarianPassword, registeredLibrarians)) {
                    System.out.println("Librarian logged in successfully.");
                    boolean exit = false;
                    while (!exit) {
                        System.out.println("Choose an operation: ");
                        System.out.println("1. Add Student");
                        System.out.println("2. Update Student");
                        System.out.println("3. Get Particular Student");
                        System.out.println("4. Get All Students");
                        System.out.println("5. Delete Student");
                        System.out.println("6. Exit to Main Menu");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); 
                        switch (choice) {
                            case 1:
                                System.out.println("Enter Student ID:");
                                int studentId = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter Student Name:");
                                String studentName = scanner.nextLine();
                                System.out.println("Enter Student Username:");
                                String studentUsername = scanner.nextLine();
                                System.out.println("Enter Student Password:");
                                String studentPassword = scanner.nextLine();
                                librarianModule.addStudent(new Student(studentId, studentName, studentUsername, studentPassword));
                                break;
                            case 2:
                                System.out.println("Enter Student ID to Update:");
                                studentId = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter New Name:");
                                studentName = scanner.nextLine();
                                try {
                                    librarianModule.updateStudent(studentId, studentName);
                                } catch (NotFoundException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.println("Enter Student ID:");
                                studentId = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    librarianModule.getStudent(studentId);
                                } catch (NotFoundException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 4:
                                librarianModule.getAllStudents();
                                break;
                            case 5:
                                System.out.println("Enter Student ID to Delete:");
                                studentId = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    librarianModule.deleteStudent(studentId);
                                } catch (NotFoundException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 6:
                                exit = true;
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                } else {
                    System.out.println("Invalid librarian credentials.");
                }
                break;

            case 3:
                System.out.println("Enter Student Username:");
                String studentUsername = scanner.nextLine();
                System.out.println("Enter Student Password:");
                String studentPassword = scanner.nextLine();
                try {
                    Student student = librarianModule.findStudentByUsername(studentUsername);
                    StudentModule studentModule = new StudentModule(student);
                    if (studentModule.login(studentUsername, studentPassword)) {
                        studentModule.viewProfile();
                    } else {
                        System.out.println("Invalid student credentials.");
                    }
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4:
                System.out.println("Exiting ");
                return;

            default:
                System.out.println("Invalid role.");
        }
                    }
                }
            
        }
	
	
	
                

	


