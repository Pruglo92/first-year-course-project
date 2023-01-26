import java.util.*;

public class EmployeeBook {

    static final String EMPLOYEE_STRING = "Employee{fullName='%s', salary=%d, id=%d}";

    private static final Employee[] employees = new Employee[11];

    static {
        Employee employee1 = new Employee("Рожков Гордей Станиславович", 4, 1500);
        Employee employee2 = new Employee("Павлов Фрол Лаврентьевич", 2, 1800);
        Employee employee3 = new Employee("Рогов Трофим Проклович", 5, 2000);
        Employee employee4 = new Employee("Фокин Юрий Степанович", 1, 2556);
        Employee employee5 = new Employee("Харитонов Игнатий Всеволодович", 3, 7456);
        Employee employee6 = new Employee("Носов Пантелеймон Адольфович", 4, 8372);
        Employee employee7 = new Employee("Логинов Вячеслав Геннадьевич", 1, 1267);
        Employee employee8 = new Employee("Пономарёв Клим Дмитриевич", 2, 9000);
        Employee employee9 = new Employee("Шаров Август Натанович", 5, 8296);
        Employee employee10 = new Employee("Рыбаков Влас Оскарович", 3, 4528);

        employees[0] = employee1;
        employees[1] = employee2;
        employees[2] = employee3;
        employees[3] = employee4;
        employees[4] = employee5;
        employees[5] = employee6;
        employees[6] = employee7;
        employees[7] = employee8;
        employees[8] = employee9;
        employees[9] = employee10;
    }

    public void getAllEmployees() {      //список всех сотрудников со всеми данными
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public void getAllSalary() {         //подсчет затрат на все зарплаты
        int allSalary = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                allSalary = allSalary + employee.getSalary();
            }
        }
        System.out.println("Зарплата всех сотрудников за месяц составляет: " + allSalary);
    }

    public void getMinSalary() {         //поиск сотрудника с минимальной зарплатой
        int min = Integer.MAX_VALUE;
        for (Employee employee : employees) {
            if (employee != null) {
                if (min > employee.getSalary()) {
                    min = employee.getSalary();
                }
            }
        }
        System.out.println("Минимальная зарплата сотрудника за месяц: " + min);
    }

    public void getMaxSalary() {         //поиск сотрудника с максимальной зарплатой
        int max = Integer.MIN_VALUE;
        for (Employee employee : employees) {
            if (employee != null) {
                if (max < employee.getSalary()) {
                    max = employee.getSalary();
                }
            }
        }
        System.out.println("Максимальная зарплата сотрудника за месяц: " + max);
    }

    public void getAverageSalary() {     //подсчет среднего значения зарплат
        double allSalary = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                allSalary = allSalary + employee.getSalary();
            }
        }
        System.out.printf("Зарплата всех сотрудников за месяц составляет: %.2f", allSalary / Employee.getCountId());
        System.out.println();
    }

    public void getAllFullNames() {      //список Ф. И. О. всех сотрудников
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getFullName());
            }
        }
    }

    public void indexedSalary(int percent) {     //индексация всех зарплат
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() + employee.getSalary() / 100 * percent);
            }
        }
        System.out.println("Зарплата проиндексирована на " + percent + "%");
    }

    public void getEmployeeWithMinSalary(int department) {       //сотрудник с минимальной зарплатой в отделе
        System.out.println("Сотрудник с минимальной зарплатой в отделе № " + department);
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    public void getEmployeeWithMaxSalary(int department) {       //сотрудник с максимальной зарплатой в отделе
        System.out.println("Сотрудник с максимальной зарплатой в отделе № " + department);
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    public void getDepartmentSalary(int department) {        //сумма затрат на зарплату по отделу
        var sum = Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getDepartment() == department)
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
        System.out.println("Сумма затрат на зарплату по отделу: " + sum);
    }

    public void getDepartmentAverageSalary(int department) {     //средняя зарплата по отделу
        var sum = Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getDepartment() == department)
                .map(Employee::getSalary)
                .mapToDouble(Integer::doubleValue)
                .average()
                .stream()
                .sum();
        System.out.println("Средняя зарплата по отделу: " + sum);
    }

    public void indexTheSalariesOfDepartmentEmployees(int percent, int department) { //Проиндексировать зарплату всех сотрудников отдела на процент
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getDepartment() == department)
                .forEach(x -> x.setSalary(x.getSalary() + x.getSalary() / 100 * percent));
        System.out.println("Зарплата сотрудников отдела №" + department + " проиндексирована на " + percent + "%");
    }

    public void getAllEmployeesOfTheDepartment(int department) {     //Напечатать всех сотрудников отдела (все данные, кроме отдела)
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getDepartment() == department)
                .forEach(x -> System.out.printf((EMPLOYEE_STRING) + "\n", x.getFullName(), x.getSalary(), x.getId()));
    }

    public void getAllEmployeesWithSalaryLessThanNumber(int number) {    //напечатать всех сотрудников с зарплатой меньше числа
        System.out.println("Сотрудники с зарплатой меньше чем " + number);
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getSalary() < number)
                .forEach(System.out::println);
    }

    public void getAllEmployeesWithSalaryGreaterThanTheNumber(int number) {  //напечатать всех сотрудников с зарплатой больше числа
        System.out.println("Сотрудники с зарплатой больше или равной чем " + number);
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getSalary() >= number)
                .forEach(System.out::println);
    }

    public void addNewEmployee(String fullName, int department, int salary) {   //добавить нового сотрудника
        boolean check = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(fullName, department, salary);
                System.out.println("Новый сотрудик удачно трудоустроен.");
                check = true;
            }
        }
        if (!check) {
            System.out.println("Все вакантные места для сотрудников заняты," +
                    "попробуйте кого-нибуть уволить.");
        }
    }

    public void deleteEmployeeById(int id) {        //удалить сотрудника по ID
        boolean removed = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId() == id) {
                    employees[i] = null;
                    System.out.println("Сотрудник с ID " + id + " успешно удалён.");
                    removed = true;
                }
            }
        }
        if (!removed) {
            throw new RuntimeException("Сотрудник с данным ID " + id + " отсутствует.");
        }
    }

    public void deleteEmployeeByName(String name) {     //удалить сотрудника по имени
        boolean removed = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getFullName().equals(name)) {
                    employees[i] = null;
                    System.out.println("Сотрудник " + name + " успешно удалён.");
                    removed = true;
                }
            }
        }
        if (!removed) {
            throw new RuntimeException("Сотрудник " + name + " отсутствует.");
        }
    }

    public void changeEmployeeSalary(String name, int salary) {     //изменить зарплату сотрудника по имени
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getFullName().equals(name))
                .forEach(x -> x.setSalary(salary));
    }

    public void changeEmployeeDepartment(String name, int department) {     //изменить отдел сотрудника по имени
        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(x -> x.getFullName().equals(name))
                .forEach(x -> x.setDepartment(department));
    }

    /**
     * Данный метод выведет на экран всех сотрудников для каждого отдела в порядке возрастания отдела.
     * За зависимость взяты уникальные номера отделов, имеющиеся у всех сотрудников, так как отдел сотруднику
     * присваивается после прохождения валидации в конструкторе.
     */
    public void getAllEmployeesByAllDepartment() {
        List<Integer> departmentNumbers = new ArrayList<>(Arrays.stream(employees)
                .filter(Objects::nonNull)
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .toList());

        for (Integer department : departmentNumbers) {
            System.out.println("В отделе №" + department + " работают следующие сотрудники:");
            for (Employee employee : employees) {
                if (employee != null) {
                    if (employee.getDepartment() == department) {
                        System.out.println(employee);
                    }
                }
            }
        }
    }
}
