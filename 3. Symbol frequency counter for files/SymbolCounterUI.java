package savedPrevious;

import java.io.*;
import java.util.Scanner;

public class SymbolCounterUI {
    SymbolCounterUI() {}

//    public void interact() {
//        Scanner userFile$;
//
//        try {
//            userFile$ = this.createFileScanner();
//        }
//        catch (FileNotFoundException exception) {
//            System.out.println("Не удалось получить доступ к файлу");
//            return;
//        }
//
//        savedPrevious.SymbolCounter counter = new savedPrevious.SymbolCounter();
//
//        while (userFile$.hasNext()) {
//            char[] line;
//
//            line = userFile$.nextLine().toCharArray();
//            counter.addCharArray(line);
//        }
//
//        try {
//            this.saveTextIntoStatsFile(counter.toString());
//        }
//        catch (IOException ex) {
//            System.out.println("Не произвести запись в файл");
//            return;
//        }
//    }
//
//    private Scanner createFileScanner() throws FileNotFoundException {
//        File userFile = this.requestUserFile();
//
//        return new Scanner(userFile);
//    }
//
//    private File requestUserFile() {
//        File file;
//
//        do {
//            String newPath = getFilePathFromConsole();
//            file = new File(newPath);
//
//            if (file.exists() == false) {
//                System.out.println("Данного файла не существует, попробуйте снова");
//            }
//        } while (file.exists() == false);
//
//        return file;
//    }
//
//    private String getFilePathFromConsole() {
//        String result;
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("Введите путь к файлу");
//        result = in.next();
//
//        return result;
//    }
//
//    private void saveTextIntoStatsFile(String text) throws IOException {
//        FileWriter outFile = new FileWriter("stats.txt", true);
//
//        try {
//            PrintWriter writer = new PrintWriter(outFile);
//
//            try {
//                writer.print(text);
//            }
//            finally {
//                writer.close();
//            }
//        }
//        finally {
//            outFile.close();
//        }
//    }
}
