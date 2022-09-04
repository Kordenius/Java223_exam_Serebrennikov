package core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class WorkerList implements Serializable {

    @Serial
    private static final long serialVersionUID = 21535436;

    private final Integer index;
    private String name;
    private String patronymic;
    private String lastname;
    private Date birthday;
    private String sex;
    private ArrayList<String> contactNumber;
    private String post;
    private String department;
    private String chief;
    private Date dayOfAdmission;
    private int salary;
    private String status;

    public WorkerList(String name, String patronymic, String lastname, Date birthday, String sex,
                      ArrayList<String> contactNumber, String post, String department, String chief,
                      Date dayOfAdmission, int salary, String status,Integer index) {
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
        this.index = index;
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

    public ArrayList<String> getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(ArrayList<String> contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDayOfAdmission() {
        return dayOfAdmission;
    }

    public void setDayOfAdmission(Date dayOfAdmission) {
        this.dayOfAdmission = dayOfAdmission;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        WorkerList that = (WorkerList) o;
        return Objects.equals(name, that.name) && Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(lastname, that.lastname) && Objects.equals(birthday, that.birthday) &&
                Objects.equals(sex, that.sex) && Objects.equals(contactNumber, that.contactNumber) &&
                Objects.equals(post, that.post) && Objects.equals(department, that.department) &&
                Objects.equals(chief, that.chief) && Objects.equals(dayOfAdmission, that.dayOfAdmission) &&
                Objects.equals(salary, that.salary) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, patronymic, lastname, birthday, sex, contactNumber, post, department, chief, dayOfAdmission, salary, status);
    }
}
