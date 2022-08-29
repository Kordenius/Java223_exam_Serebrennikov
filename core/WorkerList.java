package core;

public class WorkerList {

    private String index;
    private String name;
    private String patronymic;
    private String lastname;
    private String birthday;
    private String sex;
    private String contactNumber;
    private String post;
    private String department;
    private String chief;
    private String dayOfAdmission;
    private String salary;
    private String status;

    public WorkerList(String index, String name, String patronymic, String lastname, String birthday, String sex, String contactNumber, String post, String department, String chief, String dayOfAdmission, String salary, String status) {
        this.index = index;
        this.name = name;
        this.patronymic = patronymic;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
