package savedPrevious;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private Fio fio;
    private Date birthDate;

    public Person() {
        this(null, null);
    }

    public Person(Fio fio) {
        this(fio, null);
    }

    public Person(Fio fio, Date birthDate) {
        this.fio = fio;
        this.birthDate = birthDate;
    }

    public void setFio(Fio fio) {
        this.fio = fio;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Fio getFio() {
        return this.fio;
    }

    public String getLastname() {
        if (this.fio == null) {
            return null;
        }

        return this.fio.getLastName();
    }

    public Integer getAge() {
        if (this.birthDate == null) {
            return null;
        }

        return (int)calculateAge();
    }

    public String getGender() {
        if (this.fio == null) {
            return null;
        }

        if (isFemaleMiddleName(this.fio.getMiddleName())) {
            return "Female";
        }

        if (isMaleMiddleName(this.fio.getMiddleName())) {
            return "Male";
        }

        return "Unknown";
    }

    public String toString() {
        return getGender() + " " + getLastname() + " . Возраст:" + getAge();
    }

    private boolean isFemaleMiddleName(String middleName) {
        return middleName
                .toLowerCase()
                .toCharArray()[middleName.length() - 1] == 'а';
    }

    private boolean isMaleMiddleName(String middleName) {
        return middleName
                .toLowerCase()
                .toCharArray()[middleName.length() - 1] == 'ч';
    }

    private long calculateAge() {
        Date now = new Date();

        long differenceInSeconds = (now.getTime() - this.birthDate.getTime()) / 1000;
        long yearInSeconds = 60 * 60 * 24 * 365;

        return differenceInSeconds / yearInSeconds;
    }
}