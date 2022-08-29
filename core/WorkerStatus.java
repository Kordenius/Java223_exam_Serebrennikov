package core;

public enum WorkerStatus {

    ACTIVE("1", "Активен"),
    HIRED("2", "Нанят"),
    ON_VACATION("3", "На отдыхе"),
    ON_SICK_LEAVE("4", "На больничном"),
    FIRED("5", "Уволен");

    private String number;
    private String status;

    WorkerStatus(String number, String status) {
        this.number = number;
        this.status = status;
    }

    public static WorkerStatus getEnumByNumber(String number){
        for (WorkerStatus value : values()){
            if (value.number.equals(number))
                return value;
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(number).append(" ");
        sb.append(status).append('\n');
        return sb.toString();
    }
}
