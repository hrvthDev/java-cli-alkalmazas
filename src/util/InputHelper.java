package util;

import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine();
    }

    public static int readInt(String message) {

        while(true) {

            try {

                System.out.print(message);

                return Integer.parseInt(scanner.nextLine());

            } catch(NumberFormatException e) {

                System.out.println("Hibás számformátum. Próbáld újra.");
            }
        }
    }

    public static double readDouble(String message) {

        while(true) {

            try {

                System.out.print(message);

                return Double.parseDouble(scanner.nextLine());

            } catch(NumberFormatException e) {

                System.out.println("Hibás számformátum. Próbáld újra.");
            }
        }
    }

    public static boolean readYesNo(String message) {

        while(true) {

            System.out.print(message + " (i/n): ");

            String input = scanner.nextLine().toLowerCase();

            if(input.equals("i")) {
                return true;
            }

            if(input.equals("n")) {
                return false;
            }

            System.out.println("Csak 'i' vagy 'n' válasz adható.");
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}