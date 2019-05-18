package savedPrevious;

import java.io.Serializable;

public class Fio implements Serializable {
    private String firstName;
    private String lastName;
    private String middleName;

    Fio() {
        this(null, null, null);
    }

    Fio(String first) {
        this(first, null, null);
    }

    Fio(String first, String lastName) {
        this(first, lastName, null);
    }

    Fio(String first, String lastName, String middle) {
        this.firstName = first;
        this.lastName = lastName;
        this.middleName = middle;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }
}
