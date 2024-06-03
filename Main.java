import java.util.Random;

public class Main {
    public static void main (String[] args){
        Random random = new Random();
        //HashMap<Integer, Employee> employ;
        Employee employee = new Employee();
        System.out.println("Заполнение случайными сотрудниками:");
        employee.fullRandomMapDictionary(15); // Заполняем 15-ю сотрудниками
        employee.printEmployList(employee.mapEmployees);

        System.out.println();
        System.out.println("Поиск работника по табельному номеру:");
        // Выбираем случайный табельный номер
        String tabNum = employee.mapEmployees.get(random.nextInt(0,15)).getTabelNumber();
        System.out.println("Сотрудник под табельным номером " + tabNum);
        System.out.println(employee.searchEmployeeByTabelNum(tabNum, employee.mapEmployees));

        System.out.println();
        System.out.println("Поиск работника по стажу:");
        System.out.println("По диапазону:");
        System.out.println(employee.searchByExperienceEmployee(2, random.nextInt(2,10), employee.mapEmployees));
        System.out.println("По конкретному значению:");
        System.out.println(employee.searchByExperienceEmployee(random.nextInt(1,35), employee.mapEmployees));

        System.out.println();
        // Выбираем случайное имя
        String name = employee.mapEmployees.get(random.nextInt(0,15)).getName();
        System.out.println("Поиск телефона по имени: " + name);
        System.out.println("Контакты сотрудника "+ name+ ": "+employee.searchPhoneByNameEmployee(name, employee.mapEmployees));

        System.out.println();
        System.out.println("Добавление сотрудника в справочник (вручную)");
        employee.addEmployee(employee.mapEmployees);
        employee.printEmployList(employee.mapEmployees);
    }
}
