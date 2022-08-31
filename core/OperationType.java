package core;

public enum OperationType {

    SHOW_WORKER("1","Показать Работников"),
    ADD_WORKER("2","Добавить Работника"),
    REMOVE_WORKER("3", "Удалить Работника"),
    CHANGE_WORKER("4", "Изменить данные о работнике"),
    CHECK("5","Проверка состояния файла"),
    EXIT("6", "Выйти");

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
        sb.append(number).append(". ");
        sb.append(desc);
        sb.append("\n");
        return sb.toString();
    }
}
