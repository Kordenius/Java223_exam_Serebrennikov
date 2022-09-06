package core;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Core {

    private static final File FileWorkersPath = new File("core/Workers");
    private static final File FileWorkers = new File("core/Workers/Workers.txt");

    private static HashMap<Integer, Object> workerListOperation = new HashMap<>();

    public static void main(String[] args) {

        checkFileIsExist();
        readFormFileToWorkerListOperation();

        Scanner scanner = new Scanner(System.in);

        printStartOperationDialog();

        coreFunction:
        while (scanner.hasNextLine()) {
            String currentOperation = scanner.nextLine();
            OperationType enumByUser = OperationType.getEnumByUser(currentOperation);
            if (enumByUser==null) {
                System.out.println("Операция не найдена!");
                printStartOperationDialog();
                continue;
            }
            switch (enumByUser) {
                case SHOW_WORKER -> showWorker();
                case ADD_WORKER -> addNewWorker();
                case REMOVE_WORKER -> removeWorker();
                case CHANGE_WORKER -> System.out.println("Данная функция не работает");
                case TOP10_WORKERS_BY_SALARY -> topTenWorkersBySalary();
                case TOP10_WORKERS_BY_LONGEST_WORK -> topTenWorkersByLongestWork();
                case CHECK -> checkFileIsExist();
                case EXIT -> {
                    break coreFunction;
                }
                default -> System.out.println("Операция не найдена!");
            }

            printStartOperationDialog();
        }
        scanner.close();
    }

    private static void checkFileIsExist() {
        if (Core.FileWorkers.isFile()) {
            System.out.println("База данных на текущий момент существует");
        } else
        try {
            boolean FilePath = Core.FileWorkersPath.mkdirs();
            boolean File = Core.FileWorkers.createNewFile();
            writeMapForOperationToFileWorkerList();
            if (FilePath & File) {
                System.out.println("Создана новая база данных");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void removeWorker() {
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        Scanner removeWorkerScan = new Scanner(System.in);
        System.out.println("Ведите индекс искомого работника: ");
        String inputIndex = removeWorkerScan.nextLine();
        if (isExistWorker(inputIndex)){
            showExistWorker(inputIndex);
            System.out.println("Удалить пользователя?");
            outer:
            while (true){
                Scanner answerScan = new Scanner(System.in);
                printCorrectAnswerStatusOperationDialog();
                String answer = answerScan.nextLine();
                CorrectAnswer enumByUser = CorrectAnswer.getEnumByNumber(answer);
                if (enumByUser == null) {
                    System.out.println("Операция не найдена!");
                    continue;
                }
                switch (enumByUser){
                    case YES -> {
                        workerListOperation.remove(inputIndex);
                        writeMapForOperationToFileWorkerList();
                        System.out.println("Пользователь был удалён");
                        break outer;
                    }
                    case NO -> {
                        System.out.println("Операция переврана");
                        break outer;
                    }
                }
            }
        }
        else
            System.out.println("Пользователь не найден");
    }


    private static void addNewWorker() {
        Scanner scanner = new Scanner(System.in);
        Integer index = newIndex();
        System.out.println("Ведите имя нового работника");
        String nameWorker = scanner.nextLine();
        System.out.println("Ведите фамилию нового работника");
        String lastnameWorker = scanner.nextLine();
        System.out.println("Ведите отчество нового работника");
        String patronymicWorker = scanner.nextLine();
        Date brithDayWorker = brithDayNewWorker();
        System.out.println("Ведите пол нового работника");
        String sexWorker = scanner.nextLine();
        ArrayList<String> workerContactNumberArray = getNewWorkerContactNumber();
        System.out.println("Ведите ФИО руководителя нового работника");
        String chief = scanner.nextLine();
        System.out.println("Ведите занимаемый пост новым работником");
        String postWorker = scanner.nextLine();
        System.out.println("Ведите департамент занимаемый новым работником");
        String departmentWorker = scanner.nextLine();
        Date dayOfAdmissionWorker = dayOfAdmission();
        System.out.println("Ведите зарплату нового работника");
        int salaryWorker = scanner.nextInt();
        String statusWorker = WorkerStatus();

        String sb = showTab(index,nameWorker, lastnameWorker, patronymicWorker, brithDayWorker, sexWorker,
                workerContactNumberArray, postWorker, departmentWorker, dayOfAdmissionWorker,
                salaryWorker, statusWorker, chief);

        System.out.print(sb);
        System.out.println("Ведённые данные корректны?");
        outer:
        while (true){
            Scanner answerScan = new Scanner(System.in);
            printCorrectAnswerStatusOperationDialog();
            String answer = answerScan.nextLine();
            CorrectAnswer enumByUser = CorrectAnswer.getEnumByNumber(answer);
            if (enumByUser == null) {
                System.out.println("Операция не найдена!");
                continue;
            }
            switch (enumByUser){
                case YES -> {
                    WorkerList newWorker = new WorkerList(nameWorker, patronymicWorker,lastnameWorker,brithDayWorker,
                            sexWorker, workerContactNumberArray ,postWorker,departmentWorker,chief, dayOfAdmissionWorker,
                            salaryWorker,statusWorker,index);
                    workerListOperation.putIfAbsent(newWorker.getIndex(), newWorker);
                    writeMapForOperationToFileWorkerList();
                    break outer;
                }
                case NO -> {
                    System.out.println("Операция переврана");
                    break outer;
                }
            }
        }
    }

    private static String showTab(Integer index , String nameNewWorker, String lastnameNewWorker,
                                  String patronymicNewWorker, Date brithDayNewWorker, String sexNewWorker,
                                  ArrayList<String> newWorkerContactNumberArray, String postNewWorker,
                                  String departmentNewWorker, Date dayOfAdmissionNewWorker, int salaryNewWorker,
                                  String StatusWorker, String chief) {
        String sb = "Индекс: " + index + '\n' +
                "Имя: " + nameNewWorker + '\n' +
                "Фамилия: " + lastnameNewWorker + '\n' +
                "Отчество: " + patronymicNewWorker + '\n' +
                "День Рождение: " + brithDayNewWorker + '\n' +
                "Пол: " + sexNewWorker + '\n' +
                "контактный телефон: " + newWorkerContactNumberArray + '\n' +
                "Занимаемый пост: " + postNewWorker + '\n' +
                "отдел: " + departmentNewWorker + '\n' +
                "Начальник: " + chief + '\n' +
                "День принятия на работу: " + dayOfAdmissionNewWorker + '\n' +
                "Зарплата: " + salaryNewWorker + ".Руб" + '\n' +
                "Статус: " + StatusWorker + '\n';
        return sb;
    }

    private static Date dayOfAdmission() {
        Scanner admissionScan = new Scanner(System.in);
        Calendar dayOfAdmissionNewWorker = new GregorianCalendar();
        System.out.println("Ведите год принятия нового работника");
        int admissionDayYear = admissionScan.nextInt();
        System.out.println("Ведите месяц принятия нового работника");
        int admissionDayMount = admissionScan.nextInt();
        System.out.println("Ведите день принятия нового работника");
        int admissionDay = admissionScan.nextInt();
        dayOfAdmissionNewWorker.set(admissionDayYear,
                admissionDayMount,
                admissionDay);
        return dayOfAdmissionNewWorker.getTime();
    }

    private static String WorkerStatus() {
        Scanner statusScan = new Scanner(System.in);
        System.out.println("Ведите статус нового работника");
        String StatusWorker;
        outer:
        while (true) {
            printWorkerStatusOperationDialog();
            String statusNewWorker = statusScan.nextLine();
            WorkerStatus getEnumByNumber = WorkerStatus.getEnumByNumber(statusNewWorker);
            if (getEnumByNumber==null) {
                System.out.println("Статус не найден");
                continue;
            }
            switch (getEnumByNumber) {
                case ACTIVE -> {
                    StatusWorker = ("Активен");
                    break outer;
                }
                case HIRED -> {
                    StatusWorker = ("Нанят");
                    break outer;
                }
                case ON_VACATION -> {
                    StatusWorker = ("В отпуске");
                    break outer;
                }
                case ON_SICK_LEAVE -> {
                    StatusWorker = ("На больничном");
                    break outer;
                }
                case FIRED -> System.out.println("Данный статус недоступен для нового работника");
            }
        }
        return StatusWorker;
    }

    private static ArrayList<String> getNewWorkerContactNumber() {
        Scanner numScan = new Scanner(System.in);
        System.out.println("Ведите контактный телефон нового работника, к примеру: +79999999999");
        String contactNumberNewWorker = numScan.nextLine();
        String[] newWorkerContactNumberArraySplit = contactNumberNewWorker.split(" ");
        ArrayList<String> newWorkerContactNumberArray = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(\\+7)\\d{10}");
        for (String current : newWorkerContactNumberArraySplit){
            if (pattern.matcher(current).matches() || workerListOperation.isEmpty()){
                System.out.println("Ведённый телефон соответствует формату");
            }
            else {
                System.out.println("Невозможно добавить телефон. \n Телефон не соответствует формату.");
            }

            if (isExistContact(current) && !workerListOperation.isEmpty()){
                System.out.println("Такой номер существует");
            }
            else {
                newWorkerContactNumberArray.add(current);
                System.out.println("Телефон был добавлен");
            }
        }
        return newWorkerContactNumberArray;
    }

    private static Date brithDayNewWorker() {
        Scanner brithScan = new Scanner(System.in);
        System.out.println("Ведите год рождения нового работника");
        int brithDayYear = brithScan.nextInt();
        System.out.println("Ведите месяц рождения нового работника");
        int brithDayMount = brithScan.nextInt();
        System.out.println("Ведите день рождения нового работника");
        int brithDay = brithScan.nextInt();
        Calendar brithDayNewWorker = new GregorianCalendar();
        brithDayNewWorker.set(brithDayYear,
                brithDayMount,
                brithDay);
        return brithDayNewWorker.getTime();
    }

    private static void showWorker() {
        Scanner showScan = new Scanner(System.in);
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        System.out.println("Ведите индекс искомого пользователя: ");
        String inputIndex = showScan.nextLine();
        if (isExistWorker(inputIndex)){
            showExistWorker(inputIndex);
        }
        else
            System.out.println("Пользователь не найден");
    }

    private static void printStartOperationDialog() {
        System.out.println("Ведите число функции: ");
        System.out.println(getOperations());
    }

    private static void printWorkerStatusOperationDialog() {
        System.out.println("Ведите число функции: ");
        System.out.println(getOperationsWorkerStatus());
    }

    private static void printCorrectAnswerStatusOperationDialog() {
        System.out.println("Ведите число функции: ");
        System.out.println(getOperationsCorrectAnswer());
    }

    private static String getOperations() {
        return (Arrays.toString(OperationType.values()).
                replace("[", "")).
                replace("]", "").
                replace(", ", "").
                trim();
    }

    private static String getOperationsWorkerStatus() {
        return (Arrays.toString(WorkerStatus.values()).
                replace("[", "")).
                replace("]", "").
                replace(", ", "").
                trim();
    }

    private static String getOperationsCorrectAnswer() {
        return (Arrays.toString(CorrectAnswer.values()).
                replace("[", "")).
                replace("]", "").
                replace(", ", "").
                trim();
    }

    private static String getOperationsChangeWorker() {
        return (Arrays.toString(ChangeWorker.values()).
                replace("[", "")).
                replace("]", "").
                replace(", ", "").
                trim();
    }

    private static void writeMapForOperationToFileWorkerList() {
        try (FileOutputStream fileToWrite = new FileOutputStream(FileWorkers);
             ObjectOutputStream objectToWrite = new ObjectOutputStream(fileToWrite)) {
            objectToWrite.writeObject(workerListOperation);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void readFormFileToWorkerListOperation() {
        try (FileInputStream fileToRead = new FileInputStream(FileWorkers);
             ObjectInputStream objectToRead = new ObjectInputStream(fileToRead)) {
            workerListOperation = (HashMap<Integer, Object>) objectToRead.readObject();
            if (workerListOperation.isEmpty()) {
                System.out.println("Список работников пуст");
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private static boolean isExistContact(String phoneNumber){
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        boolean checkValue = true;
        for (Map.Entry<Integer, Object> entry : workerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            if (workerList.getContactNumber().contains(phoneNumber)){
                checkValue = true;
                break;
            }
            checkValue = false;
        }
        workerListOperation.clear();
        return checkValue;
    }

    private static boolean isExistWorker(String inputIndex){
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        Integer index = Integer.parseInt(inputIndex);
        boolean checkValue = true;
        for (Map.Entry<Integer, Object> entry : workerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            if (workerList.getIndex().equals(index)){
                checkValue = true;
                break;
            }
            checkValue = false;
        }
        workerListOperation.clear();
        return checkValue;
    }

    private static Integer newIndex(){
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        int index = 1;
        for (Map.Entry<Integer, Object> entry : workerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            if (workerList.getIndex().equals(index)){
                index++;
            }
            else
                return index;
                break;
        }
        workerListOperation.clear();
        return index;
    }

    private static void showExistWorker(String inputIndex){
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        String sb = "";
        Integer findIndex = Integer.parseInt(inputIndex);
        for (Map.Entry<Integer, Object> entry : workerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            if (workerList.getIndex().equals(findIndex)){
                Integer index = Integer.parseInt(inputIndex);
                String name = workerList.getName();
                String lastName = workerList.getLastname();
                String patronymic = workerList.getPatronymic();
                Date brithDay = workerList.getBirthday();
                String sex = workerList.getSex();
                ArrayList<String> contactNumber = workerList.getContactNumber();
                String post = workerList.getPost();
                String department = workerList.getDepartment();
                String chief = workerList.getChief();
                Date dayOfAdmission = workerList.getDayOfAdmission();
                int salary = workerList.getSalary();
                String status = workerList.getStatus();
                sb = showTab(index, name, lastName, patronymic, brithDay, sex, contactNumber, post,
                        department, dayOfAdmission, salary, status, chief);
                System.out.print(sb);
            }
        }
        workerListOperation.clear();
    }

    private static void replaceChangeWorker(){
        addNewWorker();
        readFormFileToWorkerListOperation();
    }

    private static void topTenWorkersBySalary(){
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        for (Map.Entry<Integer, Object> entry : workerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            int topSalary = workerList.getSalary();
            int checkerSalary = workerList.getSalary();
            int stopSalary = 0;
            for(int numTop = 1; numTop < 11; numTop++){
                for (int top = 0; top < workerListOperation.size(); top++){
                    if (stopSalary > checkerSalary) {
                        stopSalary = topSalary;
                        topSalary = checkerSalary;
                        checkerSalary = workerList.getSalary();
                    }
                }
                System.out.println(numTop + ", " + "Индекс: " + workerList.getIndex()+ " ФИО: " +
                        workerList.getLastname() + " " + workerList.getName() + " " + workerList.getPatronymic()
                        + ", Зарплата: " + workerList.getSalary() + ".Руб");
            }
        }
        workerListOperation.clear();
    }

    private static void topTenWorkersByLongestWork(){
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        for (Map.Entry<Integer, Object> entry : workerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            Date topDate = workerList.getDayOfAdmission();
            Date checkerDate = workerList.getDayOfAdmission();
            Date endDate = workerList.getDayOfAdmission();
            for(int numTop = 1; numTop < 11; numTop++){
                for (int top = 0; top < workerListOperation.size(); top++){
                    if(endDate.getTime() > checkerDate.getTime()){
                        endDate = topDate;
                        topDate = checkerDate;
                        checkerDate = workerList.getDayOfAdmission();
                    }
                    System.out.println(numTop + ", " + "Индекс: " + workerList.getIndex()+ " ФИО: " +
                            workerList.getLastname() + " " + workerList.getName() + " " + workerList.getPatronymic()
                            + ", Зарплата: " + workerList.getDayOfAdmission() + ".Руб");
                }
            }
        }
        workerListOperation.clear();
    }
}
