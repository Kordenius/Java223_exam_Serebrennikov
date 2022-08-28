package core;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Core {

    public static void main(String[] args){
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        LinkedList<String>WorkerList = new LinkedList<>();
        File fileWorker = new File(new File("core/Workers"),"WorkerList.txt");
        fileWorker.mkdirs();
        if (!fileWorker.exists()){
            try {

            }
            catch (Exception e){

            }
        }
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
                case ShowWorker -> show();
                case AddWorker -> add();
                case RemoveWorker -> remove();
                case ChangeWorker -> change();
                case exit -> {
                    System.out.println("Good Bye!");
                    break outer;
                }
                default -> System.out.println("Операция не найдена!");
            }

            printStartOperationDialog();
        }
        scanner.close();
    }

    private static void change() {

    }

    private static void remove() {

    }

    private static void add() {

    }

    private static void show() {

    }

    private static void printStartOperationDialog() {
        System.out.println("Ведите число функции: ");
        System.out.println(getOperations());
    }

    private static void printChangeOperationDialog() {
        System.out.println("Ведите число функции: ");
        System.out.println(getOperations());
    }

    private static String getOperations() {
        return (Arrays.toString(OperationType.values()).
                replace("[", "")).
                replace("]", "").
                replace(", ", "").
                trim();
    }
}
