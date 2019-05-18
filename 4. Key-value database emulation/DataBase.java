package savedPrevious;

import java.io.*;
import java.util.HashMap;

public class DataBase<T> {
    private HashMap<Integer, T> map;
    private int idCounter = 0;

    DataBase() {
        map = new HashMap<>();
    }

    public int add(T item) {
        map.put(++idCounter, item);

        return idCounter;
    }

    public boolean edit(int id, T item) {
        if (map.containsKey(id) == false) {
            return false;
        }

        map.put(id, item);

        return true;
    }

    public boolean remove(int id) {
        if (map.containsKey(id) == false) {
            return false;
        }

        map.remove(id);

        return true;
    }

    public void flush() {
        map.clear();
    }

    public T get(int id) {
        if (map.containsKey(id) == false) {
            return null;
        }

        return map.get(id);
    }

    public void openFromFile(String filename) {
        try {
            FileInputStream inFile$ = new FileInputStream(filename);
            ObjectInputStream inputStream$ = new ObjectInputStream(inFile$);

            try {
                this.map = (HashMap<Integer, T>)inputStream$.readObject();
                System.out.println("База успешно загружена");
            }
            finally {
                inFile$.close();
                inputStream$.close();
            }
        }
        catch (Exception ex) {
            System.out.println("Ошибка чтения из файла" + ex);
            return;
        }
    }

    public void saveIntoFile(String filename) {
        try {
            this.serializeToFile(filename);
        }
        catch (IOException ex) {
            System.out.println("Не удалось произвести запись в файл");
            return;
        }
    }

    private void serializeToFile(String filename) throws IOException {
        FileOutputStream outFile$ = new FileOutputStream(filename);
        ObjectOutputStream objOutput$ = new ObjectOutputStream(outFile$);

        try {
            objOutput$.writeObject(this.map);
        }
        finally {
            outFile$.close();
            objOutput$.close();
        }
    }
}
