package core;

public enum ChangeWorker {

    CHANGE_NAME("1", "Изменить имя"),
    CHANGE_LASTNAME("2", "Изменить фамилию"),
    CHANGE_PATRONYMIC("3","Изменить отчество"),
    CHANGE_CONTACTNUMBER("4","Изменить контактный телефон"),
    CHANGE_POST("5","Изменить пост"),
    CHANGE_DEPARTMENT("6","Изменить департамент"),
    CHANGE_CHIEF("7","Изменить начальника"),
    CHANGE_SALARY("8","Изменить зарплату"),
    CHANGE_STATUS("9","Изменить статус"),
    EXIT("10","Сохранить изменения и выйти");

    private String number;
    private String status;

    ChangeWorker(String number, String status) {
        this.number = number;
        this.status = status;
    }

    public static ChangeWorker getEnumByNumber(String number){
        for (ChangeWorker value : values()){
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
