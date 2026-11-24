package lab.mvc.model;

import java.io.Serializable;

/** TP 3.2 -- the Model of the MVC application (constructor with the four attributes). */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String birthDay;   // mm/dd/yyyy
    private String sex;

    public Person(String firstName, String lastName, String birthDay, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.sex = sex;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBirthDay() { return birthDay; }
    public void setBirthDay(String birthDay) { this.birthDay = birthDay; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
}
