package core;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.EmptyStackException;
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
                case ShowWorker -> show(WorkerList, scanner);
                case AddWorker -> add(WorkerList, scanner);
                case RemoveWorker -> remove(WorkerList, scanner);
                case ChangeWorker -> change(WorkerList, scanner);
                case CHECK -> check();
                case exit -> {
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

    }

    private static void remove(LinkedList<WorkerList> WorkerList, Scanner scanner) {

    }

    private static void add(LinkedList<WorkerList> WorkerList, Scanner scanner) {
        String newIndex = "1";
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
        String StatusWorker = null;
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
                case FIRED -> {
                    System.out.println("Данный статус недоступен для нового работника");
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Индекс: ").append(1).append('\n');
        sb.append("Имя: ").append(nameNewWorker).append('\n');
        sb.append("Отчество: ").append(patronymicNewWorker).append('\n');
        sb.append("Фамилия: ").append(lastnameNewWorker).append('\n');
        sb.append("День Рождение: ").append(brithDayNewWorker).append('\n');
        sb.append("Пол: ").append(sexNewWorker).append('\n');
        sb.append("контактный телефон: ").append(contactNumberNewWorker).append('\n');
        sb.append("Занимаемый пост: ").append(postNewWorker).append('\n');
        sb.append("отдел: ").append(departmentNewWorker).append('\n');
        sb.append("Начальник: ").append("index").append('\n');
        sb.append("День принятия на работу: ").append(dayOfAdmissionNewWorker).append('\n');
        sb.append("Зарплата: ").append(salaryNewWorker).append(".Руб").append('\n');
        sb.append("Статус: ").append(StatusWorker).append('\n');

        System.out.print(sb);
        System.out.println("Ведённые данные правильны?");
        outer:
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
                    try {
                        //WorkerList.add(newIndex, nameNewWorker, patronymicNewWorker, lastnameNewWorker, brithDayNewWorker,
                        //        sexNewWorker, contactNumberNewWorker, postNewWorker, departmentNewWorker, "123",
                        //        dayOfAdmissionNewWorker, salaryNewWorker, StatusWorker);
                        break outer;
                    }
                    catch (Exception e){
                        System.out.println();
                    }
                }
                case NO -> {
                    break outer;
                }
            }
        }
    }

    private static void show(LinkedList<WorkerList> WorkerList, Scanner scanner) {

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
        return (Arrays.toString(WorkerStatus.values()).
                replace("[", "")).
                replace("]", "").
                replace(", ", "").
                trim();
    }
}
