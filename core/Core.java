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
                case REMOVE_WORKER -> removeWorker(scanner);
                case CHANGE_WORKER -> changeWorker(scanner);
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
        }
        try {
            boolean FilePath = Core.FileWorkersPath.mkdirs();
            boolean File = Core.FileWorkers.createNewFile();
            if (FilePath & File) {
                System.out.println("Создана новая база данных");
            }
            writeMapForOperationToFileWorkerList();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void changeWorker(Scanner scanner) {

    }

    private static void removeWorker(Scanner scanner) {
        System.out.println("Ведите индекс искомого работника: ");
        String inputIndex = scanner.nextLine();

    }

    private static void addNewWorker() {
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ведите имя нового работника");
        String nameNewWorker = scanner.nextLine();
        System.out.println("Ведите фамилию нового работника");
        String lastnameNewWorker = scanner.nextLine();
        System.out.println("Ведите отчество нового работника");
        String patronymicNewWorker = scanner.nextLine();
        Date brithDayNewWorker = brithDayNewWorker();
        System.out.println("Ведите пол нового работника");
        String sexNewWorker = scanner.nextLine();
        ArrayList<String> newWorkerContactNumberArray = getNewWorkerContactNumber();
        System.out.println("Ведите число руководителя нового работника, используя только цифры");
        //printChiefOperationDialog();
        String Chief = scanner.nextLine();
        System.out.println("Ведите занимаемый пост новым работником");
        String postNewWorker = scanner.nextLine();
        System.out.println("Ведите департамент занимаемый новым работником");
        String departmentNewWorker = scanner.nextLine();
        Date dayOfAdmissionNewWorker = dayOfAdmission();
        System.out.println("Ведите зарплату нового работника");
        int salaryNewWorker = scanner.nextInt();
        String StatusWorker = newWorkerStatus();

        String sb = "Индекс: " + 1 + '\n' +
                "Имя: " + nameNewWorker + '\n' +
                "Фамилия: " + lastnameNewWorker + '\n' +
                "Отчество: " + patronymicNewWorker + '\n' +
                "День Рождение: " + brithDayNewWorker + '\n' +
                "Пол: " + sexNewWorker + '\n' +
                "контактный телефон: " + newWorkerContactNumberArray + '\n' +
                "Занимаемый пост: " + postNewWorker + '\n' +
                "отдел: " + departmentNewWorker + '\n' +
                "Начальник: " + "index" + '\n' +
                "День принятия на работу: " + dayOfAdmissionNewWorker + '\n' +
                "Зарплата: " + salaryNewWorker + ".Руб" + '\n' +
                "Статус: " + StatusWorker + '\n';

        System.out.print(sb);
        System.out.println("Ведённые данные корректны?");
        outer:
        while (true){
            printCorrectAnswerStatusOperationDialog();
            String answer = scanner.nextLine();
            CorrectAnswer enumByUser = CorrectAnswer.getEnumByNumber(answer);
            if (enumByUser==null) {
                System.out.println("Операция не найдена!");
                continue;
            }
            switch (enumByUser){
                case YES -> {
                    WorkerList newWorker = new WorkerList(nameNewWorker, patronymicNewWorker,lastnameNewWorker,brithDayNewWorker,
                            sexNewWorker, newWorkerContactNumberArray ,postNewWorker,departmentNewWorker,Chief, dayOfAdmissionNewWorker,
                            salaryNewWorker,StatusWorker,1);
                    readFormFileToWorkerListOperation();
                    workerListOperation.putIfAbsent(newWorker.getIndex() + 1, newWorker);
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

    private static String newWorkerStatus() {
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
        System.out.println("Ведите контактный телефон нового работника, используя только цифры");
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
        Date date1 = brithDayNewWorker.getTime();
        return date1;

    }

    private static void showWorker() {
        Scanner showScan = new Scanner(System.in);
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        System.out.println("Ведите индекс искомого пользователя: ");
        String inputIndex = showScan.nextLine();
        if (isExistWorker(inputIndex)){
            System.out.println("Пользователь найден");
        }
        else
            System.out.println("Пользователь не найден");
    }

    private static void printStartOperationDialog() {
        System.out.println("Ведите число функции: ");
        System.out.println(getOperations());
    }

    private static void printChangeOperationDialog() {
        System.out.println("Ведите число функции: ");
        System.out.println(getOperations());
    }

    private static void printChiefOperationDialog() {
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

    private static void writeMapForOperationToFileWorkerList() {
        try (FileOutputStream fileToWrite = new FileOutputStream(FileWorkers);
             ObjectOutputStream objectToWrite = new ObjectOutputStream(fileToWrite)) {
            objectToWrite.writeObject(workerListOperation);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void readFormFileToWorkerListOperation(){
        try (FileInputStream fileToRead = new FileInputStream(FileWorkers);
            ObjectInputStream objectToRead = new ObjectInputStream(fileToRead)){
            workerListOperation = (HashMap<Integer, Object>) objectToRead.readObject();
            objectToRead.close();
            if (workerListOperation.isEmpty()){
                System.out.println("Список работников пуст");
            }
        }
        catch (IOException | ClassNotFoundException exception){
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

    private static Integer getLastIndexWorker(){
        Integer getIndexWorker = null;

        return getIndexWorker;
    }

    private static boolean isExistWorker(String inputIndex){
        workerListOperation.clear();
        readFormFileToWorkerListOperation();
        boolean checkValue = true;
        for (Map.Entry<Integer, Object> entry : workerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            if (workerList.getIndex().equals(inputIndex)){
                checkValue = true;
                break;
            }
            checkValue = false;
        }
        workerListOperation.clear();
        return checkValue;
    }
}
