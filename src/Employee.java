public class Employee {
    private final String fullName;
    private int department;
    private int salary;
    private static int countId;
    private final int id;

    public Employee(String fullName, int department, int salary) {
        countId++;
        this.fullName = fullName;
        this.department = validate(department);
        this.salary = salary;
        this.id = countId;
    }

    public static int validate(int parameter) {
        if (parameter > 0 && parameter <= 5) {
            return parameter;
        }else {
            throw new NumberFormatException("Номер отдела может быть только от 1 до 5 включительно.");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public static int getCountId() {
        return countId;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setDepartment(int department) {
        this.department = validate(department);
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }
}
