import java.util.Scanner;

public class Cyclone {

    // Declaring global variables
    public static int gDatabaseSize;
    public static final int gMAX_CYCLONES = 50;
    public static double[] gWindSpeed = new double[gMAX_CYCLONES];
    public static Scanner gConsole = new Scanner(System.in);
    public static int gCyclonesAdded = 0;  // Counter for added/updated cyclones

    public static void main(String[] args) {

        System.out.println("Fall 2023 - UTSA - CS1083 - Section 006 - Project 2 - Cyclone - written by Hugo Villarreal\n");

        gDatabaseSize = getValidatedValue("Please, enter the number of cyclones in the database (max 50): ", 50);
        gWindSpeed = new double[gDatabaseSize];

        int option;
        do {
            option = getValidatedValue("\nMAIN MENU\n0 - Exit, 1 - Add/Update a cyclone, 2 - Summary, 3 - Clear Database, 4 - Show Cyclones, 5 - Swap Cyclones\nSelect an option: ", 5);
            if (option == 0){
                System.out.println("Thank you for looking at the cyclone database program!");
            }
            if (option == 1) {
                addUpdCyclone();
            } else if (option == 2) {
                summary();
            } else if (option == 3) {
                clearDB();
            } else if (option == 4) {
                showCyclones();
            } else if (option == 5) {
                swapCyclones();
            }
        } while (option != 0);
    }

    public static int getValidatedValue(String options, int max) {
        System.out.print(options);
        int value = gConsole.nextInt();

        while (value < 0 || value > max) {
            System.out.println("Invalid value, please, try again.");
            System.out.print(options);
            value = gConsole.nextInt();
        }

        return value;
    }

    public static void addUpdCyclone() {
        int index = getValidatedValue("Enter the index (" + 0 + " to " + (gDatabaseSize - 1) + "): ", gDatabaseSize - 1);
        System.out.println("The current wind speed of the cyclone at " + index + " is: "  + gWindSpeed[index]);
        double windSpeed = getValidatedValue("Enter the new wind speed (0 - 1000): ", 1000);

        if (gWindSpeed[index] == 0) {
            gCyclonesAdded++;
        }
        gWindSpeed[index] = windSpeed;
    }

    public static void summary() {
        System.out.println("Cyclones' Classification Summary");
        description();
    }

    public static void description() {
        System.out.println("Tropical Depression : " + getCyclonesByClass(1, 38));
        System.out.println("Tropical Storm      : " + getCyclonesByClass(39, 73));
        System.out.println("Hurricane Category 1: " + getCyclonesByClass(74, 95));
        System.out.println("Hurricane Category 2: " + getCyclonesByClass(96, 110));
        System.out.println("Hurricane Category 3: " + getCyclonesByClass(111, 129));
        System.out.println("Hurricane Category 4: " + getCyclonesByClass(130, 156));
        System.out.println("Hurricane Category 5: " + getCyclonesByClass(157, 1000));
    }

    public static int getCyclonesByClass(int minSpeed, int maxSpeed) {
        int count = 0;
        for (int i = 0; i < gDatabaseSize; i++) {
            if (gWindSpeed[i] >= minSpeed && gWindSpeed[i] <= maxSpeed) {
                count++;
            }
        }
        return count;
    }

    public static void clearDB() {
        for (int i = 0; i < gDatabaseSize; ++i) {
            gWindSpeed[i] = 0;
        }
    }

    public static void showCyclones() {
        System.out.println("LIST OF Cyclones' Wind Speed");
        for (int j = 0; j < gWindSpeed.length; j++) {
            System.out.printf("Cyclone[" + j + "]: %1d\n", (int) gWindSpeed[j]);
        }
    }

    public static void swapCyclones() {
        int idxFrom = getValidatedValue("Enter the position from (" + 0 + " to " + (gDatabaseSize-1) + "): ", gDatabaseSize - 1);
        int idxTo;
        do {
            idxTo = getValidatedValue("Enter the position to change from (" + 0 + " to " + (gDatabaseSize-1) + ") that is not " + idxFrom + ": ", gDatabaseSize - 1);
        } while (idxFrom == idxTo);
        swapValues(idxFrom, idxTo);
    }

    public static void swapValues(int from, int to) {
        double tempVal = gWindSpeed[from];
        gWindSpeed[from] = gWindSpeed[to];
        gWindSpeed[to] = tempVal;
    }
}
