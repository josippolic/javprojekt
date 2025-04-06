import java.util.Scanner;

public class UnitConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== KONVERTER MJERNIH JEDINICA ====");
            System.out.println("1. Dužina (metri ↔ kilometri ↔ milje)");
            System.out.println("2. Masa (kilogrami ↔ funte)");
            System.out.println("3. Temperatura (C ↔ F ↔ K)");
            System.out.println("0. Izlaz");
            System.out.print("Odaberi kategoriju: ");

            String izbor = scanner.nextLine();

            switch (izbor) {
                case "1":
                    konvertirajDuzinu(scanner);
                    break;
                case "2":
                    konvertirajMasu(scanner);
                    break;
                case "3":
                    konvertirajTemperaturu(scanner);
                    break;
                case "0":
                    System.out.println("Izlaz...");
                    return;
                default:
                    System.out.println("Nevažeći izbor. Pritisni Enter za pokušaj ponovno.");
                    scanner.nextLine();
                    break;
            }
        }
    }

    static void konvertirajDuzinu(Scanner scanner) {
        System.out.println("\n-- Dužina --");
        System.out.println("Dostupne jedinice: m, km, mi");
        System.out.print("Unesi vrijednost (npr. 100m ili 1.5km): ");

        String unos = scanner.nextLine();
        ParsedUnos parsed = tryParseUnos(unos);

        if (parsed == null) {
            System.out.println("Nevažeći unos.");
            scanner.nextLine();
            return;
        }

        double vrijednost = parsed.vrijednost;
        String jedinica = parsed.jedinica;

        switch (jedinica) {
            case "m":
                System.out.printf("%.2f m = %.3f km\n", vrijednost, vrijednost / 1000);
                System.out.printf("%.2f m = %.3f mi\n", vrijednost, vrijednost / 1609.34);
                break;
            case "km":
                System.out.printf("%.2f km = %.0f m\n", vrijednost, vrijednost * 1000);
                System.out.printf("%.2f km = %.3f mi\n", vrijednost, vrijednost / 1.60934);
                break;
            case "mi":
                System.out.printf("%.2f mi = %.2f m\n", vrijednost, vrijednost * 1609.34);
                System.out.printf("%.2f mi = %.2f km\n", vrijednost, vrijednost * 1.60934);
                break;
            default:
                System.out.println("Nepoznata jedinica.");
        }

        System.out.println("Pritisni Enter za nastavak...");
        scanner.nextLine();
    }

    static void konvertirajMasu(Scanner scanner) {
        System.out.println("\n-- Masa --");
        System.out.println("Dostupne jedinice: kg, lb");
        System.out.print("Unesi vrijednost (npr. 70kg ili 150lb): ");

        String unos = scanner.nextLine();
        ParsedUnos parsed = tryParseUnos(unos);

        if (parsed == null) {
            System.out.println("Nevažeći unos.");
            scanner.nextLine();
            return;
        }

        double vrijednost = parsed.vrijednost;
        String jedinica = parsed.jedinica;

        if (jedinica.equals("kg")) {
            System.out.printf("%.2f kg = %.2f lb\n", vrijednost, vrijednost * 2.20462);
        } else if (jedinica.equals("lb")) {
            System.out.printf("%.2f lb = %.2f kg\n", vrijednost, vrijednost / 2.20462);
        } else {
            System.out.println("Nepoznata jedinica.");
        }

        System.out.println("Pritisni Enter za nastavak...");
        scanner.nextLine();
    }

    static void konvertirajTemperaturu(Scanner scanner) {
        System.out.println("\n-- Temperatura --");
        System.out.println("Dostupne jedinice: C, F, K");
        System.out.print("Unesi vrijednost (npr. 25C, 77F, 300K): ");

        String unos = scanner.nextLine();
        ParsedUnos parsed = tryParseUnos(unos);

        if (parsed == null) {
            System.out.println("Nevažeći unos.");
            scanner.nextLine();
            return;
        }

        double vrijednost = parsed.vrijednost;
        String jedinica = parsed.jedinica;

        switch (jedinica) {
            case "C":
                System.out.printf("%.2f°C = %.2f°F\n", vrijednost, (vrijednost * 9 / 5) + 32);
                System.out.printf("%.2f°C = %.2fK\n", vrijednost, vrijednost + 273.15);
                break;
            case "F":
                double c = (vrijednost - 32) * 5 / 9;
                System.out.printf("%.2f°F = %.2f°C\n", vrijednost, c);
                System.out.printf("%.2f°F = %.2fK\n", vrijednost, c + 273.15);
                break;
            case "K":
                double celsius = vrijednost - 273.15;
                System.out.printf("%.2fK = %.2f°C\n", vrijednost, celsius);
                System.out.printf("%.2fK = %.2f°F\n", vrijednost, celsius * 9 / 5 + 32);
                break;
            default:
                System.out.println("Nepoznata jedinica.");
        }

        System.out.println("Pritisni Enter za nastavak...");
        scanner.nextLine();
    }

    static class ParsedUnos {
        double vrijednost;
        String jedinica;

        ParsedUnos(double vrijednost, String jedinica) {
            this.vrijednost = vrijednost;
            this.jedinica = jedinica.toLowerCase();
        }
    }

    static ParsedUnos tryParseUnos(String unos) {
        if (unos == null || unos.trim().isEmpty()) return null;

        StringBuilder brojStr = new StringBuilder();
        StringBuilder jedinica = new StringBuilder();

        for (char c : unos.toCharArray()) {
            if (Character.isDigit(c) || c == '.' || c == ',') {
                brojStr.append(c);
            } else if (!Character.isWhitespace(c)) {
                jedinica.append(c);
            }
        }

        try {
            double vrijednost = Double.parseDouble(brojStr.toString().replace(',', '.'));
            return new ParsedUnos(vrijednost, jedinica.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
