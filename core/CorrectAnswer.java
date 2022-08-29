package core;

public enum CorrectAnswer {

    YES("1", "Да"),
    NO("2", "Нет");

    private String number;
    private String status;

    CorrectAnswer(String number, String status) {
        this.number = number;
        this.status = status;
    }

    public static CorrectAnswer getEnumByNumber(String number){
        for (CorrectAnswer value : values()){
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
