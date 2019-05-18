package savedPrevious;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PersonUI {
    PersonUI() {}

    public void interact() {
        Person person = this.inputNewPerson();

        System.out.println("Фамилия " + person.getLastname());
        System.out.println("Полных лет " + person.getAge());
        System.out.println("Гендер " + person.getGender());
    }

    public Person inputNewPerson() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите имя: ");
        String first = in.nextLine();

        System.out.println("Введите фамилию: ");
        String last = in.nextLine();

        System.out.println("Введите отчество: ");
        String middle = in.nextLine();

        Fio fio = new Fio(first, last, middle);

        Date birthDate = null;
        do {
            try {
                System.out.println("Введите день рождения в формате дд.мм.гггг: ");
                String candidate = in.nextLine();
                birthDate = parseDate(candidate);
            }
            catch (java.text.ParseException e) {
                System.out.println("Некорректная дата, попробуйте еще");
            }
        } while (birthDate == null);

        return new Person(fio, birthDate);
    }

    private Date parseDate(String str_date) throws java.text.ParseException{
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("dd.mm.yyyy");
        date = formatter.parse(str_date);

        return date;
    }
}
