package org.example;

import java.util.Scanner;

public class App {
    private static final int hours = 24;
    private static int[] costs = new int[hours];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String meny = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;
        System.out.println(meny);

        switch (scanner.nextLine()) {
            case "1":
                inmatning(scanner);
                break;
            case "2":
                minMax();
                break;
            case "3":
                sortera();
                break;
            case "4":
                laddningstid();
                break;
            case "5":
                visualisering();
                break;
            case "e":
                break;
        }
    }

    private static void inmatning(Scanner scanner) {
        System.out.print("Ange dygnets elpriser: \n");
        for (int i = 0; 0 < hours; i++) {
            System.out.print("Elpriset för timme " + String.format("%02d", i) + "-" + String.format("%02d", i + 1) + ": \n");
            costs[i] = scanner.nextInt();
        }
    }
}
