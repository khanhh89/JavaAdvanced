package sesion12.bt5.model;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String department;
    private String pathology;
    private int admissionDays;

    public Patient(String id, String name, int age, String department, String pathology, int admissionDays) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.pathology = pathology;
        this.admissionDays = admissionDays;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getPathology() { return pathology; }
    public void setPathology(String pathology) { this.pathology = pathology; }
    public int getAdmissionDays() { return admissionDays; }
    public void setAdmissionDays(int admissionDays) { this.admissionDays = admissionDays; }
}
