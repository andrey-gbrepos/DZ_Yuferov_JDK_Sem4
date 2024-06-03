//Создать справочник сотрудников
//Необходимо:
//Создать класс справочник сотрудников, который содержит внутри
//коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
//Табельный номер
//Номер телефона
//Имя
//        Стаж
//        Добавить метод, который ищет сотрудника по стажу (может быть список)
//        Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
//        Добавить метод, который ищет сотрудника по табельному номеру
//Добавить метод добавления нового сотрудника в справочник



import java.util.*;

public class Employee {
    Integer keyId;
    private String tabelNumber;
    private String phoneNumber;
    private String name;
    private Integer experience;
    private Scanner scanner;

    public HashMap<Integer, Employee> mapEmployees = new HashMap<>();

    private Random random = new Random();

    public String getTabelNumber() {
        return tabelNumber;
    }

    public void setTabelNumber(String tabelNumber) {
        this.tabelNumber = tabelNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    @Override
    public String toString() {
        return String.format("%9s %13s %20s %5d %s", getTabelNumber(),
                        getPhoneNumber(), getName(), getExperience(), "\n");

    }

    public List<Employee> searchByExperienceEmployee(Integer minValueExp, Integer maxValueExp,
                                                                    HashMap<Integer, Employee> map) {
        List<Employee> employList = new ArrayList<>();
        for (Integer key : map.keySet()){
            if (map.get(key).getExperience() >= minValueExp &&
                    map.get(key).experience <= maxValueExp ){
                employList.add(map.get(key));
            }
       }
        System.out.println("Список сотрудников со стажем от "
                            + minValueExp + " до " + maxValueExp);
        return employList;
    }

    public List<Employee> searchByExperienceEmployee(Integer valueExp,
                                                     HashMap<Integer, Employee> map) {
        List<Employee> employList = new ArrayList<>();
        for (Integer key : map.keySet()){
            if (map.get(key).getExperience() == valueExp){
                employList.add(map.get(key));
            }
        }
        System.out.println("Список сотрудников со стажем "
                + valueExp);
        return employList;
    }

    public List<String> searchPhoneByNameEmployee(String name,
                                                     HashMap<Integer, Employee> map) {
        List<String> phoneNumber = new ArrayList<>();
        for (Integer key : map.keySet()){
            if (map.get(key).getName().equals(name)){
                phoneNumber.add(map.get(key).getPhoneNumber());
            }
        }
        return phoneNumber;
    }

    public Employee searchEmployeeByTabelNum(String tabNum,
                                           HashMap<Integer, Employee> map) {

        Employee employee = new Employee();
        for (Integer key : map.keySet()){
            if (map.get(key).getTabelNumber().equals(tabNum)){
                 employee = map.get(key);
            }
        }
        return employee;
    }

    public void fullRandomMapDictionary(Integer numberOfEmployees){

        String[] firstName = {"Валерий", "Оксана", "Сергей", "Вероника", "Григорий", "Дарья", "Константин", "Людмила"};
        String[] lastName = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Горхов", "Сергеев", "Колосов", "Семенов"};
        for (int i = 0; i < numberOfEmployees ; i++) {
            Employee employee = new Employee();
            employee.setTabelNumber(String.format("%s%d", "e-",random.nextInt(100001,500000)));
            employee.setPhoneNumber(String.format("%s%d", "+79",random.nextInt(010101010,319999999)));

            Integer tempIndex1 = random.nextInt(0, 8);
            Integer tempIndex2 = random.nextInt(0, 8);
            employee.setName(firstName[tempIndex1] + " " + ((tempIndex1%2 != 0) ?
                    lastName[tempIndex2] + "а":lastName[tempIndex2]));
            employee.setExperience(random.nextInt(1, 40));

            this.mapEmployees.put(i, employee);
        }
    }

    public  void addEmployee (HashMap<Integer, Employee> mapEmployees){
        Employee employee = new Employee();
        Integer nextId = -1;
        Boolean successName = true;
        for (Integer key : mapEmployees.keySet()){
            if(key > nextId) nextId = key;
        }
        employee.setTabelNumber(String.format("%s%d", "e-",random.nextInt(100001,500000)));
        System.out.println("Сотруднику присвоен табельный номер: " + employee.getTabelNumber());
        try {
            employee.setName(enterNameFromConsole("Введите Имя и Фамилию сотрудника: "));
        }catch (IllegalArgumentNameExeption e) {
            successName = false;
        }
        if (successName){
            String phoneNumber = enterPhoneFromConsole("Введите 10 цифр номера телефона сотрудника: ");
            if (phoneNumber.equals("")){
                employee.setPhoneNumber("");
            } else {
                employee.setPhoneNumber(phoneNumber);
            }
            employee.setExperience(enterExperienceFromConsole("Введите стаж сотрудника (в годах)"));
            mapEmployees.put(++nextId, employee);
        }
    }

    public  String enterNameFromConsole (String str) throws IllegalArgumentNameExeption{  //Приглашение для ввода
        scanner = new Scanner(System.in);
        String alphabit = Alphabit.ENGLISH_ALPHABIT + Alphabit.RUSSIAN_ALPHABIT + " ";
        String name = "";
        System.out.println(str);
        name = scanner.nextLine();
        String[] string = name.split("");
        for (int j = 0; j < string.length; j++) {
            if (!alphabit.contains(string[j])) {
                throw new IllegalArgumentNameExeption("Некорректный ввод. Пользователь "+ name + " не добавлен.\n");
            }
        }
        return name;
    }

    public  String enterPhoneFromConsole (String str) {  //Приглашение для ввода
        scanner = new Scanner(System.in);
        String numbers = "0123456789";
        System.out.println(str);
        System.out.print("+7 ");
        int uncorrectChar = 0;
        String phoneNum = scanner.next();
        String[] phone = phoneNum.split("");
        for (int i = 0; i < phone.length; i++) {
            if (!numbers.contains(phone[i])) {
                uncorrectChar++;
            }
        }
        if (uncorrectChar == 0) phoneNum = "+7"+phoneNum;
        else {
            System.out.println("Ввод не корректен. Присвоен пустой номер");
            phoneNum = "";
        }
        return phoneNum;
    }

    public  Integer enterExperienceFromConsole (String str) {  //Приглашение для ввода
        String  experience = "";
        String strExperience ="";
        for (int i = 0; i <= 80; i++) {
            strExperience += i;
        }
        System.out.println(str);
        experience = scanner.next();
         if (strExperience.contains(experience)){
            return Integer.parseInt(experience);
        } else {
             System.out.println("Не корректный ввод. Установлено значение - 1");
             experience = "1";
         }
        return Integer.parseInt(experience);
    }

    public void printEmployList(HashMap<Integer, Employee> employList){
        //List <Integer> keys = new ArrayList<>();
        System.out.printf("%3s %9s %13s %18s %5s %s", "id", "Таб.№", "Ном. телефона", " Имя Фамилия", "Стаж","\n");
        System.out.println("----------------------------------------------------------");
        for (Integer key : employList.keySet()){
            System.out.printf("%3d", key);
            System.out.print(employList.get(key));
        }

    }
}
