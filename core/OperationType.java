package core;

public enum OperationType {

    ShowWorker("1","Показать Работников"),
    AddWorker("2","Добавить Работника"),
    RemoveWorker("3", "Удалить Работника"),
    ChangeWorker("4", "Изменить данные о работнике"),
    exit("5", "Выйти");

    private final String desc;
    private final String number;

    OperationType(String number, String desc) {
        this.number = number;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getNumber() {
        return number;
    }

    public static OperationType getEnumByUser(String number) {
        for (OperationType value : values()) {
            if (value.number.equals(number)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(number);
        sb.append(desc);
        sb.append("\n");
        return sb.toString();
    }
}
