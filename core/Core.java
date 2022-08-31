package core;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Core {

    public static void main(String[] args){
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        LinkedList<WorkerList>WorkerList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        printStartOperationDialog();

        outer:
        while (scanner.hasNextLine()) {
            String currentOperation = scanner.nextLine();
            OperationType enumByUser = OperationType.getEnumByUser(currentOperation);
            if (enumByUser == null) {
                System.out.println("Операция не найдена!");
                printStartOperationDialog();
                continue;
            }
            switch (enumByUser) {
                case SHOW_WORKER -> show(WorkerList, scanner);
                case ADD_WORKER -> add(WorkerList, scanner);
                case REMOVE_WORKER -> remove(WorkerList, scanner);
                case CHANGE_WORKER -> change(WorkerList, scanner);
                case CHECK -> check();
                case EXIT -> {
                    break outer;
                }
                default -> System.out.println("Операция не найдена!");
            }

            printStartOperationDialog();
        }
        scanner.close();
    }

    private static void check(){
        File FileWorkers = new File("core/Workers/Workers.txt");
        if (!FileWorkers.exists()){
            try {
                if (FileWorkers.createNewFile()){
                    System.out.println("Файл был создан");
                }
            }
            catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    private static void change(LinkedList<WorkerList> WorkerList, Scanner scanner) {
        System.out.println("Ведите индекс искомого пользователя: ");
        String inputIndex = scanner.nextLine();
        for(WorkerList Workers : WorkerList) {
            if (Workers.getIndex().equals(inputIndex)) {

            }
        }
    }

    private static void remove(LinkedList<WorkerList> WorkerList, Scanner scanner) {
        System.out.println("Ведите индекс искомого работника: ");
        String inputIndex = scanner.nextLine();
        for(WorkerList Workers : WorkerList) {
            if (Workers.getIndex().equals(inputIndex)) {
                outer:
                while (true) {
                    System.out.println("Ведите нужное число");
                    String answer = scanner.nextLine();
                    printCorrectAnswerStatusOperationDialog();
                    CorrectAnswer getEnumByNumber = CorrectAnswer.getEnumByNumber(answer);
                    if (getEnumByNumber==null) {
                        System.out.println("Статус не найден");
                        continue;
                    }
                    switch (getEnumByNumber) {
                        case YES -> {
                            WorkerList.removeIf(currentUser -> Workers.getIndex().equals(inputIndex));
                            System.out.println("работник Удалён.");
                            break outer;
                        }
                        case NO -> {
                            System.out.println("Операция остановлена");
                            break outer;
                        }
                    }
                }
            }
        }
    }

    private static void add(LinkedList<WorkerList> WorkerList, Scanner scanner) {
        //for (WorkerList Worker : WorkerList){
        //    String newIndex = String.valueOf(WorkerList.get(WorkerList.lastIndexOf(Worker.getIndex())));
        System.out.println("Ведите имя нового работника");
        String nameNewWorker = scanner.nextLine();
        System.out.println("Ведите отчество нового работника");
        String patronymicNewWorker = scanner.nextLine();
        System.out.println("Ведите фамилию нового работника");
        String lastnameNewWorker = scanner.nextLine();
        System.out.println("Ведите день рождения нового работника");
        String brithDayNewWorker = scanner.nextLine();
        System.out.println("Ведите пол нового работника");
        String sexNewWorker = scanner.nextLine();
        System.out.println("Ведите контактный телефон нового работника, используя только цифры");
        String contactNumberNewWorker = scanner.nextLine();
        System.out.println("Ведите число руководителя нового работника, используя только цифры");
        System.out.println("Ведите занимаемый пост новым работником");
        String postNewWorker = scanner.nextLine();
        System.out.println("Ведите департамент занимаемый новым работником");
        String departmentNewWorker = scanner.nextLine();
        System.out.println("Ведите день принятия нового работника");
        String dayOfAdmissionNewWorker = scanner.nextLine();
        System.out.println("Ведите зарплату нового работника");
        String salaryNewWorker = scanner.nextLine();
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
            outers:
            while (true) {
                printCorrectAnswerStatusOperationDialog();
                String answer = scanner.nextLine();
                CorrectAnswer getEnumByNumber = CorrectAnswer.getEnumByNumber(answer);
                if (getEnumByNumber==null) {
                    System.out.println("Статус не найден");
                    continue;
                }
                switch (getEnumByNumber) {
                    case YES -> {
                            try (InputStream inputStream = new FileInputStream("core/Workers/Workers.txt");
                                OutputStream outputStream = new FileOutputStream("core/Workers/Workers.txt")){
                            //Worker.add(1, newIndex, nameNewWorker, patronymicNewWorker, lastnameNewWorker, brithDayNewWorker,
                            //        sexNewWorker, contactNumberNewWorker, postNewWorker, departmentNewWorker, "123",
                            //        dayOfAdmissionNewWorker, salaryNewWorker, StatusWorker);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            break outers;
                    }
                    case NO -> {
                        break outers;
                    }
                }
            }
        }
    //}

    private static void show(LinkedList<WorkerList> WorkerList, Scanner scanner) {
        System.out.println("Ведите индекс искомого пользователя: ");
        String inputIndex = scanner.nextLine();
        for(WorkerList Workers : WorkerList){
            if (Workers.getIndex().equals(inputIndex)){
                String sb = "Индекс: " + inputIndex + '\n' +
                        "Имя: " + Workers.getName() + '\n' +
                        "Отчество: " + Workers.getPatronymic() + '\n' +
                        "Фамилия: " + Workers.getLastname() + '\n' +
                        "День Рождение: " + Workers.getBirthday() + '\n' +
                        "Пол: " + Workers.getSex() + '\n' +
                        "контактный телефон: " + Workers.getContactNumber() + '\n' +
                        "Занимаемый пост: " + Workers.getPost() + '\n' +
                        "отдел: " + Workers.getDepartment() + '\n' +
                        "Начальник: " + Workers.getChief() + '\n' +
                        "День принятия на работу: " + Workers.getDayOfAdmission() + '\n' +
                        "Зарплата: " + Workers.getSalary() + ".Руб" + '\n' +
                        "Статус: " + Workers.getStatus() + '\n';
                System.out.println(sb);
            }
        }
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
}
