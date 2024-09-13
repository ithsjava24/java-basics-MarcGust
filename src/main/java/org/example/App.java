package org.example;

import java.util.Scanner;

public class App {
    private static final int hours = 24;
    private static final int[] costs = new int[hours];

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

        while (true) {
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
//            case "4":
//                laddningstid();
//                break;
//            case "5":
//                visualisering();
//                break;
                case "e":
                    return;
                default:
                    System.out.println("Felaktigt val. Välj igen.");
                    break;
            }
        }
    }

    private static void inmatning(Scanner scanner) {
        System.out.print("Ange dygnets elpriser: \n");
        for (int i = 0; 0 < hours; i++) {
            System.out.print("Elpriset för timme " + String.format("%02d", i) + "-" + String.format("%02d", i + 1) + ": \n");
            costs[i] = scanner.nextInt();
        }
    }

    private static void minMax() {
        int minCost = costs[0];
        int maxCost = costs[0];
        int minHour = 0;
        int maxHour = 0;
        int sum = 0;

        for (int i = 0; i < hours; i++) {
            if (costs[i] < minCost) {
                minCost = costs[i];
                minHour = i;
            }
            if (costs[i] > maxCost) {
                maxCost = costs[i];
                maxHour = i;
            }
            sum += costs[i];
        }

        float averageCost = sum / (float) hours;

        System.out.print("Lägsta pris: " + minHour + " - " + minHour + 1 + ", " + minCost + " öre/kWh\n");
        System.out.print("Högsta pris: " + maxHour + " - " + maxHour + 1 + ", " + maxCost + " öre/kWh\n");
        System.out.print("Medelpris: " + averageCost + " öre/kWh\n");
    }

    public static void sortera() {
        int[] sortHours = new int[hours];
        for (int i = 0; i < hours; i++) {
            sortHours[i] = i;
        }
    }
}
