package org.example;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private static final int hours = 24;
    private static final int[] costs = new int[hours];

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("sv-SE"));
        Scanner scanner = new Scanner(System.in);

        String meny = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                5. Visualisering
                e. Avsluta
                """;
        System.out.println(meny);

        while (true) {
            switch (scanner.nextLine().toLowerCase()) {
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
                    return;
                default:
                    System.out.println("Felaktigt val. Välj igen.");
                    break;
            }
        }
    }

    public static void inmatning(Scanner scanner) {
        System.out.print("Ange dygnets elpriser: \n");
        for (int i = 0; i < hours; i++) {
            System.out.printf("Elpriset för timme " + String.format("%02d", i) + "-" + String.format("%02d", i + 1) + ": \n");
            costs[i] = scanner.nextInt();
        }

        scanner.nextLine();
    }

    public static void minMax() {
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

        System.out.printf("Lägsta pris: %02d-%02d, %d öre/kWh\n", minHour, minHour + 1, minCost);
        System.out.printf("Högsta pris: %02d-%02d, %d öre/kWh\n", maxHour, maxHour + 1, maxCost);
        System.out.printf("Medelpris: %.2f öre/kWh\n", averageCost);
    }

    public static void sortera() {
        int[] sortCosts = Arrays.copyOf(costs, costs.length);
        Arrays.sort(sortCosts);

        boolean[] written = new boolean[hours];

        System.out.print("Dyrast till billigast tider:\n");
        for (int i = sortCosts.length - 1; i >= 0; i--) {
            for (int j = 0; j < costs.length; j++) {
                if (costs[j] == sortCosts[i] && !written[j]) {
                    System.out.printf("%02d-%02d %d öre\n", j, j + 1, costs[j]);
                    written[j] = true;
                    break;
                }
            }
        }
    }

    public static void laddningstid() {
        int minSum = Integer.MAX_VALUE;
        int startHour = 0;

        for (int i = 0; i <= 20; i++) {
            int sum = costs[i] + costs[i + 1] + costs[i + 2] + costs[i + 3];
            if (sum < minSum) {
                minSum = sum;
                startHour = i;
            }
        }

        double averageCost = minSum / 4.0;
        System.out.printf("Påbörja laddning klockan %d\nMedelpris 4h: %.1f öre/kWh\n", startHour, averageCost);
    }

    public static void  visualisering() {
        System.out.println("Work in progress");

        /*// Bestäm bredden på grafen (antal kolumner) och höjd på grafen (antal rader)
        int maxGrafBredd = 76;
        int maxHojd = 10;

        // Hitta det högsta och lägsta priset för att skapa en skala
        double maxPris = Double.MIN_VALUE;
        double minPris = Double.MAX_VALUE;
        for (double pris : elpriser) {
            if (pris > maxPris) maxPris = pris;
            if (pris < minPris) minPris = pris;
        }

        // Beräkna skalfaktorn för att passa priserna inom grafens bredd
        double skalfaktor = (maxGrafBredd - 1) / (maxPris - minPris);

        // Skapa en matris för grafen, fylld med mellanslag
        char[][] graf = new char[maxHojd][24];
        for (int i = 0; i < maxHojd; i++) {
            for (int j = 0; j < 24; j++) {
                graf[i][j] = ' ';
            }
        }

        // Fyll i matrisen med "x" baserat på priserna
        for (int i = 0; i < 24; i++) {
            // Beräkna antal "x" baserat på priset och skalfaktorn
            int antalX = (int) Math.round((elpriser[i] - minPris) * skalfaktor);
            // Fyll i "x" i grafen
            for (int j = 0; j < Math.min(antalX, maxHojd); j++) {
                graf[maxHojd - j - 1][i] = 'x';
            }
        }

        // Skriv ut grafen från högsta till lägsta pris
        double steg = (maxPris - minPris) / maxHojd;
        for (int i = 0; i < maxHojd; i++) {
            double nivArPris = maxPris - i * steg;
            System.out.printf("%4.0f| ", nivArPris);
            for (int j = 0; j < 24; j++) {
                System.out.print(graf[i][j]);
            }
            System.out.println();
        }

        // Skriv ut axeln med timmarna (00-23)
        System.out.println("   |------------------------------------------------------------------------");
        System.out.print("   |");
        for (int i = 0; i < 24; i++) {
            System.out.printf(" %02d", i);
        }
        System.out.println();*/
    }
}