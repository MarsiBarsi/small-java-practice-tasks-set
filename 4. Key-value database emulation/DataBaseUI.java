package savedPrevious;

import java.util.Scanner;

public class DataBaseUI {
    DataBaseUI() {}

    public void interact() {
        DataBase<Person> personDb = new DataBase<>();
        Scanner in = new Scanner(System.in);

        while (true) {
            this.printInfo();

            int command = in.nextInt();

            switch (command) {
                case 1:
                    personDb.openFromFile("db.txt");
                    break;
                case 2:
                    this.addNewPersonFromConsole(personDb);
                    break;
                case 3:
                    this.inputToEdit(personDb);
                    break;
                case 4:
                    this.inputIdToDelete(personDb);
                    break;
                case 5:
                    personDb.saveIntoFile("db.txt");
                    break;
                case 6:
                    this.printById(personDb);
                    break;
                case 0:
                    return;
            }
        }
    }

    private void addNewPersonFromConsole(DataBase<Person> base) {
        PersonUI personUI = new PersonUI();

        Person person = personUI.inputNewPerson();

        int id = base.add(person);
        System.out.println("Запись произведена. Id записи: " + id);

    }

    private void inputToEdit(DataBase db) {
        Scanner in = new Scanner(System.in);

        int id = in.nextInt();

        Person newPerson = new Person();

        boolean success = db.edit(id, newPerson);

        System.out.println(success ? "Успешно изменено" : "Изменение не получилось");
    }

    private void inputIdToDelete(DataBase db) {
        Scanner in = new Scanner(System.in);

        int id = in.nextInt();

        boolean success = db.remove(id);

        System.out.println(success ? "Успешно удалено" : "Удаление не получилось");
    }

    private void printById(DataBase db) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите id:");
        int id = in.nextInt();

        Person person = (Person)db.get(id);

        if (person == null) {
            System.out.println("Такой записи нет");

            return;
        }

        System.out.println(person);
    }

    private void printInfo() {
        System.out.println("Введите команду:");
        System.out.println("1. Загрузить базу из файла");
        System.out.println("2. Добавить из консоли");
        System.out.println("3. Изменить элемент базы по id");
        System.out.println("4. Удалить элемент базы по id");
        System.out.println("5. Сохранить базу в файл");
        System.out.println("6. Посмотреть по id");
        System.out.println("0. Выход");
    }
}
