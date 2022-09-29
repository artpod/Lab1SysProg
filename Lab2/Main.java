import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            try {
                /*System.out.format("Введіть ім'я файлу з автоматом: ");
                String pathname = scanner.next();
                NFA nfa = new NFA(pathname);*/
                NFA nfa = new NFA("test.txt");

                System.out.format("Введіть слово для обробки: ");
                String word = scanner.next();


                Set<Integer> states = nfa.processWord(word);
                if (states.isEmpty()) System.out.println("Слово не може бути оброблено автоматом");
                else {
                System.out.print("Слово успішно оброблено автоматом! Фінальні стани - ");
                for (var item : states)
                System.out.print(item+" ");
                System.out.println("");
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Неправильний шлях");
            }
        }

    static Scanner getScanner(String pathname) throws FileNotFoundException {
        File file = new File(pathname);

        if (!file.exists()) {
            System.out.format("Файлу '%s' не існує.%n", pathname);
        }

        if (!file.canRead()) {
            System.out.format("Неможливо прочитати файл '%s'.%n", pathname);
        }

        return new Scanner(file);
    }
}
