package core;

public class WorkerList {

    private int index;
    private String name;
    private String surname;
    private String lastname;
    private String birthday;
    private String sex;
    private int contactNumber;
    private String post;
    private String department;
    private String chief;
    private String dayOfAdmission;
    private int salary;
    private String status;

    public WorkerList(int index, String name, String surname, String lastname, String birthday, String sex, int contactNumber, String post, String department, String chief, String dayOfAdmission, int salary, String status) {
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.sex = sex;
        this.contactNumber = contactNumber;
        this.post = post;
        this.department = department;
        this.chief = chief;
        this.dayOfAdmission = dayOfAdmission;
        this.salary = salary;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public String getDayOfAdmission() {
        return dayOfAdmission;
    }

    public void setDayOfAdmission(String dayOfAdmission) {
        this.dayOfAdmission = dayOfAdmission;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
