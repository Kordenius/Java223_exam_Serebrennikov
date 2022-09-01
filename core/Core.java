package core;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

public class Core {

    private static final File FileWorkersPath = new File("core/Workers");
    private static final File FileWorkers = new File("core/Workers/Workers.txt");

    private static HashMap<String, Object> WorkerListOperation = new HashMap<>();

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        check(FileWorkers, FileWorkersPath);

        Scanner scanner = new Scanner(System.in);

        printStartOperationDialog();

        outer:
        while (scanner.hasNextLine()) {
            String currentOperation = scanner.nextLine();
            OperationType enumByUser = OperationType.getEnumByUser(currentOperation);
            if (enumByUser==null) {
                System.out.println("Операция не найдена!");
                printStartOperationDialog();
                continue;
            }
            switch (enumByUser) {
                case SHOW_WORKER -> show(scanner);
                case ADD_WORKER -> add(scanner);
                case REMOVE_WORKER -> remove(scanner);
                case CHANGE_WORKER -> change(scanner);
                case CHECK -> check(FileWorkers, FileWorkersPath);
                case EXIT -> {
                    break outer;
                }
                default -> System.out.println("Операция не найдена!");
            }

            printStartOperationDialog();
        }
        scanner.close();
    }

    private static void check(File FileWorkers, File FileWorkersPath) {
        if (FileWorkers.isFile()) {
            System.out.println("База данных на текущий момент существует");
        }
        try {
            boolean FilePath = FileWorkersPath.mkdirs();
            boolean File = FileWorkers.createNewFile();
            if (FilePath & File) {
                System.out.println("Создана новая база данных");
            }
            writeMapForOperationToFileWorkerList();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void change(Scanner scanner) {

    }

    private static void remove(Scanner scanner) {
        System.out.println("Ведите индекс искомого работника: ");
        String inputIndex = scanner.nextLine();

    }

    private static void add(Scanner scanner) {
        System.out.println("Ведите имя нового работника");
        String nameNewWorker = scanner.nextLine();
        System.out.println("Ведите отчество нового работника");
        String patronymicNewWorker = scanner.nextLine();
        System.out.println("Ведите фамилию нового работника");
        String lastnameNewWorker = scanner.nextLine();
        Calendar brithDayNewWorker = null;
        System.out.println("Ведите год рождения нового работника");
        int brithDayYear = scanner.nextInt();
        System.out.println("Ведите месяц рождения нового работника");
        int brithDayMount = scanner.nextInt();
        System.out.println("Ведите день рождения нового работника");
        int brithDay = scanner.nextInt();
        brithDayNewWorker.set(brithDayYear,brithDayMount,brithDay);
        System.out.println("Ведите пол нового работника");
        String sexNewWorker = scanner.nextLine();
        System.out.println("Ведите контактный телефон нового работника, используя только цифры");
        String contactNumberNewWorker = scanner.nextLine();
        String[] newWorkerContactNumberArraySplit = contactNumberNewWorker.split(" ");
        ArrayList<String> newWorkerContactNumberArray = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(\\+7)\\d{10}");
        for (String current : newWorkerContactNumberArraySplit){
            if (pattern.matcher(current).matches()){
                System.out.println("Ведённый телефон соответствует формату");
            }
            else System.out.println("Невозможно добавить телефон. \n Телефон не соответствует формату.");
            if (isExistContact(current)){
                System.out.println("Такой номер существует");
            }
            else {
                newWorkerContactNumberArray.add(current);
                System.out.println("Телефон был добавлен");
            }
        }
        System.out.println("Ведите число руководителя нового работника, используя только цифры");
        printChiefOperationDialog();
        String Chief = scanner.nextLine();
        System.out.println("Ведите занимаемый пост новым работником");
        String postNewWorker = scanner.nextLine();
        System.out.println("Ведите департамент занимаемый новым работником");
        String departmentNewWorker = scanner.nextLine();
        System.out.println("Ведите день принятия нового работника");
        Calendar dayOfAdmissionNewWorker = null;
        System.out.println("Ведите год рождения нового работника");
        int admissionDayYear = scanner.nextInt();
        System.out.println("Ведите месяц рождения нового работника");
        int admissionDayMount = scanner.nextInt();
        System.out.println("Ведите день рождения нового работника");
        int admissionDay = scanner.nextInt();
        dayOfAdmissionNewWorker.set(admissionDayYear,admissionDayMount,admissionDay);
        System.out.println("Ведите зарплату нового работника");
        int salaryNewWorker = scanner.nextInt();
        System.out.println("Ведите статус нового работника");
        String StatusWorker;
        outer:
        while (true) {
            printWorkerStatusOperationDialog();
            String statusNewWorker = scanner.nextLine();
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

        String sb = "Индекс: " + 1 + '\n' +
                "Имя: " + nameNewWorker + '\n' +
                "Отчество: " + patronymicNewWorker + '\n' +
                "Фамилия: " + lastnameNewWorker + '\n' +
                "День Рождение: " + brithDayNewWorker + '\n' +
                "Пол: " + sexNewWorker + '\n' +
                "контактный телефон: " + contactNumberNewWorker + '\n' +
                "Занимаемый пост: " + postNewWorker + '\n' +
                "отдел: " + departmentNewWorker + '\n' +
                "Начальник: " + "index" + '\n' +
                "День принятия на работу: " + dayOfAdmissionNewWorker + '\n' +
                "Зарплата: " + salaryNewWorker + ".Руб" + '\n' +
                "Статус: " + StatusWorker + '\n';

        System.out.print(sb);
        System.out.println("Ведённые данные правильны?");
        outer:
        while (true){
            printCorrectAnswerStatusOperationDialog();
            String answer = scanner.nextLine();
            CorrectAnswer enumByUser = CorrectAnswer.getEnumByNumber(answer);
            if (enumByUser==null) {
                System.out.println("Операция не найдена!");
                printStartOperationDialog();
                continue;
            }
            switch (enumByUser){
                case YES -> {
                    WorkerList newWorker = new WorkerList(nameNewWorker, patronymicNewWorker,lastnameNewWorker,brithDayNewWorker,
                            sexNewWorker, newWorkerContactNumberArray ,postNewWorker,departmentNewWorker,Chief, dayOfAdmissionNewWorker,
                            salaryNewWorker,StatusWorker,1);
                    readFormFileToWorkerListOperation();
                    WorkerListOperation.putIfAbsent(newWorker.getIndex(), newWorker);
                    break outer;
                }
                case NO -> {
                    System.out.println("Операция переврана");
                    break outer;
                }
            }
        }

    }

    private static void show(Scanner scanner) {
        System.out.println("Ведите индекс искомого пользователя: ");
        String inputIndex = scanner.nextLine();

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
            objectToWrite.writeObject(WorkerListOperation);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void readFormFileToWorkerListOperation(){
        try (FileInputStream fileToRead = new FileInputStream(FileWorkers);
            ObjectInputStream objectToRead = new ObjectInputStream(fileToRead)){
            WorkerListOperation = (HashMap<String, Object>) objectToRead.readObject();
            if (WorkerListOperation.isEmpty()){
                System.out.println("Список работников пуст");
            }
        }
        catch (IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }

    private static boolean isExistContact(String phoneNumber){
        WorkerListOperation.clear();
        readFormFileToWorkerListOperation();
        boolean checkValue = true;
        for (Map.Entry<String, Object> entry :WorkerListOperation.entrySet()){
            WorkerList workerList = (WorkerList) entry.getValue();
            if (workerList.getContactNumber().contains(phoneNumber)){
                checkValue = true;
                break;
            }
            checkValue = false;
        }
        WorkerListOperation.clear();
        return checkValue;
    }
}
