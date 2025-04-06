import java.util.Scanner;

public class KrugProvjera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Unos podataka
        System.out.print("Unesi X koordinatu točke A: ");
        double x1 = scanner.nextDouble();

        System.out.print("Unesi Y koordinatu točke A: ");
        double y1 = scanner.nextDouble();

        System.out.print("Unesi X koordinatu centra kruga C: ");
        double x0 = scanner.nextDouble();

        System.out.print("Unesi Y koordinatu centra kruga C: ");
        double y0 = scanner.nextDouble();

        System.out.print("Unesi radijus kruga (r > 0): ");
        double r = scanner.nextDouble();

        if (r <= 0) {
            System.out.println("Radijus mora biti veći od 0.");
            scanner.close();
            System.exit(0); // Ispravno zatvaranje programa
        }

        // Izračun udaljenosti između točke A i centra C
        double udaljenost = Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));

        // Provjera
        if (udaljenost <= r) {
            System.out.println("Točka A pripada krugu.");
        } else {
            System.out.println("Točka A NE pripada krugu.");
        }

        scanner.close();
    }
}
