import java.io.*;
import java.util.*;

public class TextProcessor {
    public static String readFile(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
    public static String removePunctuation(String text) {
        return text.replaceAll("[^a-z A-Z 0-9\\s]", "");
    }
    public static String removeExtraSpaces(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }
    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }
    public static String toUpperCase(String text) {
        return text.toUpperCase();
    }
    public static String filterWordsByLength(String text, int minLength) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() >= minLength) {
                result.append(word).append(" ");
            }
        }
        return result.toString().trim();
    }
    public static String filterNumbers(String text) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            try {
                Double.parseDouble(word);
                result.append(word).append(" ");
            } catch (NumberFormatException e) {
            }
        }
        return result.toString().trim();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = "";

        System.out.println("=== Text Processor - PP Lab1 ===\n");

        System.out.print("Introdu numele fisierului text: ");
        String filename = scanner.nextLine();
        try {
            text = readFile(filename);
            System.out.println("\n Continut original ");
            System.out.println(text);
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== Alege procesarea ===");
            System.out.println("1. Elimina semne de punctuatie");
            System.out.println("2. Elimina spatii multiple");
            System.out.println("3. Converteste la Lower Case");
            System.out.println("4. Converteste la Upper Case");
            System.out.println("5. Filtreaza cuvinte dupa numar minim de litere");
            System.out.println("6. Filtreaza doar numerele");
            System.out.println("7. Aplica toate procesarile");
            System.out.println("0. Iesire");
            System.out.print("Optiunea ta: ");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Optiune invalida!");
                continue;
            }

            String result = text;
            switch (option) {
                case 1:
                    result = removePunctuation(text);
                    System.out.println("\n--- Fara punctuatie ---");
                    System.out.println(result);
                    break;
                case 2:
                    result = removeExtraSpaces(text);
                    System.out.println("\n--- Fara spatii multiple ---");
                    System.out.println(result);
                    break;
                case 3:
                    result = toLowerCase(text);
                    System.out.println("\n--- Lower Case ---");
                    System.out.println(result);
                    break;
                case 4:
                    result = toUpperCase(text);
                    System.out.println("\n--- Upper Case ---");
                    System.out.println(result);
                    break;
                case 5:
                    System.out.print("Numar minim de litere: ");
                    int minLen;
                    try {
                        minLen = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Numar invalid!");
                        continue;
                    }
                    result = filterWordsByLength(removePunctuation(text), minLen);
                    System.out.println("\n--- Cuvinte cu minim " + minLen + " litere ---");
                    System.out.println(result);
                    break;
                case 6:
                    result = filterNumbers(text);
                    System.out.println("\n--- Doar numerele ---");
                    System.out.println(result);
                    break;
                case 7:
                    result = removePunctuation(text);
                    result = removeExtraSpaces(result);
                    result = toLowerCase(result);
                    System.out.println("\n--- Toate procesarile aplicate ---");
                    System.out.println(result);
                    break;
                case 0:
                    running = false;
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
        scanner.close();
    }
}
